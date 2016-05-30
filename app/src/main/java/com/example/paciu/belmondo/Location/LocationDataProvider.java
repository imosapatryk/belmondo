package com.example.paciu.belmondo.Location;

import android.location.Location;

/**
 * Created by paciu on 11.05.2016.
 */
public interface LocationDataProvider extends LocationObservable {
    Location getLocation();
}
