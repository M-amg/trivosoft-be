package com.zenthrex.core.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum UserStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    PENDING("Pending"),
    DELETED("Deleted");

    private final String displayName;

    UserStatus(String displayName) {
        this.displayName = displayName;
    }
}