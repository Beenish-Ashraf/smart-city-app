package com.example.mycity;

public class FamousPlace {


    String cityName;
    String name;
    String discription;
    float rating;
    int usersRated;
    float latitude;
    float longitude;



    public String getCityName() {
        return cityName;
    }



    public FamousPlace(String cityName, String name, String discription, float rating, int usersRated, float latitude, float longitude) {
        this.cityName = cityName;
        this.name = name;
        this.discription = discription;
        this.rating = rating;
        this.usersRated = usersRated;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public float getRating() {
        return rating;
    }

    public int getUsersRated() {
        return usersRated;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }


}
