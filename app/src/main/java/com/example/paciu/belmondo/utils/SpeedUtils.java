package com.example.paciu.belmondo.Utils;

/**
 * Created by paciu on 23.03.2016.
 */
public class SpeedUtils {

    public static float MetresPerSecondToKilometresPerHour(float metresPerSecond){
        return metresPerSecond * 3.6f;
    }

    public static float KilometresPerHourToMetresPerSecond(float kilometresPerHour){
        return kilometresPerHour / 3.6f;
    }

    public static float MetresPerSecondToMilesPerHour(float metresPerSecond){
        return MetresPerSecondToKilometresPerHour(metresPerSecond) * 1.609344f;
    }

    public static float MilesPerHourToMetresPerSecond(float milesPerHour){
        return KilometresPerHourToMetresPerSecond(MilesPerHourToKilometresPerHour(milesPerHour));
    }

    public static float KilometresPerHourToMilesPerHour(float kilometresPerHour){
        return kilometresPerHour * 1.609344f;
    }

    public static float MilesPerHourToKilometresPerHour(float milesPerHour){
        return milesPerHour / 1.609344f;
    }


}
