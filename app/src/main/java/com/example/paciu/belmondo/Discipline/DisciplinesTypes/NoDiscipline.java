package com.example.paciu.belmondo.Discipline.DisciplinesTypes;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.R;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;

/**
 * Created by paciu on 23.04.2016.
 */
public class NoDiscipline implements Discipline {

    @Override
    public String getResourceUsedName() {
        return "NoDiscipline";
    }

    @Override
    public String getName() {
        return "NoDiscipline";
    }

    @Override
    public Amount<Length> getDisciplineOnMapResolution() {
        return Amount.valueOf(0, SI.METER);
    }

    @Override
    public int getIconResId() {
        return R.drawable.no_discipline;
    }

    @Override
    public boolean hasMap() {
        return false;
    }
}
