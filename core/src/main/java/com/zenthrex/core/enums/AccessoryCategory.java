package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum AccessoryCategory {

    // Équipements d'Attelage & Sécurité
    HITCHING_SECURITY("Attelage & Sécurité"),

    // Équipements Extérieurs
    EXTERIOR_EQUIPMENT("Équipements Extérieurs"),

    // Aménagement Intérieur
    INTERIOR_FITTINGS("Aménagement Intérieur"),

    // Énergie & Autonomie
    ENERGY_AUTONOMY("Énergie & Autonomie"),

    // Accessoires de Voyage & Technologie
    TRAVEL_TECHNOLOGY("Voyage & Technologie"),

    // Entretien & Nettoyage
    MAINTENANCE_CLEANING("Entretien & Nettoyage");

    private final String displayName;

    AccessoryCategory(String displayName) {
        this.displayName = displayName;
    }

}
