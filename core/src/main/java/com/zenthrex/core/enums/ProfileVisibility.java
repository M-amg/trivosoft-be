package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum ProfileVisibility {
    PUBLIC("Public"),
    PRIVATE("Privé"),
    CONTACTS_ONLY("Contacts uniquement");

    private final String displayName;

    ProfileVisibility(String displayName) {
        this.displayName = displayName;
    }
}
