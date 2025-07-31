package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    SYSTEM("Notification système"),
    ORDER("Commande"),
    BOOKING("Réservation"),
    PAYMENT("Paiement"),
    VERIFICATION("Vérification"),
    MARKETING("Marketing"),
    SECURITY("Sécurité"),
    MESSAGE("Message"),
    REMINDER("Rappel");

    private final String displayName;

    NotificationType(String displayName) {
        this.displayName = displayName;
    }
}
