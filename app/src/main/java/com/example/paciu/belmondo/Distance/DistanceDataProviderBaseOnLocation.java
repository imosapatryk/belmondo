package com.example.paciu.belmondo.Distance;

import android.location.Location;

import com.example.paciu.belmondo.Location.LocationChangedListener;
import com.example.paciu.belmondo.Location.LocationObservable;

import org.jscience.physics.amount.Amount;

import java.util.HashSet;
import java.util.Set;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Created by paciu on 11.05.2016.
 */
public class DistanceDataProviderBaseOnLocation implements DistanceDataProvider, LocationChangedListener{
    private Amount<Length> distance;
    private Amount<Length> resolution;
    private Set<DistanceChangedListener> listenerSet;
    private Location lastLocation;
    private boolean enabled;

    public DistanceDataProviderBaseOnLocation(LocationObservable locationObservable, Amount<Length> resolution) {
        distance = Amount.valueOf(0, SI.METER);
        this.resolution = resolution;
        listenerSet = new HashSet<>();
        enabled = true;
        locationObservable.registerLocationObserver(this);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean e) {
        this.enabled = e;
    }

    @Override
    public Amount<Length> getDistance() {
        return distance;
    }

    @Override
    public Amount<Length> getResolution() {
        return resolution;
    }

    @Override
    public void setResoultion(Amount<Length> resolution) {
        if(resolution.compareTo(Amount.valueOf(0, SI.METER)) < 0) throw new IllegalArgumentException("Resoultion cannot be less than 0");
    }

    @Override
    public void addDistanceListener(DistanceChangedListener listener) {
        listenerSet.add(listener);
    }

    @Override
    public void removeDistanceListener(DistanceChangedListener listener) {
        listenerSet.remove(listener);
    }

    @Override
    public void notifyDistanceListeners(Amount<Length> distance) {
        for(DistanceChangedListener l : listenerSet){
            if(l != null){
                l.onDistanceChanged(distance);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if(isEnabled()){
            if(lastLocation == null && location != null){
                lastLocation = location;
            } else {
                Amount<Length> distanceFromLastLocation = getDistanceBetween(lastLocation, location);
                if(distanceFromLastLocation.compareTo(resolution) > 0){
                    distance = Amount.valueOf(distance.doubleValue(SI.METER) + distanceFromLastLocation.doubleValue(SI.METER), SI.METER);
                    notifyDistanceListeners(distance);
                    lastLocation = location;
                }
            }
        } else if(location != null){
            lastLocation = location;
        }
    }

    protected Amount<Length> getDistanceBetween(Location a, Location b){
        return Amount.valueOf(a.distanceTo(b), SI.METER);
    }
}
