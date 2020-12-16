package com.example.restaurantadvisorapp.restaurant;

public class RestaurantList {
    private int id;
    private String name;
    private String description;
    private Double grade;
    private String localization;
    private String phone_number;
    private String website;
    private String hours;

    public RestaurantList(int id, String name, String description, Double grade, String localization, String phone_number, String website, String hours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.grade = grade;
        this.localization = localization;
        this.phone_number = phone_number;
        this.website = website;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Double getGrade() {
        return grade;
    }

    String getDescription() {
        return description;
    }

    String getLocalization() {
        return localization;
    }

    String getPhone_number() {
        return phone_number;
    }

    String getWebsite() {
        return website;
    }

    String getHours() {
        return hours;
    }
}
