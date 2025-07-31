package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    PENDING("En attente"),
    IN_PROGRESS("En cours"),
    COMPLETED("Terminé"),
    CANCELLED("Annulé"),
    ON_HOLD("En pause");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }
}
