package com.p.smartcitytravelers;

public class City {
    private int famousCities;
    private String id;
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public City(int famousCities, String id, String image, String name, double rating, int usersRated) {
        this.famousCities = famousCities;
        this.id = id;
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.usersRated = usersRated;
    }

    private String name;
    private double rating;
    private int usersRated;


    public City(int famousCities, String id, String name, double rating, int usersRated) {
        this.famousCities = famousCities;
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.usersRated = usersRated;
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


}
