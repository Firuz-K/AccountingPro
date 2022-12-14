package com.applicationpro.enums;

public enum InvoiceStatus {
    DRAFT("Draft"), APPROVED("Approved");

    private final String value;

    InvoiceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
