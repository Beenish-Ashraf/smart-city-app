package com.p.smartcitytravelers;

public class FamousPlace {


    private String city_name;
    private String name;
    private String id;
    private String image;
    private String city_id;
    private String discription;
    private double rating;
    private int usersRated;
    private double latitude;
    private double longitude;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FamousPlace(String city_name, String name, String id, String image, String city_id, String discription, double rating, int usersRated, double latitude, double longitude) {
        this.city_name = city_name;
        this.name = name;
        this.id = id;
        this.image = image;
        this.city_id = city_id;
        this.discription = discription;
        this.rating = rating;
        this.usersRated = usersRated;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public FamousPlace() {
    }

    public FamousPlace(String city_name, String name, String id, String city_id, String discription, double rating, int usersRated, double latitude, double longitude) {
        this.city_name = city_name;
        this.name = name;
        this.id = id;
        this.city_id = city_id;
        this.discription = discription;
        this.rating = rating;
        this.usersRated = usersRated;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
