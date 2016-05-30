package com.example.paciu.belmondo.Location;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by paciu on 11.05.2016.
 */
public class GoogleLocationRequester {
    private LocationListener locationListener;
    private LocationRequest locationRequest;
    private ResultCallback<LocationSettingsResult> resultCallback;

    public GoogleLocationRequester(GoogleApiClient apiClient, ResultCallback<LocationSettingsResult> resultCallback, LocationListener locationListener, LocationRequest locationRequest){
        this.locationListener = locationListener;
        this.locationRequest = locationRequest;
        this.resultCallback = resultCallback;
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


}
