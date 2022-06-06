package com.applicationpro.enums;

public enum ProductStatus {

    ACTIVE("Active"), PASSIVE("Inactive");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

