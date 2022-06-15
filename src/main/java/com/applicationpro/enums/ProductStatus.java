package com.applicationpro.enums;

public enum ProductStatus {

    ACTIVE("Active"), DISABLED("Disabled"), OUT_OF_STOCK("Out of stock");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

