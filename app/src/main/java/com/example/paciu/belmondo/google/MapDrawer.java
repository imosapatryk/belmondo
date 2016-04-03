package com.example.paciu.belmondo.google;

import android.location.Location;

import com.example.paciu.belmondo.Distance.DistanceInMetres;
import com.example.paciu.belmondo.utils.LocationUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by paciu on 01.04.2016.
 */
public class MapDrawer {
    private PolyLineAppearance polyLineAppearance;
    private boolean drawMap = false;
    private boolean drawingStarted = false;
    private Location lastLocation;
    private GoogleMap googleMap;
    private DistanceInMetres distanceDrawn;

    public MapDrawer(Location lastLocation, GoogleMap googleMap) {
        this.lastLocation = lastLocation;
        this.googleMap = googleMap;
        this.polyLineAppearance = new PolyLineAppearance();
        this.distanceDrawn = new DistanceInMetres();
    }

    public void draw(Location location){

        if(isDrawing()) {
            float distanceInMetresBetweenLocations = LocationUtils.getDistanceInMetres(getLastLatLng(), new LatLng(location.getLatitude(), location.getLongitude()));
            if ( distanceInMetresBetweenLocations > 1) {
                googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .color(polyLineAppearance.getLineColor())
                        .width(polyLineAppearance.getLineWidth()).add(getLastLatLng())
                        .add(new LatLng(location.getLatitude(), location.getLongitude())));
                distanceDrawn.addToDistance(distanceInMetresBetweenLocations);
            }
        }
        lastLocation = location;
    }

    public boolean isDrawing(){
        return drawMap;
    }

    public void startDrawing(){
        if(!drawingStarted){
            drawingStarted = true;
            drawMap = true;
            googleMap.addMarker(new MarkerOptions().title("Start")
                    .position(getLastLatLng()));
            distanceDrawn.setDistance(0);
        }
    }

    public void pauseDrawing(){
        if(drawingStarted)
            drawMap = false;
    }

    public void resumeDrawing(){
        if(drawingStarted && !drawMap)
            drawMap = true;
    }

    public void stopDrawing(){
        drawMap = false;
        drawingStarted = false;

        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                                .title("Stop")
                                                .position(getLastLatLng()));
    }

    protected LatLng getLastLatLng(){
        return new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
    }

    public PolyLineAppearance getLineAppearance(){
        return polyLineAppearance;
    }

    public DistanceInMetres getDistanceDrawn(){
        return distanceDrawn;
    }
}
