package com.example.paciu.belmondo.google;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.example.paciu.belmondo.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.LocationSource;

import java.util.HashSet;

/**
 * Created by paciu on 21.03.2016.
 */
public class GoogleLocationProvider implements LocationListener, LocationSourceProvider {

    private LocationRequest locationRequest;
    private ResultCallback<LocationSettingsResult> resultCallback;
    private LocationSourceListenersSet locationSourceSet;
    private OnLocationChangeListener locationChangedListener;

    private Location lastLocation;
    private Context context;

    public GoogleLocationProvider(Context context, GoogleApiClient client, ResultCallback<LocationSettingsResult> resultCallback, OnLocationChangeListener locationChangeListener){
        this.context = context;
        this.resultCallback = resultCallback;
        this.locationChangedListener = locationChangeListener;
        this.locationSourceSet = new LocationSourceListenersSet();
        this.lastLocation = new Location("location provider initialization");
        createAndSetupLocationRequest(client);
        checkLocationSettings(client);
    }

    protected void createAndSetupLocationRequest(GoogleApiClient client){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void checkLocationSettings(GoogleApiClient client) {
        this.checkLocationSettings(client, this.resultCallback);
    }

    protected void checkLocationSettings(GoogleApiClient client, ResultCallback<LocationSettingsResult> resultListener){
        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        PendingResult<LocationSettingsResult> locationSettingsRequestPendingResult = LocationServices.SettingsApi.checkLocationSettings(client, locationSettingsRequest);
        locationSettingsRequestPendingResult.setResultCallback(resultListener);
    }

    public void switchResult(Context context, int resultCode){
        switch(resultCode){
            case Activity.RESULT_OK:{
                Toast.makeText(context, R.string.gps_has_been_enabled, Toast.LENGTH_SHORT).show();
                break;
            }
            case Activity.RESULT_CANCELED:{
                Toast.makeText(context, R.string.gps_unavailable, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void requestLocationUpdates(GoogleApiClient client){
        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        locationSourceSet.callAllOnLocationChangedListeners(location);
        callOnLocationChangedListener(location);
    }

    protected void callOnLocationChangedListener(Location location){
        if(locationChangedListener != null){
            locationChangedListener.onLocationChanged(location);
        }
    }

    public float getSpeedInMetresPerSecond() {
        return lastLocation.getSpeed();
    }

    @Override
    public LocationSource getNewLocationSource(){
        LocationSourceImp locationSourceImp = new LocationSourceImp();
        locationSourceSet.addLocationSource(locationSourceImp);
        return locationSourceImp;
    }

    public Context getContext() {
        return context;
    }

    public interface OnLocationChangeListener {
        void onLocationChanged(Location location);
    }
}

