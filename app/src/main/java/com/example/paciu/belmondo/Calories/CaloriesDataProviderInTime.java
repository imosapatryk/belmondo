package com.example.paciu.belmondo.Calories;

import android.os.Handler;
import android.os.Looper;

import com.example.paciu.belmondo.Profile.Profile;
import com.example.paciu.belmondo.Speed.SpeedDataProvider;
import com.example.paciu.belmondo.SportActivity.SportActivity;
import com.example.paciu.belmondo.Timer.TimeParts;
import com.example.paciu.belmondo.Timer.TimerListener;
import com.example.paciu.belmondo.Timer.TimerObservable;
import com.example.paciu.belmondo.Utils.CaloriesUtils;
import com.example.paciu.belmondo.Utils.DateUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.measure.unit.SI;

/**
 * Created by paciu on 12.05.2016.
 */
public class CaloriesDataProviderInTime implements CaloriesDataProvider, TimerListener{

    private boolean enabled;
    private double calories;
    private Set<CaloriesChangedListener> caloriesChangedListenerSet;
    private SpeedDataProvider speedDataProvider;
    private List<SportActivity> sportActivities;
    private Profile profile;

    public CaloriesDataProviderInTime(Profile profile, List<SportActivity> sportActivities, SpeedDataProvider speedDataProvider, TimerObservable timerObservable){
        caloriesChangedListenerSet = new HashSet<>();
        this.profile = profile;
        this.sportActivities = sportActivities;
        this.speedDataProvider = speedDataProvider;
        calories = 0;
        enabled = true;
        timerObservable.addTimerListener(this);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int getCalories() {
        return (int)calories;
    }

    @Override
    public Profile getProfile() {
        return profile;
    }

    @Override
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void addCaloriesListener(CaloriesChangedListener caloriesListener) {
        caloriesChangedListenerSet.add(caloriesListener);
    }

    @Override
    public void removeCaloriesListener(CaloriesChangedListener caloriesListener) {
        caloriesChangedListenerSet.remove(caloriesListener);
    }

    @Override
    public void notifyCaloriesObservable(int calories) {
        if(isEnabled()){
            for(CaloriesChangedListener l : caloriesChangedListenerSet){
                if(l != null){
                    l.onCaloriesChanged(calories);
                }
            }
        }
    }

    @Override
    public void onTimerStarted(TimeParts timeParts) {

    }

    @Override
    public void onTimerStopped(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onTimerPaused(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onTimerResumed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void on100MilisElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onSecondElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                double caloriesAmount = (getBMR() / 24) * getMET() * 1.f/3600;
                if(caloriesAmount != 0) {
                    calories += caloriesAmount;
                    notifyCaloriesObservable((int)calories);
                }
            }
        });
    }

    @Override
    public void onMinuteElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onHourElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onNewIntervalCreated(TimeParts lastTimeInterval) {

    }

    protected  double getBMR(){
        return CaloriesUtils.getBMR(profile.getWeight(), profile.getHeight(), DateUtils.getAge(profile.getBirthDate(), "dd/MM/yyyy"), profile.getSex());
    }

    protected double getMET() {
        double speed = speedDataProvider.getSpeed().doubleValue(SI.METERS_PER_SECOND);
        if (speed < 0.5) return 0;
        int i = 0;
        for (; i < sportActivities.size(); i++) {
            if (sportActivities.get(i).getUpperSpeedValue().doubleValue(SI.METERS_PER_SECOND) >= speed) {
                break;
            }
        }
        if (sportActivities.size() > 0) {
            if (i == sportActivities.size()) {
                return sportActivities.get(i - 1).getMET();
            } else {
                return sportActivities.get(i).getMET();
            }
        }
        return 0;
    }
}
