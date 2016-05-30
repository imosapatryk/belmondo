package com.example.paciu.belmondo.Distance;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;

/**
 * Created by paciu on 11.05.2016.
 */
public interface DistanceChangedListener {
    void onDistanceChanged(Amount<Length> lengthAmount);
}
