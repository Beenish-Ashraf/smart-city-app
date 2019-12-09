package com.e.smartcityapp;

import java.util.List;

public class CityWithPhoto {
    private City city;
    private List<Image> images;

    public City getCity() {
        return city;
    }

    public CityWithPhoto(City city, List<Image> images) {
        this.city = city;
        this.images = images;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
