package com.example.paciu.belmondo.Calories;

/**
 * Created by paciu on 10.05.2016.
 */
public interface CaloriesObservable {
    void addCaloriesListener(CaloriesChangedListener caloriesListener);
    void removeCaloriesListener(CaloriesChangedListener caloriesListener);
    void notifyCaloriesObservable(int calories);
}
