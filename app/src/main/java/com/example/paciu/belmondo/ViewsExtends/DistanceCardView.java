package com.example.paciu.belmondo.ViewsExtends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;

import com.example.paciu.belmondo.Distance.DistanceChangedListener;
import com.example.paciu.belmondo.Distance.DistanceObservable;
import com.example.paciu.belmondo.R;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Created by paciu on 01.04.2016.
 */
public class DistanceCardView  extends CardViewForDisplayStatFeature  implements View.OnClickListener, DistanceChangedListener{

    private DistanceObservable distanceObservable;
    private Amount<Length> distance;
    private int whichDistanceUnit;

    public DistanceCardView(Context context) {
        super(context);
        setup();
    }

    public DistanceCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public DistanceCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    protected void setup(){
        distance = Amount.valueOf(0, SI.METER);
        setOnTitleTextViewClickListener(this);
    }

    public void setDistance(Amount<Length> distance){
        this.distance = distance;
        updateContentText(false);
    }

    public void updateContentText(boolean init){
        String [] unitArray = getContext().getResources().getStringArray(R.array.distanceUnit);
        String unit = unitArray.length > whichDistanceUnit && whichDistanceUnit > 0 ? unitArray[whichDistanceUnit] : unitArray[0];
        if(init)
            this.setContentText("- " + unit);
        else {
            Unit<Length> lengthUnit = ((Unit<Length>) Unit.valueOf(unit));
            this.setContentText(String.format("%1$.2f", (float) distance.doubleValue(lengthUnit)) + " " + unit);
        }
    }

    protected void setOnTitleTextViewClickListener(OnClickListener listener){
        if(titleTextView != null){
            titleTextView.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == titleTextView){
            showChooseUnitDialog();
        }
    }

    public void setDisplayUnit(int which){
        whichDistanceUnit = which;
    }

    protected void showChooseUnitDialog(){
        new AlertDialog.Builder(getContext()).setSingleChoiceItems(R.array.distanceUnit, whichDistanceUnit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichDistanceUnit = which;
            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setDistance(distance);
                    }
                }).setTitle(R.string.choose_distance_unit).show();
    }

    public void setDistanceObservable(DistanceObservable observable){
        if(distanceObservable != null){
            distanceObservable.removeDistanceListener(this);
        }
        distanceObservable = observable;
        if(distanceObservable != null) {
            distanceObservable.addDistanceListener(this);
        }
    }

    @Override
    public void onDistanceChanged(Amount<Length> distance) {
        setDistance(distance);
    }
}
