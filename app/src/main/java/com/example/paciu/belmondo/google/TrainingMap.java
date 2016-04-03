package com.example.paciu.belmondo.google;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by paciu on 02.04.2016.
 */
public class TrainingMap implements OnMapReadyCallback{
    private GoogleMap map;
    private MapDrawerBasedOnLocationSource mapDrawerBasedOnLocationSource;
    private LocationSource locationSource;
    private LocationSourceProvider sourceProvider;

    public TrainingMap(LocationSourceProvider sourceProvider){
        this.sourceProvider = sourceProvider;
        this.locationSource = sourceProvider.getNewLocationSource();;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
        mapDrawerBasedOnLocationSource = new MapDrawerBasedOnLocationSource(map.getMyLocation(), map, sourceProvider.getNewLocationSource());
    }

    protected void setupMap(GoogleMap googleMap){
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setLocationSource(locationSource);
    }

    public MapDrawerBasedOnLocationSource getMapDrawer(){
        return mapDrawerBasedOnLocationSource;
    }
}

