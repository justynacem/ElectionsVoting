package com.app.model;

public enum Education {

    LOWER("High School Education"),
    BACHELOR("Bachelor Degree"),
    MASTER("Master Degree"),
    PHD("PHD Degree");

    private String description;

    Education(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}