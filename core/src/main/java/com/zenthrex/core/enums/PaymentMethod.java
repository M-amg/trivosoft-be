package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT_CARD("Credit Card", "Credit card payment"),
    DEBIT_CARD("Debit Card", "Debit card payment"),
    BANK_TRANSFER("Bank Transfer", "Bank wire transfer"),
    PAYPAL("PayPal", "PayPal payment"),
    STRIPE("Stripe", "Stripe payment"),
    CASH("Cash", "Cash payment"),
    CHECK("Check", "Check payment"),
    DIGITAL_WALLET("Digital Wallet", "Digital wallet payment"),
    CRYPTOCURRENCY("Cryptocurrency", "Cryptocurrency payment"),
    BUY_NOW_PAY_LATER("Buy Now Pay Later", "BNPL payment");

    private final String displayName;
    private final String description;

    PaymentMethod(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
