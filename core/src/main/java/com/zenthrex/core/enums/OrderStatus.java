package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    DRAFT("Draft", "Order is being prepared"),
    PENDING("Pending", "Order submitted, awaiting confirmation"),
    CONFIRMED("Confirmed", "Order confirmed, awaiting payment"),
    PAID("Paid", "Payment received"),
    PROCESSING("Processing", "Order is being processed"),
    SHIPPED("Shipped", "Order has been shipped"),
    DELIVERED("Delivered", "Order delivered to customer"),
    COMPLETED("Completed", "Order completed successfully"),
    CANCELLED("Cancelled", "Order was cancelled"),
    REFUNDED("Refunded", "Order was refunded"),
    FAILED("Failed", "Order processing failed");

    private final String displayName;
    private final String description;

    OrderStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

}
