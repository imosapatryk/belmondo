package com.example.paciu.belmondo.Result;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by paciu on 21.04.2016.
 */
public final class LatLngSerializableImpl implements Serializable {
    public final double latitude;
    public final double longitude;

    public LatLngSerializableImpl(LatLng latLng) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
    }
}
