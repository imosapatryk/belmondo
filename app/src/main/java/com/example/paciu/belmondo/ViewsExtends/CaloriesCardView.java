package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.util.AttributeSet;

import com.example.paciu.belmondo.Calories.CaloriesChangedListener;
import com.example.paciu.belmondo.Calories.CaloriesObservable;

/**
 * Created by paciu on 10.05.2016.
 */
public class CaloriesCardView extends CardViewForDisplayStatFeature implements CaloriesChangedListener{
    private CaloriesObservable caloriesObservable;

    public CaloriesCardView(Context context) {
        super(context);
    }

    public CaloriesCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CaloriesCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCaloriesObservable(CaloriesObservable observable){
        if(caloriesObservable != null){
            caloriesObservable.removeCaloriesListener(this);
        }
        caloriesObservable = observable;

        if(caloriesObservable != null) {
            caloriesObservable.addCaloriesListener(this);
        }
    }

    @Override
    public void onCaloriesChanged(int calories) {
        setContentText(String.format("%1$d", (int)calories));
    }
}
