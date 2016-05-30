package com.example.paciu.belmondo.SportActivity;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Velocity;

/**
 * Created by paciu on 12.05.2016.
 */
public interface SportActivity {
    int getType();
    void setType(int type);
    String getName();
    void setName(String name);
    float getMET();
    void setMET(float MET);
    Amount<Velocity> getUpperSpeedValue();
    void setUpperSpeedValue(Amount<Velocity> upperSpeedValue);
    int getId();
    void setId(int id);
}