package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum TaxType {
    VAT("VAT", "Value Added Tax"),
    GST("GST", "Goods and Services Tax"),
    SALES_TAX("Sales Tax", "Sales Tax"),
    EXCISE_TAX("Excise Tax", "Excise Tax"),
    NONE("None", "No tax applied");

    private final String displayName;
    private final String description;

    TaxType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}