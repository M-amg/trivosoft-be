package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum NotificationPriority {
    LOW("Faible"),
    NORMAL("Normal"),
    HIGH("Élevé"),
    URGENT("Urgent");

    private final String displayName;

    NotificationPriority(String displayName) {
        this.displayName = displayName;
    }
}
