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
public class Walking implements Discipline {

    private Context context;

    public Walking(Context context){
        this.context = context;
    }

    @Override
    public String getResourceUsedName() {
        return "walking";
    }

    @Override
    public String getName() {
        return context.getString(R.string.walking);
    }

    @Override
    public Amount<Length> getDisciplineOnMapResolution() {
        return Amount.valueOf(5, SI.METER);
    }

    @Override
    public int getIconResId() {
        return R.drawable.walking;
    }

    @Override
    public boolean hasMap() {
        return true;
    }
}
