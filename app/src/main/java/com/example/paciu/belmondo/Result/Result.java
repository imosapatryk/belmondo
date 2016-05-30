package com.example.paciu.belmondo.Result;

import com.google.android.gms.maps.model.LatLng;

import org.jscience.physics.amount.Amount;

import java.io.Serializable;
import java.util.List;

import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;

/**
 * Created by paciu on 12.05.2016.
 */
public interface Result extends Serializable {
    int getUserId();
    void setUserId(int userId);
    int getId();
    void setId(int id);
    String getDisciplineName();
    void setDisciplineName(String disciplineName);
    Amount<Velocity> getMaxSpeed();
    void setMaxSpeed(Amount<Velocity> maxSpeed);
    Amount<Velocity> getAvgSpeed();
    void setAvgSpeed(Amount<Velocity> avgSpeed);
    Amount<Length> getDistance();
    void setDistance(Amount<Length> distance);
    int getCalories();
    void setCalories(int calories);
    String getDuration();
    void setDuration(String duration);
    String getDateTime();
    void setDateTime(String dateTime);
    List<LatLngSerializableImpl> getMapCoords();
    void setMapCoords(List<LatLngSerializableImpl> map);
    void setMapCoords(List<LatLng> map, boolean setNullIfMapNull);
}
