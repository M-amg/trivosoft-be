// core/src/main/java/com/zenthrex/core/enums/VerificationStatus.java
package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum VerificationStatus {
    PENDING("En attente"),
    IN_REVIEW("En cours de vérification"),
    APPROVED("Approuvé"),
    REJECTED("Rejeté"),
    EXPIRED("Expiré");

    private final String displayName;

    VerificationStatus(String displayName) {
        this.displayName = displayName;
    }
}


