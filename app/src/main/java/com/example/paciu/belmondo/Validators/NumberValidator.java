package com.example.paciu.belmondo.Validators;

import com.example.paciu.belmondo.utils.NumberUtils;

/**
 * Created by paciu on 07.03.2016.
 */
public abstract class NumberValidator<T extends Number> extends Validator<T> implements INumberValidator<T> {

    private T minValue;
    private T maxValue;

    public NumberValidator(){}

    public NumberValidator(T minValue, T maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    protected void checkNumberInRange(T number) throws NumberUtils.NotAcceptableTypeException, NumberOutOfRangeException {
        if(!NumberUtils.isNumberInRange(number, minValue, maxValue)) throw new NumberOutOfRangeException();
    }

    public static class NumberOutOfRangeException extends Validator.ValidationException {
        public NumberOutOfRangeException(){}

        public NumberOutOfRangeException(String message){
            super(message);
        }
    }
}
