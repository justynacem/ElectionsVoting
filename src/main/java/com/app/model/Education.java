package com.app.model;

public enum Education {

    LOWER("HIGH_SCHOOL_EDUCATION"),
    BACHELOR("BACHELOR_EDUCATION"),
    MASTER("MASTER_EDUCATION"),
    PHD("PHD_EDUCATION");

    private String description;

    Education(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}