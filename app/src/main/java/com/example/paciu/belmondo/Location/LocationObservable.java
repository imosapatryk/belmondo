package com.example.paciu.belmondo.Location;

import android.location.Location;

/**
 * Created by paciu on 09.04.2016.
 */
public interface LocationObservable  {
    void registerLocationObserver(LocationChangedListener locationListener);
    void removeLocationObserver(LocationChangedListener locationListener);
    void notifyLocationObservable(Location location);
}
