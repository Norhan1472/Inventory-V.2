package com.spring.products.enumeration;

public enum Status {
    ACTIVE("ACTIVE"),
    NOT_ACTIVE("NOT_ACTIVE");
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
