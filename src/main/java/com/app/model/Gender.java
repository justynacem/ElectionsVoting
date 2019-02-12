package com.app.model;

public enum Gender {

    MALE("Female Gender"),
    FEMALE("Male Gender");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}