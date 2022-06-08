package com.applicationpro.enums;

public enum Unit {

    LBS("Pounds"), PCS("Pieces");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
