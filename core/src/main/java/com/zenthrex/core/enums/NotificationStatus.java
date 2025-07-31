// core/src/main/java/com/zenthrex/core/enums/NotificationStatus.java
package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum NotificationStatus {
    PENDING("En attente"),
    SENT("Envoyé"),
    DELIVERED("Livré"),
    READ("Lu"),
    FAILED("Échec"),
    BOUNCED("Rejeté"),
    CANCELLED("Annulé");

    private final String displayName;

    NotificationStatus(String displayName) {
        this.displayName = displayName;
    }
}


