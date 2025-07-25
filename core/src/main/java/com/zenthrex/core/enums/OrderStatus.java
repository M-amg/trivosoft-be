package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

}
