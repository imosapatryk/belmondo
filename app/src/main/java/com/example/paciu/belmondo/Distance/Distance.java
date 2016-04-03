package com.example.paciu.belmondo.Distance;

/**
 * Created by paciu on 01.04.2016.
 */
public abstract class Distance {
    private float distance = 0;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void addToDistance(float distance){
        this.distance += distance;
    }
}
