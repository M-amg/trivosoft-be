package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum InvoiceType {
    STANDARD("Standard", "Standard invoice"),
    PROFORMA("Pro Forma", "Pro forma invoice"),
    CREDIT_NOTE("Credit Note", "Credit note for refunds"),
    DEBIT_NOTE("Debit Note", "Debit note for additional charges"),
    RECURRING("Recurring", "Recurring subscription invoice");

    private final String displayName;
    private final String description;

    InvoiceType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}