package com.example.paciu.belmondo.ViewsExtends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;

import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.Speed.SpeedChangedListener;
import com.example.paciu.belmondo.Speed.SpeedObservable;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Created by paciu on 23.03.2016.
 */
public class SpeedCardView extends CardViewForDisplayStatFeature implements View.OnClickListener, SpeedChangedListener{

    int whichSpeedUnit;
    private SpeedObservable speedObservable;
    private Amount<Velocity> speed;

    public SpeedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTitleTextViewClickListener(this);
        speed = Amount.valueOf(0, SI.METERS_PER_SECOND);
    }

    public void setSpeedObservable(SpeedObservable observable){
        if(speedObservable != null){
            speedObservable.removeSpeedChangedListener(this);
        }
        speedObservable = observable;

        if(speedObservable != null) {
            speedObservable.addSpeedChangedListener(this);
        }
    }

    protected void setOnTitleTextViewClickListener(OnClickListener listener){
        if(titleTextView != null){
            titleTextView.setOnClickListener(listener);
        }
    }

    protected void setSpeed(Amount<Velocity> speed){
        this.speed = speed;
        updateContentText(false);
    }

    public void updateContentText(boolean init){
        String [] unitArray = getContext().getResources().getStringArray(R.array.speedUnits);
        String unit = unitArray.length > whichSpeedUnit && whichSpeedUnit > 0 ? unitArray[whichSpeedUnit] : unitArray[0];
        if(init)
            this.setContentText("- " + unit);
        else
            this.setContentText(String.format("%1$.1f", (float)speed.doubleValue((Unit<Velocity>) Unit.valueOf(unit)) ) + " " + unit);
    }

    @Override
    public void onClick(View v) {
        if(v == titleTextView){
            showChooseUnitDialog();
        }
    }

    public void setDisplayUnit(int which){
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
                       setSpeed(speed);
                    }
                }).setTitle(R.string.choose_speed_unit).show();
    }

    @Override
    public void onSpeedChanged(Amount<Velocity> speed) {
        setSpeed(speed);
    }
}
