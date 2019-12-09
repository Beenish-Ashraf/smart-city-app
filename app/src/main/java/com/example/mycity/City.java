package com.example.mycity;

public class City {
    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public City(String name, int famousCities, double rating, String description, String image) {
        this.name = name;
        this.famousCities = famousCities;
        this.rating = rating;
        this.description = description;
        this.image = image;
    }

    String name;
    int famousCities;
    double rating;
    String description;
    String image;



    public String getName() {
        return name;
    }

    public int getFamousCities() {
        return famousCities;
    }

    public City(String name, int famousCities) {

        this.name = name;
        this.famousCities = famousCities;
    }
}
