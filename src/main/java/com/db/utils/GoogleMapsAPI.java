package com.db.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Query latitude and longitude by providing address to google maps api
 * Created by Prashant on 23-04-2017.
 */
public class GoogleMapsAPI {

    private GeoApiContext context;
    public GoogleMapsAPI(){
        this.context = new GeoApiContext().setApiKey("AIzaSyDaOOcQbbaFa7e_HMV0flS3hvHD2nsz_Q4");
    }

    public LatLng getDetails(String address) throws Exception{
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();
        return results[0].geometry.location;
    }

}
