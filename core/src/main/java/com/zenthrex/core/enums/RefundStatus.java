package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum RefundStatus {
    REQUESTED("Requested", "Refund has been requested"),
    APPROVED("Approved", "Refund approved"),
    PROCESSING("Processing", "Refund is being processed"),
    COMPLETED("Completed", "Refund completed"),
    REJECTED("Rejected", "Refund request rejected"),
    FAILED("Failed", "Refund processing failed");

    private final String displayName;
    private final String description;

    RefundStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
