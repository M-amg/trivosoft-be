package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum VerificationType {
    IDENTITY("Vérification d'identité"),
    BUSINESS("Vérification d'entreprise"),
    ADDRESS("Vérification d'adresse"),
    PHONE("Vérification de téléphone"),
    EMAIL("Vérification d'email"),
    DOCUMENT("Vérification de documents");

    private final String displayName;

    VerificationType(String displayName) {
        this.displayName = displayName;
    }
}
