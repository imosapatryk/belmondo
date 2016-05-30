package com.example.paciu.belmondo.Discipline.DisciplinesTypes;

import android.content.Context;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.R;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;

/**
 * Created by paciu on 10.04.2016.
 */
public class Running implements Discipline {
    private Context context;
    public Running(Context context) {
        this.context = context;
    }

    @Override
    public String getResourceUsedName() {
        return "running";
    }

    @Override
    public String getName() {
        return context.getString(R.string.running);
    }

    @Override
    public Amount<Length> getDisciplineOnMapResolution() {
        return Amount.valueOf(8, SI.METER);
    }
    @Override
    public int getIconResId() {
        return R.drawable.running;
    }

    @Override
    public boolean hasMap() {
        return true;
    }
}
