package com.example.mycity;


public class Image {


    String city;
    String famousPlace;

    int length;
    int width;
    String source;

    public Image(String city, String famousPlace, int length, int width, String source) {
        this.city = city;
        this.famousPlace = famousPlace;
        this.length = length;
        this.width = width;
        this.source = source;
    }

    public String getCity() {
        return city;
    }

    public String getFamousPlace() {
        return famousPlace;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getSource() {
        return source;
    }

    public Image(int length, int width, String source) {
        this.length = length;
        this.width = width;
        this.source = source;
    }
}
