package com.example.paciu.belmondo.google;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;

/**
 * Created by paciu on 02.04.2016.
 */
public class MapDrawerBasedOnLocationSource extends MapDrawer implements LocationSource.OnLocationChangedListener {

    private LocationSource locationSource;

    public MapDrawerBasedOnLocationSource(Location firstLocation, GoogleMap googleMap, LocationSource locationSource) {
        super(firstLocation, googleMap);
        this.locationSource = locationSource;

        this.locationSource.activate(this);
    }

    public LocationSource getLocationSource() {
        return locationSource;
    }

    public void setLocationSource(LocationSource locationSource) {
        this.locationSource = locationSource;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.draw(location);
    }
}
