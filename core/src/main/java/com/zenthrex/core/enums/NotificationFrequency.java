package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum NotificationFrequency {
    INSTANT("Instantané"),
    HOURLY("Chaque heure"),
    DAILY("Quotidien"),
    WEEKLY("Hebdomadaire"),
    NEVER("Jamais");

    private final String displayName;

    NotificationFrequency(String displayName) {
        this.displayName = displayName;
    }
}
