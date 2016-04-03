package com.example.paciu.belmondo.speed;

import com.example.paciu.belmondo.speed.Speed;

/**
 * Created by paciu on 23.03.2016.
 */
public class SpeedInMetresPerSecond extends Speed {

    public SpeedInMetresPerSecond(float speedInMetresPerSecond) {
        super(speedInMetresPerSecond);
    }

    public float toKilometresPerHour(){
        return getSpeed() * 3.6f;
    }

    public float toMilesPerHour(){
        return toKilometresPerHour() * 1.609344f;
    }
}
