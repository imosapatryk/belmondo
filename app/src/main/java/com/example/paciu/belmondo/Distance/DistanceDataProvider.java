package com.example.paciu.belmondo.Distance;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;

/**
 * Created by paciu on 11.05.2016.
 */
public interface DistanceDataProvider extends DistanceObservable{
    boolean isEnabled();
    void setEnabled(boolean enabled);
    Amount<Length> getDistance();
    Amount<Length> getResolution();
    void setResoultion(Amount<Length> resolution);

}
