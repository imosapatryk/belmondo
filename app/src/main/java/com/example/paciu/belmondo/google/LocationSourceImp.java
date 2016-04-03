package com.example.paciu.belmondo.google;

import android.location.Location;

import com.google.android.gms.maps.LocationSource;

/**
 * Created by paciu on 02.04.2016.
 */
public class LocationSourceImp implements LocationSource {

    private LocationSource.OnLocationChangedListener listener;

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        setListener(onLocationChangedListener);
    }

    @Override
    public void deactivate() {
        setListener(null);
    }

    public void callOnLocationChangedListener(Location location){
        if(listener != null){
            listener.onLocationChanged(location);
        }
    }

    protected LocationSource.OnLocationChangedListener getListener(){
        return listener;
    }

    protected void setListener(OnLocationChangedListener listener) {
        this.listener = listener;
    }
}
