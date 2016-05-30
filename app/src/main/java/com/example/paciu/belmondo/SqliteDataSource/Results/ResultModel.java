package com.example.paciu.belmondo.SqliteDataSource.Results;

import com.example.paciu.belmondo.Result.LatLngSerializableImpl;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 10.04.2016.
 */
public class ResultModel implements Serializable{
    private int userId;
    private int id;
    private String dateTime;
    private String duration;
    private String disciplineName;
    private float maxSpeed;
    private float avgSpeed;
    private float distance;
    private int calories;
    private List<LatLngSerializableImpl> mapCoords;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<LatLngSerializableImpl> getMapCoords() {
        return mapCoords;
    }

    public void setMapCoords(List<LatLngSerializableImpl> map) {
        this.mapCoords = map;
    }

    public void setMapCoords(List<LatLng> map, boolean setNullIfMapNull){
        if(map != null) {
            List<LatLngSerializableImpl> latLngSerializables = new ArrayList<>();
            for (LatLng ll : map) {
                latLngSerializables.add(new LatLngSerializableImpl(ll));
            }
            this.mapCoords = latLngSerializables;
        } else if(setNullIfMapNull){
            this.mapCoords = null;
        }
    }
}
