package com.example.paciu.belmondo.SportActivity;

import com.example.paciu.belmondo.SqliteDataSource.Activities.SportActivityModel;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;

/**
 * Created by paciu on 12.05.2016.
 */
public class SportActivityModelAdapter implements SportActivity {
    public SportActivityModel sportActivityModel;

    public SportActivityModelAdapter(SportActivityModel sportActivityModel) {
        this.sportActivityModel = sportActivityModel;
    }

    @Override
    public int getType() {
        return sportActivityModel.getType();
    }

    @Override
    public void setType(int type) {
        sportActivityModel.setType(type);
    }

    @Override
    public String getName() {
        return sportActivityModel.getName();
    }

    @Override
    public void setName(String name) {
        sportActivityModel.setName(name);
    }

    @Override
    public float getMET() {
        return sportActivityModel.getMET();
    }

    @Override
    public void setMET(float MET) {
        sportActivityModel.setMET(MET);
    }

    @Override
    public Amount<Velocity> getUpperSpeedValue() {
        return Amount.valueOf(sportActivityModel.getUpperValueInMPerSecond(), SI.METERS_PER_SECOND);
    }

    @Override
    public void setUpperSpeedValue(Amount<Velocity> upperSpeedValue) {
        sportActivityModel.setUpperValueInMPerSecond((float)upperSpeedValue.doubleValue(SI.METERS_PER_SECOND));
    }

    @Override
    public int getId() {
        return sportActivityModel.getId();
    }

    @Override
    public void setId(int id) {
        sportActivityModel.setId(id);
    }
}
