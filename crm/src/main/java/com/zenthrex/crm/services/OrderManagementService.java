// crm/src/main/java/com/zenthrex/crm/services/OrderManagementService.java
package com.zenthrex.crm.services;

import com.zenthrex.core.entites.crm.ProcurementOrder;
import com.zenthrex.core.entites.crm.ProcurementOrderLine;
import com.zenthrex.core.enums.OrderStatus;
import com.zenthrex.core.repositories.ProcurementOrderRepository;
import com.zenthrex.crm.dto.OrderDto;
import com.zenthrex.crm.dto.CreateOrderRequest;
import com.zenthrex.crm.dto.OrderItemRequest;
import com.zenthrex.crm.dto.UpdateOrderStatusRequest;
import com.zenthrex.crm.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderManagementService {

    private final ProcurementOrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    /**
     * Create a new order from caravan booking or accessory purchase
     */
    public OrderDto createOrder(CreateOrderRequest request) {
        log.info("Creating new order for customer: {}", request.customerId());

        ProcurementOrder order = ProcurementOrder.builder()
                .number(generateOrderNumber())
                .total(calculateTotal(request.items()))
                .vat(calculateVAT(request.items()))
                .margin(calculateMargin(request.items()))
                .status(OrderStatus.PENDING)
                .build();

        // Create order lines
        List<ProcurementOrderLine> orderLines = request.items().stream()
                .map(item -> createOrderLine(item, order))
                .toList();

        order.setProcurementOrderLines(orderLines);

        ProcurementOrder savedOrder = orderRepository.save(order);

        // Send order confirmation
        notificationService.sendOrderConfirmation(savedOrder);

        log.info("Order created successfully with number: {}", savedOrder.getNumber());
        return orderMapper.toDto(savedOrder);
    }

    /**
     * Get orders with filtering and pagination
     */
    public Page<OrderDto> getOrders(String status, String customerEmail,
                                    LocalDateTime dateFrom, LocalDateTime dateTo,
                                    Pageable pageable) {

        log.info("Fetching orders with filters - status: {}, customer: {}", status, customerEmail);

        Page<ProcurementOrder> orders = orderRepository.findOrdersWithFilters(
                status, customerEmail, dateFrom, dateTo, pageable);

        return orders.map(orderMapper::toDto);
    }

    /**
     * Update order status
     */
    public OrderDto updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        log.info("Updating order {} status to: {}", orderId, request.status());

        ProcurementOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderStatus oldStatus = order.getStatus();
        order.setStatus(OrderStatus.valueOf(request.status()));

        ProcurementOrder updatedOrder = orderRepository.save(order);

        // Handle status-specific logic
        handleStatusChange(updatedOrder, oldStatus, request);

        return orderMapper.toDto(updatedOrder);
    }

    /**
     * Cancel order
     */
    public void cancelOrder(Long orderId, String reason) {
        log.info("Cancelling order: {} with reason: {}", orderId, reason);

        ProcurementOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        // Process refund if payment was made
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            paymentService.processRefund(order, reason);
        }

        notificationService.sendOrderCancellation(order, reason);
    }

    /**
     * Get order by ID with detailed information
     */
    public OrderDto getOrderById(Long orderId) {
        ProcurementOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderMapper.toDetailDto(order);
    }

    // ==================== PRIVATE HELPER METHODS ====================

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private Double calculateTotal(List<OrderItemRequest> items) {
        return items.stream()
                .mapToDouble(item -> item.unitPrice() * item.quantity())
                .sum();
    }

    private Double calculateVAT(List<OrderItemRequest> items) {
        Double subtotal = calculateTotal(items);
        return subtotal * 0.20; // 20% VAT - adjust based on your requirements
    }

    private Double calculateMargin(List<OrderItemRequest> items) {
        return items.stream()
                .mapToDouble(OrderItemRequest::margin)
                .sum();
    }

    private ProcurementOrderLine createOrderLine(OrderItemRequest item, ProcurementOrder order) {
        return ProcurementOrderLine.builder()
                .designation(item.designation())
                .quantity(item.quantity())
                .unitPrice(item.unitPrice())
                .vat(item.unitPrice() * item.quantity() * 0.20)
                .margin(item.margin())
                .entity(item.entityType())
                .entityId(item.entityId())
                .status("ORDERED")
                .procurementOrder(order)
                .build();
    }

    private void handleStatusChange(ProcurementOrder order, OrderStatus oldStatus,
                                    UpdateOrderStatusRequest request) {

        switch (order.getStatus()) {
            case CONFIRMED:
                // Process payment
                paymentService.processPayment(order);
                notificationService.sendOrderConfirmation(order);
                break;

            case COMPLETED:
                // Mark as delivered, update inventory
                updateInventoryOnCompletion(order);
                notificationService.sendOrderCompletion(order);
                break;

            case CANCELLED:
                // Handle cancellation logic
                if (oldStatus == OrderStatus.CONFIRMED) {
                    paymentService.processRefund(order, request.notes());
                }
                notificationService.sendOrderCancellation(order, request.notes());
                break;

            default:
                // Just send status update notification
                notificationService.sendOrderStatusUpdate(order, oldStatus);
        }
    }

    private void updateInventoryOnCompletion(ProcurementOrder order) {
        // Update inventory for accessories or mark caravan as unavailable
        order.getProcurementOrderLines().forEach(line -> {
            if ("ACCESSORY".equals(line.getEntity())) {
                // Update accessory stock
                inventoryService.updateAccessoryStock(line.getEntityId(), -line.getQuantity());
            } else if ("CARAVAN".equals(line.getEntity())) {
                // Mark caravan booking as completed
                caravanService.markBookingCompleted(line.getEntityId());
            }
        });
    }
}
