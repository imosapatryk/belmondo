package com.example.paciu.belmondo.Result;

import com.example.paciu.belmondo.SqliteDataSource.Results.ResultModel;
import com.google.android.gms.maps.model.LatLng;

import org.jscience.physics.amount.Amount;

import java.util.List;

import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;

/**
 * Created by paciu on 12.05.2016.
 */
public class ResultModelAdapter implements Result {
    private ResultModel resultModel;

    public ResultModelAdapter(ResultModel resultModel) {
        this.resultModel = resultModel;
    }

    @Override
    public int getUserId() {
        return resultModel.getUserId();
    }

    @Override
    public void setUserId(int userId) {
        resultModel.setUserId(userId);
    }

    @Override
    public int getId() {
        return resultModel.getId();
    }

    @Override
    public void setId(int id) {
        resultModel.setId(id);
    }

    @Override
    public String getDisciplineName() {
        return resultModel.getDisciplineName();
    }

    @Override
    public void setDisciplineName(String disciplineName) {
        resultModel.setDisciplineName(disciplineName);
    }

    @Override
    public Amount<Velocity> getMaxSpeed() {
        return Amount.valueOf(resultModel.getMaxSpeed(), SI.METERS_PER_SECOND);
    }

    @Override
    public void setMaxSpeed(Amount<Velocity> maxSpeed) {
        resultModel.setMaxSpeed((float)maxSpeed.doubleValue(SI.METERS_PER_SECOND));
    }

    @Override
    public Amount<Velocity> getAvgSpeed() {
        return Amount.valueOf(resultModel.getAvgSpeed(), SI.METERS_PER_SECOND);
    }

    @Override
    public void setAvgSpeed(Amount<Velocity> avgSpeed) {
        resultModel.setAvgSpeed((float)avgSpeed.doubleValue(SI.METERS_PER_SECOND));
    }

    @Override
    public Amount<Length> getDistance() {
        return Amount.valueOf(resultModel.getDistance(), SI.METER);
    }

    @Override
    public void setDistance(Amount<Length> distance) {
        resultModel.setDistance((float)distance.doubleValue(SI.METER));
    }

    @Override
    public int getCalories() {
        return resultModel.getCalories();
    }

    @Override
    public void setCalories(int calories) {
        resultModel.setCalories(calories);
    }

    @Override
    public String getDuration() {
        return resultModel.getDuration();
    }

    @Override
    public void setDuration(String duration) {
        resultModel.setDuration(duration);
    }

    @Override
    public String getDateTime() {
        return resultModel.getDateTime();
    }

    @Override
    public void setDateTime(String dateTime) {
        resultModel.setDateTime(dateTime);
    }

    @Override
    public List<LatLngSerializableImpl> getMapCoords() {
        return resultModel.getMapCoords();
    }

    @Override
    public void setMapCoords(List<LatLngSerializableImpl> map) {
        resultModel.setMapCoords(map);
    }

    @Override
    public void setMapCoords(List<LatLng> map, boolean setNullIfMapNull) {
        resultModel.setMapCoords(map, setNullIfMapNull);
    }
}
