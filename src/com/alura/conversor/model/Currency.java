package com.alura.conversor.model;

public enum Currency {
    USD("USD", "Dólar Estadounidense"),
    ARS("ARS", "Peso Argentino"),
    BRL("BRL", "Real Brasileño"),
    COP("COP", "Peso Colombiano");

    private final String code;
    private final String displayName;

    Currency(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
