package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    PENDING("En attente"),
    IN_REVIEW("En cours de traitement"),
    APPROVED("Approuvé"),
    REJECTED("Rejeté"),
    CANCELLED("Annulé");

    private final String displayName;

    RequestStatus(String displayName) {
        this.displayName = displayName;
    }
}
