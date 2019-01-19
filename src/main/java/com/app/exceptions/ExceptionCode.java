package com.app.exceptions;

public enum ExceptionCode {
    SERVICE ("SERVICE EXCEPTION"),
    SECURITY ("SECURITY EXCEPTION"),
    VALIDATION ("VALIDATION EXCEPTION"),
    REPOSITORY("REPOSITORY EXCEPTION"),
    FILE ("FILE EXCEPTION"),
    UNIDENTIFIED("UNIDENTIFIED EXCEPTION");

    private String description;

    ExceptionCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
