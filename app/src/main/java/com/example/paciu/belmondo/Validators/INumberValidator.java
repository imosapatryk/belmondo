package com.example.paciu.belmondo.Validators;

import com.example.paciu.belmondo.utils.NumberUtils;

/**
 * Created by paciu on 07.03.2016.
 */
public interface INumberValidator<T> extends IValidator<T> {
    @Override
    boolean validate(T value) throws NumberValidator.NumberOutOfRangeException, NumberUtils.NotAcceptableTypeException;
}
