package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum AccessoryStatus {
    AVAILABLE("Disponible"),
    OUT_OF_STOCK("Rupture de stock"),
    DISCONTINUED("Arrêté"),
    PENDING_APPROVAL("En attente d'approbation"),
    REJECTED("Rejeté");

    private final String displayName;

    AccessoryStatus(String displayName) {
        this.displayName = displayName;
    }

}
