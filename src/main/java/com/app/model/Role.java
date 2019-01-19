package com.app.model;

public enum Role {
    USER ("ROLE_USER"),
    ADMIN ("ROLE_ADMIN");

    private String fullName;

    Role(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
