package com.example.paciu.belmondo.Speed;

import android.location.Location;

import com.example.paciu.belmondo.Location.LocationChangedListener;
import com.example.paciu.belmondo.Location.LocationObservable;

import org.jscience.physics.amount.Amount;

import java.util.HashSet;
import java.util.Set;

import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;

/**
 * Created by paciu on 12.05.2016.
 */
public class SpeedDataProviderBasedOnLocation implements SpeedDataProvider, LocationChangedListener {
    private boolean enabled;
    private Amount<Velocity> speed;
    private Amount<Velocity> maxSpeed;
    private Set<SpeedChangedListener> speedChangedListenerSet;

    public SpeedDataProviderBasedOnLocation(LocationObservable locationObservable){
        locationObservable.registerLocationObserver(this);
        speed = Amount.valueOf(0, SI.METERS_PER_SECOND);
        maxSpeed = Amount.valueOf(0, SI.METERS_PER_SECOND);
        speedChangedListenerSet = new HashSet<>();
        enabled = true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Amount<Velocity> getSpeed() {
        return speed;
    }

    @Override
    public Amount<Velocity> getMaxSpeed() {
        return maxSpeed;
    }


    @Override
    public void addSpeedChangedListener(SpeedChangedListener speedChangedListener) {
        speedChangedListenerSet.add(speedChangedListener);
    }

    @Override
    public void removeSpeedChangedListener(SpeedChangedListener speedChangedListener) {
        speedChangedListenerSet.remove(speedChangedListener);
    }

    @Override
    public void notifySpeedChangedListeners(Amount<Velocity> speed) {
        for(SpeedChangedListener l : speedChangedListenerSet){
            if(l != null){
                l.onSpeedChanged(speed);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null && isEnabled()){
            speed = Amount.valueOf(location.getSpeed(), SI.METERS_PER_SECOND);
            if(speed.compareTo(maxSpeed) > 0) {
                maxSpeed = Amount.valueOf(location.getSpeed(), SI.METERS_PER_SECOND);
            }
            notifySpeedChangedListeners(speed);
        }
    }
}
