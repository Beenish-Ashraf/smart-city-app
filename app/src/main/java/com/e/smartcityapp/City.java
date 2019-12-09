package com.e.smartcityapp;

public class City {
    private String name;
    private String id;
    private int famousCities;
    private double rating;
    private int usersRated;
    private String description;

    public City(String name, String id, int famousCities, double rating, int usersRated, String description) {
        this.name = name;
        this.id = id;
        this.famousCities = famousCities;
        this.rating = rating;
        this.usersRated = usersRated;
        this.description = description;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFamousCities() {
        return famousCities;
    }

    public void setFamousCities(int famousCities) {
        this.famousCities = famousCities;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(int usersRated) {
        this.usersRated = usersRated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
