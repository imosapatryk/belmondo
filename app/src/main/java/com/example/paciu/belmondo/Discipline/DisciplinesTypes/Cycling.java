package com.example.paciu.belmondo.Discipline.DisciplinesTypes;

import android.content.Context;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.R;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;

/**
 * Created by paciu on 12.04.2016.
 */
public class Cycling implements Discipline {

    private Context context;

    public Cycling(Context context){
        this.context = context;
    }

    @Override
    public String getResourceUsedName() {
        return "cycling";
    }

    @Override
    public String getName() {
        return context.getString(R.string.cycling);
    }

    @Override
    public Amount<Length> getDisciplineOnMapResolution() {
        return Amount.valueOf(12, SI.METER);
    }

    @Override
    public int getIconResId() {
        return R.drawable.cycling;
    }

    @Override
    public boolean hasMap() {
        return true;
    }
}
