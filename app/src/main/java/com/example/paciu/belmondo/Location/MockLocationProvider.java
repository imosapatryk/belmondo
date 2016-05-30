package com.example.paciu.belmondo.Location;

import android.location.Location;
import android.os.Looper;

import com.example.paciu.belmondo.Timer.TimeParts;
import com.example.paciu.belmondo.Timer.Timer;
import com.example.paciu.belmondo.Timer.TimerListener;
import com.example.paciu.belmondo.Timer.TimerObservable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paciu on 30.05.2016.
 */
public class MockLocationProvider implements LocationDataProvider {

    private Location l;
    private Set<LocationChangedListener> locationChangedListenerSet;

    public MockLocationProvider() {
        l = new Location("");
        l.setLongitude(30);
        l.setLatitude(30);
        l.setSpeed(4);
        locationChangedListenerSet = new HashSet<>();
        new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
              postAddLongitude();
            }
        }, 1000);
    }

    private void postAddLongitude(){
        new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                l.setLongitude(l.getLongitude() + 0.000017);
                notifyLocationObservable(new Location(l));
                postAddLongitude();
            }
        }, 1000);
    }

    @Override
    public Location getLocation() {
        return l;
    }

    @Override
    public void registerLocationObserver(LocationChangedListener locationListener) {
        locationChangedListenerSet.add(locationListener);
    }

    @Override
    public void removeLocationObserver(LocationChangedListener locationListener) {
        locationChangedListenerSet.remove(locationListener);
    }

    @Override
    public void notifyLocationObservable(Location location) {
        for (LocationChangedListener lis : locationChangedListenerSet) {
            if (lis != null) {
                lis.onLocationChanged(location);
            }
        }
    }

}