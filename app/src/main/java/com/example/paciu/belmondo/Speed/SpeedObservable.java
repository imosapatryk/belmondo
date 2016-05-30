package com.example.paciu.belmondo.Speed;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Velocity;

/**
 * Created by paciu on 12.05.2016.
 */
public interface SpeedObservable {
    void addSpeedChangedListener(SpeedChangedListener speedChangedListener);
    void removeSpeedChangedListener(SpeedChangedListener speedChangedListener);
    void notifySpeedChangedListeners(Amount<Velocity> speed);
}
