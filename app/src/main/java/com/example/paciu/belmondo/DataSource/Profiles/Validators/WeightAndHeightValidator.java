package com.example.paciu.belmondo.DataSource.Profiles.Validators;

import com.example.paciu.belmondo.utils.NumberUtils;
import com.example.paciu.belmondo.Validators.NumberValidator;

/**
 * Created by paciu on 07.03.2016.
 */
public class WeightAndHeightValidator extends NumberValidator<Float> {

    public WeightAndHeightValidator(Float minValue, Float maxValue) {
        super(minValue, maxValue);
    }

    @Override
    public boolean validate(Float value) throws NumberUtils.NotAcceptableTypeException, NumberOutOfRangeException{
        checkNumberInRange(value);
        return true;
    }
}
