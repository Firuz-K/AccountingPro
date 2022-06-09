package com.applicationpro.enums;

public enum Unit {

    LB("Pound"),
    PC("Piece"),
    M("Meter"),
    KG("Kilogram"),
    IN("Inch"),
    GAL("Gallon"),
    L("Liter");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
