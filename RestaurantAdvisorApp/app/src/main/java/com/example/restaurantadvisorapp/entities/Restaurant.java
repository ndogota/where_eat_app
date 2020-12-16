package com.example.restaurantadvisorapp.entities;

public class Restaurant {
    private int id;
    private String name;
    private String description;
    private Double grade;
    private String localization;
    private String phone_number;
    private String website;
    private String hours;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Double getGrade() {
        return grade;
    }

    public String getLocalization() {
        return localization;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getWebsite() {
        return website;
    }

    public String getHours() {
        return hours;
    }
}
