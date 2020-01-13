package com.p.smartcitytravelers;

import java.io.Serializable;

public class FamousPlaces implements Serializable {

    String imageFamous;
    String famousPlaceName;
    Double longitude;
    Double latitude;
    Double rating;

    public FamousPlaces(String imageFamous, String famousPlaceName, Double rating) {
        this.imageFamous = imageFamous;
        this.famousPlaceName = famousPlaceName;
        this.rating = rating;
    }

    public String getImageFamous() {
        return imageFamous;
    }

    public String getFamousPlaceName() {
        return famousPlaceName;
    }

    public Double getRating() {
        return rating;
    }
}
