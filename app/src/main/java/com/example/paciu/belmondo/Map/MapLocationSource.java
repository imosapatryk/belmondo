package com.example.paciu.belmondo.Map;

import android.location.Location;

import com.example.paciu.belmondo.Location.LocationChangedListener;
import com.example.paciu.belmondo.Location.LocationObservable;
import com.google.android.gms.maps.LocationSource;

/**
 * Created by paciu on 08.04.2016.
 */
public class MapLocationSource implements LocationChangedListener, LocationSource {

    private LocationObservable locationObservable;
    private OnLocationChangedListener listener;

    public MapLocationSource(LocationObservable subject) {
        this.locationObservable = subject;
        this.locationObservable.registerLocationObserver(this);
    }

    protected void callLocationSourceListenerChanged(Location value){
        if(listener != null){
            listener.onLocationChanged(value);
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        this.listener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        this.listener = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        callLocationSourceListenerChanged(location);
    }
}
