package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("Pending", "Payment is pending"),
    PROCESSING("Processing", "Payment is being processed"),
    COMPLETED("Completed", "Payment completed successfully"),
    FAILED("Failed", "Payment failed"),
    CANCELLED("Cancelled", "Payment was cancelled"),
    REFUNDED("Refunded", "Payment was refunded"),
    PARTIALLY_REFUNDED("Partially Refunded", "Payment partially refunded"),
    DISPUTED("Disputed", "Payment is disputed"),
    EXPIRED("Expired", "Payment link expired");

    private final String displayName;
    private final String description;

    PaymentStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}