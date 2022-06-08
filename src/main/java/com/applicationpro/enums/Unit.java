package com.applicationpro.enums;

public enum Unit {

    LB("Pound"), PC("Piece");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
