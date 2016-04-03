package com.example.paciu.belmondo.google;

import android.location.Location;

import com.google.android.gms.maps.LocationSource;

import java.util.HashSet;

/**
 * Created by paciu on 02.04.2016.
 */
public class LocationSourceListenersSet {
    private HashSet<LocationSourceImp> locationSources;

    public LocationSourceListenersSet(){
        locationSources = new HashSet<>();
    }

    public void addLocationSource(LocationSourceImp locationSource){
        locationSources.add(locationSource);
    }

    public void removeLocationSource(LocationSourceImp locationSource){
        locationSources.remove(locationSource);
    }

    public void removeLocationSource(LocationSource.OnLocationChangedListener locationChangedListener){
        LocationSourceImp locationSourceImp = null;
        for(LocationSourceImp l : locationSources){
            if(l.getListener() == locationChangedListener){
                locationSourceImp = l;
            }
        }
        if(locationSourceImp != null){
            removeLocationSource(locationSourceImp);
        }
    }

    public void callAllOnLocationChangedListeners(Location location){
        for(LocationSourceImp l : locationSources){
            callOnLocationChanged(l, location);
        }
    }

    protected void callOnLocationChanged(LocationSourceImp locationSource, Location location){
        if(locationSource != null){
            locationSource.callOnLocationChangedListener(location);
        }
    }

}
