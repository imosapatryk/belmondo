package com.example.paciu.belmondo.Location;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paciu on 21.03.2016.
 */
public class GoogleLocationProvider implements LocationDataProvider, LocationListener {
    private Location lastLocation;

    public LocationRequest getLocationRequest() {
        return locationRequest;
    }

    private LocationRequest locationRequest;
    private Set<LocationChangedListener> locationChangedListenerSet;

    public GoogleLocationProvider(GoogleApiClient client){
        this.locationChangedListenerSet = new HashSet<>();
        createAndSetupLocationRequest();
    }

    protected void createAndSetupLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void requestLocationUpdates(GoogleApiClient client){
        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
    }

    @Override
    public Location getLocation() {
        return lastLocation;
    }

    @Override
    public void registerLocationObserver(LocationChangedListener locationListener) {
        locationChangedListenerSet.add(locationListener);
    }

    @Override
    public void removeLocationObserver(LocationChangedListener locationListener) {
        locationChangedListenerSet.remove(locationListener);
    }

    @Override
    public void notifyLocationObservable(Location location) {
        for(LocationChangedListener l : locationChangedListenerSet){
            if(l != null){
                l.onLocationChanged(location);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if(location != null){
            notifyLocationObservable(location);
        }
    }
}

