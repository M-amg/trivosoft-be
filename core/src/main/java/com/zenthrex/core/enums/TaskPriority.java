package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum TaskPriority {
    LOW("Faible"),
    NORMAL("Normal"),
    HIGH("Élevé"),
    URGENT("Urgent");

    private final String displayName;

    TaskPriority(String displayName) {
        this.displayName = displayName;
    }
}
