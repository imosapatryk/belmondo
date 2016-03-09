package com.example.paciu.belmondo.utils;

import com.example.paciu.belmondo.Validators.Validator;

/**
 * Created by paciu on 06.03.2016.
 */
public class NumberUtils {

    public static boolean isNumberInRange(int number, int from, int to){
        return number >= from && number <= to;
    }

    public static boolean isNumberInRange(float number, float from, float to){
        return number >= from && number <= to;
    }

    public static boolean isNumberInRange(double number, double from, double to){
        return number >= from && number <= to;
    }

    public static<T extends Number> boolean isNumberInRange(T number, T from, T to) throws NotAcceptableTypeException {
        if (number instanceof Float){
            return isNumberInRange(number.floatValue(), from.floatValue(), to.floatValue());
        } else if (number instanceof Integer){
            return isNumberInRange(number.intValue(), from.intValue(), to.intValue());
        } else if (number instanceof Double){
            return isNumberInRange(number.doubleValue(), from.doubleValue(), to.doubleValue());
        } else throw new NotAcceptableTypeException("Given type is not acceptable");
    }

    public static class NotAcceptableTypeException extends Validator.ValidationException{
        public NotAcceptableTypeException(){
            super();
        }

        public NotAcceptableTypeException(String message){
            super(message);
        }
    }
}
