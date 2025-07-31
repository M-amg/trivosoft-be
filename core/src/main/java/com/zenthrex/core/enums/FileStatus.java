package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum FileStatus {
    UPLOADING("En cours d'upload"),
    UPLOADED("Uploadé"),
    PROCESSING("En traitement"),
    PROCESSED("Traité"),
    VERIFIED("Vérifié"),
    REJECTED("Rejeté"),
    DELETED("Supprimé"),
    EXPIRED("Expiré"),
    CORRUPTED("Corrompu");

    private final String displayName;

    FileStatus(String displayName) {
        this.displayName = displayName;
    }
}
