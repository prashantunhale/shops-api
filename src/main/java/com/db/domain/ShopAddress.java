package com.db.domain;

import com.google.maps.model.LatLng;

/**
 * Created by Prashant on 23-04-2017.
 */
public class ShopAddress {

    private String number;
    private String postCode;
    private LatLng coordinates;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "ShopAddress{" +
                "number='" + number + '\'' +
                ", postCode='" + postCode + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
