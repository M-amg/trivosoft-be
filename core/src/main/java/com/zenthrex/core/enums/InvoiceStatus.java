package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum InvoiceStatus {
    DRAFT("Draft", "Invoice is being prepared"),
    PENDING("Pending", "Invoice sent, awaiting payment"),
    PAID("Paid", "Invoice has been paid"),
    OVERDUE("Overdue", "Invoice payment is overdue"),
    CANCELLED("Cancelled", "Invoice was cancelled"),
    REFUNDED("Refunded", "Invoice was refunded"),
    PARTIALLY_PAID("Partially Paid", "Invoice partially paid");

    private final String displayName;
    private final String description;

    InvoiceStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}