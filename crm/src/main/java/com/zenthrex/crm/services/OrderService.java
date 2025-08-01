// ==================== ORDER SERVICE IMPLEMENTATION ====================

package com.zenthrex.crm.services;

import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.crm.OrderItem;
import com.zenthrex.core.entites.crm.OrderStatusHistory;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.enums.OrderStatus;
import com.zenthrex.core.enums.OrderType;
import com.zenthrex.core.enums.Currency;
import com.zenthrex.core.exception.ResourceNotFoundException;
import com.zenthrex.core.repositories.OrderItemRepository;
import com.zenthrex.core.repositories.OrderRepository;
import com.zenthrex.core.repositories.OrderStatusHistoryRepository;
import com.zenthrex.core.repositories.UserRepository;
import com.zenthrex.crm.dto.mapper.OrderMapper;
import com.zenthrex.crm.dto.order.*;
import com.zenthrex.notification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final NotificationService notificationService;
    private final OrderNumberGenerator orderNumberGenerator;
    private final OrderValidationService orderValidationService;

    // ==================== ORDER CREATION ====================

    /**
     * Create new order from request
     */
    public OrderDto createOrder(CreateOrderRequest request) {
        log.info("Creating new order for customer: {}", request.getCustomerId());

        // Validate request
        orderValidationService.validateCreateOrderRequest(request);

        // Get customer
        User customer = userRepository.findById(Math.toIntExact(request.getCustomerId()))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Create order entity
        Order order = Order.builder()
                .orderNumber(orderNumberGenerator.generateOrderNumber())
                .orderType(OrderType.valueOf(request.getOrderType()))
                .status(OrderStatus.DRAFT)
                .customer(customer)
                .customerEmail(customer.getEmail())
                .customerPhone(customer.getPhone())
                .currency(Currency.valueOf(request.getCurrency()))
                .notes(request.getNotes())
                .createdBy(getCurrentUserId())
                .build();

        // Set addresses
        if (request.getBillingAddress() != null) {
            setBillingAddress(order, request.getBillingAddress());
        }

        if (request.getShippingAddress() != null) {
            setShippingAddress(order, request.getShippingAddress());
        }

        // Save order first to get ID
        Order savedOrder = orderRepository.save(order);

        // Create order items
        List<OrderItem> orderItems = createOrderItems(savedOrder, request.getItems());

        // Calculate totals
        calculateOrderTotals(savedOrder, orderItems);

        // Save updated order
        savedOrder = orderRepository.save(savedOrder);

        // Create status history
        createStatusHistory(savedOrder, null, OrderStatus.DRAFT, "Order created", "Order created from request");

        log.info("Order created successfully: {}", savedOrder.getOrderNumber());

        return orderMapper.toDto(savedOrder);
    }

    /**
     * Create order from caravan booking
     */
    public OrderDto createOrderFromBooking(CaravanBooking booking) {
        log.info("Creating order from caravan booking: {}", booking.getId());

        // Create order from booking
        Order order = Order.builder()
                .orderNumber(orderNumberGenerator.generateOrderNumber())
                .orderType(OrderType.CARAVAN_BOOKING)
                .status(OrderStatus.PENDING)
                .customerEmail("customer@example.com")
                .currency(Currency.EUR)
                .notes("Created from caravan booking")
                .createdBy(1L) // System user
                .build();

        // Calculate totals from booking
        BigDecimal bookingTotal = calculateBookingTotal(booking);
        order.setSubtotal(bookingTotal);
        order.setTotalAmount(bookingTotal);

        Order savedOrder = orderRepository.save(order);

        // Create order item for booking
        OrderItem bookingItem = OrderItem.builder()
                .order(savedOrder)
                .itemType("CARAVAN_BOOKING")
                .itemId(booking.getId())
                .itemName("Caravan Booking")
                .itemDescription("Caravan booking from " + booking.getStartDate() + " to " + booking.getEndDate())
                .quantity(1)
                .unitPrice(bookingTotal)
                .lineTotal(bookingTotal)
                .startDate(booking.getStartDate().atStartOfDay())
                .endDate(booking.getEndDate().atStartOfDay())
                .build();

        orderItemRepository.save(bookingItem);


        log.info("Order created from booking: {}", savedOrder.getOrderNumber());

        return orderMapper.toDto(savedOrder);
    }

    // ==================== ORDER RETRIEVAL ====================

    /**
     * Get order by ID
     */
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long id) {
        log.info("Fetching order by ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderMapper.toDto(order);
    }

    /**
     * Get order by order number
     */
    @Transactional(readOnly = true)
    public OrderDto getOrderByNumber(String orderNumber) {
        log.info("Fetching order by number: {}", orderNumber);

        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderMapper.toDto(order);
    }

    /**
     * Get orders with filters
     */
    @Transactional(readOnly = true)
    public Page<OrderDto> getOrdersWithFilters(OrderType orderType, OrderStatus status,
                                               Long customerId, LocalDateTime startDate,
                                               LocalDateTime endDate, Pageable pageable) {
        log.info("Fetching orders with filters - type: {}, status: {}, customer: {}",
                orderType, status, customerId);

        Page<Order> orders = orderRepository.findOrdersWithFilters(
                orderType, status, customerId, startDate, endDate, pageable);

        return orders.map(orderMapper::toDto);
    }

    /**
     * Get customer orders
     */
    @Transactional(readOnly = true)
    public Page<OrderDto> getCustomerOrders(Long customerId, Pageable pageable) {
        log.info("Fetching orders for customer: {}", customerId);

        User customer = userRepository.findById(Math.toIntExact(customerId))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Page<Order> orders = orderRepository.findByCustomerOrderByCreatedAtDesc(customer, pageable);

        return orders.map(orderMapper::toDto);
    }

    // ==================== ORDER STATUS MANAGEMENT ====================

    /**
     * Update order status
     */
    public OrderDto updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        log.info("Updating order {} status to: {}", orderId, request.getStatus());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderStatus oldStatus = order.getStatus();
        OrderStatus newStatus = OrderStatus.valueOf(request.getStatus());

        // Validate status transition
        orderValidationService.validateStatusTransition(oldStatus, newStatus);

        // Update order
        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(getCurrentUserId());

        Order updatedOrder = orderRepository.save(order);

        // Create status history
        createStatusHistory(order, oldStatus, newStatus, request.getReason(), request.getNotes());

        // Handle status-specific logic
        handleStatusChange(order, oldStatus, newStatus);

        // Send notifications
        notificationService.sendOrderStatusUpdate(
                orderRepository.findByOrderNumber(order.getOrderNumber()).orElseThrow( () -> new ResourceNotFoundException("Order not found")),
                oldStatus.name()
        );

        log.info("Order status updated: {} -> {}", oldStatus, newStatus);

        return orderMapper.toDto(updatedOrder);
    }

    /**
     * Cancel order
     */
    public OrderDto cancelOrder(Long orderId, CancelOrderRequest request) {
        log.info("Cancelling order: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // Validate cancellation
        orderValidationService.validateCancellation(order);

        OrderStatus oldStatus = order.getStatus();

        // Update order
        order.setStatus(OrderStatus.CANCELLED);
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(getCurrentUserId());

        Order cancelledOrder = orderRepository.save(order);

        // Create status history
        createStatusHistory(order, oldStatus, OrderStatus.CANCELLED,
                request.getReason(), request.getNotes());

        // Handle cancellation logic
        handleOrderCancellation(order, request);

        log.info("Order cancelled: {}", order.getOrderNumber());

        return orderMapper.toDto(cancelledOrder);
    }

    // ==================== ORDER ITEMS MANAGEMENT ====================

    /**
     * Add item to order
     */
    public OrderDto addOrderItem(Long orderId, CreateOrderItemRequest request) {
        log.info("Adding item to order: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // Validate order can be modified
        orderValidationService.validateOrderCanBeModified(order);

        // Create order item
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .itemType(request.getItemType())
                .itemId(request.getItemId())
                .itemName(getItemName(request.getItemType(), request.getItemId()))
                .itemDescription(getItemDescription(request.getItemType(), request.getItemId()))
                .itemSku(getItemSku(request.getItemType(), request.getItemId()))
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .discountAmount(request.getDiscountAmount())
                .lineTotal(calculateLineTotal(request))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .notes(request.getNotes())
                .build();

        orderItemRepository.save(orderItem);

        // Recalculate order totals
        List<OrderItem> allItems = orderItemRepository.findByOrderOrderBySortOrderAsc(order);
        calculateOrderTotals(order, allItems);

        Order updatedOrder = orderRepository.save(order);

        log.info("Item added to order: {}", orderItem.getItemName());

        return orderMapper.toDto(updatedOrder);
    }

    /**
     * Remove item from order
     */
    public OrderDto removeOrderItem(Long orderId, Long itemId) {
        log.info("Removing item {} from order: {}", itemId, orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // Validate order can be modified
        orderValidationService.validateOrderCanBeModified(order);

        OrderItem orderItem = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));

        if (!orderItem.getOrder().getId().equals(orderId)) {
            throw new IllegalArgumentException("Order item does not belong to this order");
        }

        orderItemRepository.delete(orderItem);

        // Recalculate order totals
        List<OrderItem> remainingItems = orderItemRepository.findByOrderOrderBySortOrderAsc(order);
        calculateOrderTotals(order, remainingItems);

        Order updatedOrder = orderRepository.save(order);

        log.info("Item removed from order: {}", orderItem.getItemName());

        return orderMapper.toDto(updatedOrder);
    }

    // ==================== ORDER HISTORY ====================

    /**
     * Get order status history
     */
    @Transactional(readOnly = true)
    public List<OrderStatusHistoryDto> getOrderHistory(Long orderId) {
        log.info("Fetching order history for: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        List<OrderStatusHistory> history = orderStatusHistoryRepository
                .findByOrderOrderByChangedAtDesc(order);

        return history.stream()
                .map(this::mapToStatusHistoryDto)
                .collect(Collectors.toList());
    }

    // ==================== PRIVATE HELPER METHODS ====================

    private List<OrderItem> createOrderItems(Order order, List<CreateOrderItemRequest> itemRequests) {
        return itemRequests.stream()
                .map(request -> createOrderItem(order, request))
                .collect(Collectors.toList());
    }

    private OrderItem createOrderItem(Order order, CreateOrderItemRequest request) {
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .itemType(request.getItemType())
                .itemId(request.getItemId())
                .itemName(getItemName(request.getItemType(), request.getItemId()))
                .itemDescription(getItemDescription(request.getItemType(), request.getItemId()))
                .itemSku(getItemSku(request.getItemType(), request.getItemId()))
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .discountAmount(request.getDiscountAmount())
                .lineTotal(calculateLineTotal(request))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .notes(request.getNotes())
                .build();

        return orderItemRepository.save(orderItem);
    }

    private void calculateOrderTotals(Order order, List<OrderItem> items) {
        BigDecimal subtotal = items.stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxAmount = calculateTaxAmount(subtotal);
        BigDecimal totalAmount = subtotal.add(taxAmount);

        order.setSubtotal(subtotal);
        order.setTaxAmount(taxAmount);
        order.setTotalAmount(totalAmount);
    }

    private BigDecimal calculateLineTotal(CreateOrderItemRequest request) {
        BigDecimal total = request.getUnitPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        if (request.getDiscountAmount() != null) {
            total = total.subtract(request.getDiscountAmount());
        }
        return total;
    }

    private BigDecimal calculateTaxAmount(BigDecimal subtotal) {
        // Apply standard VAT rate of 20% TODO : Make configurable
        return subtotal.multiply(new BigDecimal("0.20"));
    }

    private BigDecimal calculateBookingTotal(CaravanBooking booking) {
        // Calculate total from booking details TODO : Implement actual logic
        return BigDecimal.valueOf(booking.getPriceUnite() != null ? booking.getPriceUnite() : 100.0);
    }

    private void setBillingAddress(Order order, CreateAddressRequest address) {
        order.setBillingAddressLine1(address.getAddressLine1());
        order.setBillingAddressLine2(address.getAddressLine2());
        order.setBillingCity(address.getCity());
        order.setBillingState(address.getState());
        order.setBillingPostalCode(address.getPostalCode());
        order.setBillingCountry(address.getCountry());
    }

    private void setShippingAddress(Order order, CreateAddressRequest address) {
        order.setShippingAddressLine1(address.getAddressLine1());
        order.setShippingAddressLine2(address.getAddressLine2());
        order.setShippingCity(address.getCity());
        order.setShippingState(address.getState());
        order.setShippingPostalCode(address.getPostalCode());
        order.setShippingCountry(address.getCountry());
    }

    private void createStatusHistory(Order order, OrderStatus fromStatus, OrderStatus toStatus,
                                     String reason, String notes) {
        OrderStatusHistory history = OrderStatusHistory.builder()
                .order(order)
                .fromStatus(fromStatus)
                .toStatus(toStatus)
                .reason(reason)
                .notes(notes)
                .changedBy(getCurrentUserId())
                .build();

        orderStatusHistoryRepository.save(history);
    }

    private void handleStatusChange(Order order, OrderStatus oldStatus, OrderStatus newStatus) {
        switch (newStatus) {
            case CONFIRMED -> handleOrderConfirmation(order);
            case PAID -> handleOrderPayment(order);
            case SHIPPED -> handleOrderShipment(order);
            case DELIVERED -> handleOrderDelivery(order);
            case COMPLETED -> handleOrderCompletion(order);
            case CANCELLED -> handleOrderCancellation(order, null);
        }
    }

    private void handleOrderConfirmation(Order order) {
        log.info("Handling order confirmation: {}", order.getOrderNumber());
        // Implement confirmation logic
        // - Reserve inventory
        // - Generate invoice
        // - Send confirmation notification
    }

    private void handleOrderPayment(Order order) {
        log.info("Handling order payment: {}", order.getOrderNumber());
        // Implement payment logic
        // - Update payment status
        // - Release reserved inventory
        // - Schedule fulfillment
    }

    private void handleOrderShipment(Order order) {
        log.info("Handling order shipment: {}", order.getOrderNumber());
        // Implement shipment logic
        // - Generate shipping labels
        // - Update tracking information
        // - Send shipment notification
    }

    private void handleOrderDelivery(Order order) {
        log.info("Handling order delivery: {}", order.getOrderNumber());
        // Implement delivery logic
        // - Update delivery confirmation
        // - Send delivery notification
        // - Start completion timer
    }

    private void handleOrderCompletion(Order order) {
        log.info("Handling order completion: {}", order.getOrderNumber());
        // Implement completion logic
        // - Generate receipt
        // - Update customer metrics
        // - Send completion notification
    }

    private void handleOrderCancellation(Order order, CancelOrderRequest request) {
        log.info("Handling order cancellation: {}", order.getOrderNumber());
        // Implement cancellation logic
        // - Release reserved inventory
        // - Process refunds if applicable
        // - Send cancellation notification

        if (request != null && request.getProcessRefund()) {
            // Initiate refund process
            log.info("Processing refund for cancelled order: {}", order.getOrderNumber());
        }
    }

    private String getItemName(String itemType, Long itemId) {
        // Fetch item name based on type and ID
        // This would integrate with other modules (Caravan, Accessory, etc.)
        return "Item Name"; // Placeholder
    }

    private String getItemDescription(String itemType, Long itemId) {
        // Fetch item description based on type and ID
        return "Item Description"; // Placeholder
    }

    private String getItemSku(String itemType, Long itemId) {
        // Fetch item SKU based on type and ID
        return "ITEM-SKU"; // Placeholder
    }

    private Long getCurrentUserId() {
        // Get current user from security Module
        return 1L; // Placeholder
    }

    private OrderStatusHistoryDto mapToStatusHistoryDto(OrderStatusHistory history) {
        return new OrderStatusHistoryDto(
                history.getId(),
                history.getFromStatus() != null ? history.getFromStatus().name() : null,
                history.getToStatus().name(),
                history.getReason(),
                history.getNotes(),
                history.getChangedBy(),
                getUserName(history.getChangedBy()), // Fetch user name
                history.getChangedAt()
        );
    }

    private String getUserName(Long userId) {
        return userRepository.findById(Math.toIntExact(userId))
                .map(user -> user.getFirstname() + " " + user.getLastname())
                .orElse("Unknown User");
    }
}