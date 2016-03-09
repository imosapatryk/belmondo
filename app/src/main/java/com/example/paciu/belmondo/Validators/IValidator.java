package com.example.paciu.belmondo.Validators;

import java.text.ParseException;

/**
 * Created by paciu on 07.03.2016.
 */
public interface IValidator<T> {
    public boolean validate(T value) throws Validator.ValidationException, ParseException;
}
