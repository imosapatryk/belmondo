package com.example.paciu.belmondo.Profile;

/**
 * Created by paciu on 14.04.2016.
 */
public enum Sex {
    MALE,
    FEMALE;

    public int toInt(){
        switch (this){
            case MALE: return 0;
            default: return 1;
        }
    }

    public static Sex fromInt(int i){
        switch (i){
            case 0: return MALE;
            default: return FEMALE;
        }
    }
}
