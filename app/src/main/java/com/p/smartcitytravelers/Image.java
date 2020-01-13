package com.p.smartcitytravelers;


public class Image {


    private String city_id;
    private String famousPlace_id;
    private int length;
    private int width;
    private String source;

    public Image() {
    }

    public Image(String city_id, String famousPlace_id, int length, int width, String source) {
        this.city_id = city_id;
        this.famousPlace_id = famousPlace_id;
        this.length = length;
        this.width = width;
        this.source = source;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getFamousPlace_id() {
        return famousPlace_id;
    }

    public void setFamousPlace_id(String famousPlace_id) {
        this.famousPlace_id = famousPlace_id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
