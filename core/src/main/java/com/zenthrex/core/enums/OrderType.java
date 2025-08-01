package com.zenthrex.core.enums;


import lombok.Getter;

@Getter
public enum OrderType {
    CARAVAN_BOOKING("Caravan Booking", "Caravan rental booking"),
    ACCESSORY_PURCHASE("Accessory Purchase", "Accessory purchase order"),
    SERVICE("Service", "Service booking"),
    MIXED("Mixed", "Mixed order with multiple types");

    private final String displayName;
    private final String description;

    OrderType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}