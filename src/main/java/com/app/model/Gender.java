package com.app.model;

public enum Gender {

    MALE("MALE_GENDER"),
    FEMALE("FEMALE_GENDER");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}