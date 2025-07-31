package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum TaskType {
    VERIFICATION("Vérification"),
    MODERATION("Modération"),
    SUPPORT("Support client"),
    INVESTIGATION("Investigation"),
    APPROVAL("Approbation"),
    FOLLOW_UP("Suivi");

    private final String displayName;

    TaskType(String displayName) {
        this.displayName = displayName;
    }
}
