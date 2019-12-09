package com.e.smartcityapp;

import java.util.List;

public class FamousPlaceWithPhoto {
    private FamousPlace place;
    private List<Image> images;

    public FamousPlaceWithPhoto(FamousPlace place, List<Image> images) {
        this.place = place;
        this.images = images;
    }

    public FamousPlace getPlace() {
        return place;
    }

    public void setPlace(FamousPlace place) {
        this.place = place;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
