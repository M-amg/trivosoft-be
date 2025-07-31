package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum DataRetentionPolicy {
    STANDARD("Standard (7 ans)"),
    MINIMAL("Minimal (3 ans)"),
    EXTENDED("Étendu (10 ans)");

    private final String displayName;

    DataRetentionPolicy(String displayName) {
        this.displayName = displayName;
    }
}
