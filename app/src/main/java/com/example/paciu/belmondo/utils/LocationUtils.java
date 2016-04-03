package com.example.paciu.belmondo.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by paciu on 01.04.2016.
 */
public class LocationUtils {
    public static float getDistanceInMetres(LatLng p1, LatLng p2){
        float [] results = new float[1];
        Location.distanceBetween(p1.latitude, p1.longitude, p2.latitude, p2.longitude, results);
        return results[0];
    }

    public static float getDistanceInMetres(Location l1, Location l2){
        return getDistanceInMetres(getLatLng(l1), getLatLng(l2));
    }

    public static LatLng getLatLng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

}
