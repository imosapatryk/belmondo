package com.example.paciu.belmondo.speed;

/**
 * Created by paciu on 23.03.2016.
 */
public abstract class Speed {

    float speed;

    public Speed(float speed){
        this.speed = speed;
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float value){
        speed = value;
    }
}
