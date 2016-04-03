package com.example.paciu.belmondo.ViewsExtends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;

import com.example.paciu.belmondo.Distance.DistanceCounter;
import com.example.paciu.belmondo.Distance.DistanceInMetres;
import com.example.paciu.belmondo.R;

/**
 * Created by paciu on 01.04.2016.
 */
public class DistanceCardView  extends CardViewForDisplayStatFeature  implements View.OnClickListener{

    private DistanceInMetres distanceInMetres;
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
        distanceInMetres = new DistanceInMetres();
        setOnTitleTextViewClickListener(this);
    }

    public void setDistanceInMetres(float value){
        this.distanceInMetres.setDistance(value);

        String [] unitArray = getContext().getResources().getStringArray(R.array.distanceUnit);
        String unit = unitArray.length > whichDistanceUnit && whichDistanceUnit > 0 ? unitArray[whichDistanceUnit] : unitArray[0];
        this.setContentText(String.format("%1$.2f", switchSpeedInUnit(whichDistanceUnit)) + " " + unit);
    }

    protected void setOnTitleTextViewClickListener(OnClickListener listener){
        if(titleTextView != null){
            titleTextView.setOnClickListener(listener);
        }
    }

    protected float switchSpeedInUnit(int whichInUnitsArray){
        switch (whichInUnitsArray){
            case 0:{
                return distanceInMetres.toKilometres();
            }
            case 1:{
                return (float) distanceInMetres.toMiles();
            }
            default: return distanceInMetres.toKilometres();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == titleTextView){
            showChooseUnitDialog();
        }
    }

    protected void setDisplayUnit(int which){
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
                        setDistanceInMetres(distanceInMetres.getDistance());
                    }
                }).setTitle(R.string.choose_distance_unit).show();
    }
}
