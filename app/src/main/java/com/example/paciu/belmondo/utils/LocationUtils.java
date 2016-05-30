package com.example.paciu.belmondo.Utils;

import android.location.Location;

import com.example.paciu.belmondo.Result.LatLngSerializableImpl;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

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

    public static double [] getLatitudes(List<LatLngSerializableImpl> mapCoords){
        if(mapCoords != null) {
            double[] latitudes = new double[mapCoords.size()];
            for (int i = 0; i < mapCoords.size(); i++) {
                latitudes[i] = mapCoords.get(i).latitude;
            }
            return latitudes;
        } return new double[0];
    }

    public static double [] getLongitudes(List<LatLngSerializableImpl> mapCoords){
        if(mapCoords != null) {
            double[] longitudes = new double[mapCoords.size()];
            for (int i = 0; i < mapCoords.size(); i++) {
                longitudes[i] = mapCoords.get(i).longitude;
            }
            return longitudes;
        } return new double[0];
    }

    public static double [] getAltitudes(List<LatLngSerializableImpl> mapCoords){
        if(mapCoords != null) {
            double[] altitudes = new double[mapCoords.size()];
            for (int i = 0; i < mapCoords.size(); i++) {
                altitudes[i] = 0;
            }
            return altitudes;
        } return new double[0];
    }
}
