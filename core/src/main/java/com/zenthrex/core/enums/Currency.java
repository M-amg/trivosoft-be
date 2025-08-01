package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum Currency {
    EUR("EUR", "Euro", "€", 2),
    USD("USD", "US Dollar", "$", 2),
    GBP("GBP", "British Pound", "£", 2),
    MAD("MAD", "Moroccan Dirham", "MAD", 2),
    CAD("CAD", "Canadian Dollar", "C$", 2),
    AUD("AUD", "Australian Dollar", "A$", 2);

    private final String code;
    private final String displayName;
    private final String symbol;
    private final int decimalPlaces;

    Currency(String code, String displayName, String symbol, int decimalPlaces) {
        this.code = code;
        this.displayName = displayName;
        this.symbol = symbol;
        this.decimalPlaces = decimalPlaces;
    }
}
