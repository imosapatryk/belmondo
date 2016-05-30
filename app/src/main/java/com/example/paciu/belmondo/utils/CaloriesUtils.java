package com.example.paciu.belmondo.Utils;

import com.example.paciu.belmondo.Profile.Sex;

/**
 * Created by paciu on 23.03.2016.
 */
public class CaloriesUtils {
    public static double getBMR(int weightInKilos, int heightInCentimeters, int age, Sex sex){
        switch(sex){
            case MALE: return (13.75 * (float)weightInKilos) + (5 * (float)heightInCentimeters) - (6.76 * age) + 66;
            case FEMALE: return (9.56 * (float)weightInKilos) + (1.85 * (float)heightInCentimeters) - (4.68 * age) + 655;
        }
        return -1;
    }

    public static double getCaloriesBurned(double bmr, float met, float durationInHours){
        return (bmr/24)*met*durationInHours;
    }
}
