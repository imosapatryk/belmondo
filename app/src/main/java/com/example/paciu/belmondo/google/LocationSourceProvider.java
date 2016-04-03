package com.example.paciu.belmondo.google;

import com.google.android.gms.maps.LocationSource;

/**
 * Created by paciu on 02.04.2016.
 */
public interface LocationSourceProvider {
    LocationSource getNewLocationSource();
}
