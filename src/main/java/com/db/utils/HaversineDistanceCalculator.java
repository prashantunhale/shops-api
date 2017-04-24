package com.db.utils;

import com.google.maps.model.LatLng;

/**
 * Reference: https://en.wikipedia.org/wiki/Haversine_formula
 * Created by Prashant on 23-04-2017.
 */
public class HaversineDistanceCalculator {
    public static final double R = 6372.8; // In kilometers

    public static double calculate(LatLng customerLocation, LatLng shopLocation) {
        double lat1 = customerLocation.lat;
        double lon1 = customerLocation.lng;
        double lat2 = shopLocation.lat;
        double lon2 = shopLocation.lng;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

}
