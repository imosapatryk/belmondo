package com.example.paciu.belmondo.ViewsExtends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;

import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.speed.SpeedInMetresPerSecond;

/**
 * Created by paciu on 23.03.2016.
 */
public class SpeedCardView extends CardViewForDisplayStatFeature implements View.OnClickListener{

    SpeedInMetresPerSecond speedInMetresPerSecond;
    int whichSpeedUnit;

    public SpeedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        speedInMetresPerSecond = new SpeedInMetresPerSecond(0);

        setOnTitleTextViewClickListener(this);

    }

    protected void setOnTitleTextViewClickListener(OnClickListener listener){
        if(titleTextView != null){
            titleTextView.setOnClickListener(listener);
        }
    }

    public SpeedInMetresPerSecond getSpeedInMetresPerSecond(){
        return speedInMetresPerSecond;
    }

    public void setSpeedInMetresPerSecond(float value){
        this.speedInMetresPerSecond.setSpeed(value);

        String [] unitArray = getContext().getResources().getStringArray(R.array.speedUnits);
        String unit = unitArray.length > whichSpeedUnit && whichSpeedUnit > 0 ? unitArray[whichSpeedUnit] : unitArray[0];
        this.setContentText(String.format("%1$.1f", switchSpeedInUnit(whichSpeedUnit)) + " " + unit);
    }

    protected float switchSpeedInUnit(int whichInUnitsArray){
        switch (whichInUnitsArray){
            case 0:{
                return speedInMetresPerSecond.toKilometresPerHour();
            }
            case 1:{
                return speedInMetresPerSecond.toMilesPerHour();
            }
            default: return speedInMetresPerSecond.toKilometresPerHour();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == titleTextView){
            showChooseUnitDialog();
        }
    }

    protected void setDisplayUnit(int which){
        whichSpeedUnit = which;
    }

    protected void showChooseUnitDialog(){
        new AlertDialog.Builder(getContext()).setSingleChoiceItems(R.array.speedUnits, whichSpeedUnit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichSpeedUnit = which;
            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setSpeedInMetresPerSecond(speedInMetresPerSecond.getSpeed());
                    }
                }).setTitle(R.string.choose_speed_unit).show();
    }
}
