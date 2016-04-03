package com.example.paciu.belmondo.Distance;

/**
 * Created by paciu on 01.04.2016.
 */
public class DistanceInMetres extends Distance{

    public float toKilometres(){
        return getDistance()/1000;
    }

    public double toMiles(){
        return getDistance() * 0.000621;
    }
}
