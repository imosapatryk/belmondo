package com.example.paciu.belmondo.Discipline;
import org.jscience.physics.amount.Amount;
import javax.measure.quantity.Length;

/**
 * Created by paciu on 10.04.2016.
 */
public interface Discipline {
    String getResourceUsedName();
    String getName();
    Amount<Length> getDisciplineOnMapResolution();
    int getIconResId();
    boolean hasMap();
}
