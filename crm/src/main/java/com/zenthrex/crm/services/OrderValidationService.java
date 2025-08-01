package com.zenthrex.crm.services;

import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.enums.OrderStatus;
import com.zenthrex.crm.dto.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderValidationService {

    // Valid status transitions
    private static final Set<OrderStatus> MODIFIABLE_STATUSES = EnumSet.of(
            OrderStatus.DRAFT,
            OrderStatus.PENDING
    );

    private static final Set<OrderStatus> CANCELLABLE_STATUSES = EnumSet.of(
            OrderStatus.DRAFT,
            OrderStatus.PENDING,
            OrderStatus.CONFIRMED
    );

    /**
     * Validate create order request
     */
    public void validateCreateOrderRequest(CreateOrderRequest request) {
        log.debug("Validating create order request for customer: {}", request.getCustomerId());

        if (request.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        if (request.getOrderType() == null || request.getOrderType().trim().isEmpty()) {
            throw new IllegalArgumentException("Order type is required");
        }

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        if (request.getCurrency() == null || request.getCurrency().trim().isEmpty()) {
            throw new IllegalArgumentException("Currency is required");
        }

        // Validate each order item
        request.getItems().forEach(this::validateOrderItem);

        log.debug("Create order request validation passed");
    }

    /**
     * Validate order item
     */
    private void validateOrderItem(com.zenthrex.crm.dto.order.CreateOrderItemRequest item) {
        if (item.getItemType() == null || item.getItemType().trim().isEmpty()) {
            throw new IllegalArgumentException("Item type is required");
        }

        if (item.getItemId() == null) {
            throw new IllegalArgumentException("Item ID is required");
        }

        if (item.getQuantity() == null || item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Item quantity must be positive");
        }

        if (item.getUnitPrice() == null || item.getUnitPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Item unit price cannot be negative");
        }
    }

    /**
     * Validate status transition
     */
    public void validateStatusTransition(OrderStatus fromStatus, OrderStatus toStatus) {
        log.debug("Validating status transition: {} -> {}", fromStatus, toStatus);

        if (fromStatus == toStatus) {
            throw new IllegalArgumentException("Order is already in " + toStatus + " status");
        }

        // Check if transition is valid based on current status
        switch (fromStatus) {
            case DRAFT -> {
                if (!EnumSet.of(OrderStatus.PENDING, OrderStatus.CANCELLED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from DRAFT to " + toStatus);
                }
            }
            case PENDING -> {
                if (!EnumSet.of(OrderStatus.CONFIRMED, OrderStatus.CANCELLED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from PENDING to " + toStatus);
                }
            }
            case CONFIRMED -> {
                if (!EnumSet.of(OrderStatus.PAID, OrderStatus.CANCELLED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from CONFIRMED to " + toStatus);
                }
            }
            case PAID -> {
                if (!EnumSet.of(OrderStatus.PROCESSING, OrderStatus.CANCELLED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from PAID to " + toStatus);
                }
            }
            case PROCESSING -> {
                if (!EnumSet.of(OrderStatus.SHIPPED, OrderStatus.CANCELLED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from PROCESSING to " + toStatus);
                }
            }
            case SHIPPED -> {
                if (!EnumSet.of(OrderStatus.DELIVERED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from SHIPPED to " + toStatus);
                }
            }
            case DELIVERED -> {
                if (!EnumSet.of(OrderStatus.COMPLETED).contains(toStatus)) {
                    throw new IllegalArgumentException("Cannot transition from DELIVERED to " + toStatus);
                }
            }
            case COMPLETED, CANCELLED, REFUNDED, FAILED -> {
                throw new IllegalArgumentException("Cannot change status from " + fromStatus);
            }
        }

        log.debug("Status transition validation passed");
    }

    /**
     * Validate order can be modified
     */
    public void validateOrderCanBeModified(Order order) {
        log.debug("Validating if order {} can be modified", order.getOrderNumber());

        if (!MODIFIABLE_STATUSES.contains(order.getStatus())) {
            throw new IllegalStateException(
                    String.format("Order in %s status cannot be modified", order.getStatus()));
        }

        log.debug("Order modification validation passed");
    }

    /**
     * Validate order cancellation
     */
    public void validateCancellation(Order order) {
        log.debug("Validating order cancellation for: {}", order.getOrderNumber());

        if (!CANCELLABLE_STATUSES.contains(order.getStatus())) {
            throw new IllegalStateException(
                    String.format("Order in %s status cannot be cancelled", order.getStatus()));
        }

        log.debug("Order cancellation validation passed");
    }

    /**
     * Validate payment processing
     */
    public void validatePaymentProcessing(Order order) {
        log.debug("Validating payment processing for: {}", order.getOrderNumber());

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed orders can be paid");
        }

        if (order.getTotalAmount() == null ||
                order.getTotalAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Order total amount must be positive");
        }

        log.debug("Payment processing validation passed");
    }

    /**
     * Validate order completion
     */
    public void validateOrderCompletion(Order order) {
        log.debug("Validating order completion for: {}", order.getOrderNumber());

        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new IllegalStateException("Only delivered orders can be completed");
        }

        log.debug("Order completion validation passed");
    }

    /**
     * Validate refund processing
     */
    public void validateRefundProcessing(Order order) {
        log.debug("Validating refund processing for: {}", order.getOrderNumber());

        if (!EnumSet.of(OrderStatus.PAID, OrderStatus.COMPLETED, OrderStatus.CANCELLED)
                .contains(order.getStatus())) {
            throw new IllegalStateException("Order status does not allow refund processing");
        }

        log.debug("Refund processing validation passed");
    }
}
