package com.example.paciu.belmondo.DataSource.Profiles.Validators;

import com.example.paciu.belmondo.Validators.StringValidator;
import com.example.paciu.belmondo.Validators.Validator;

/**
 * Created by paciu on 07.03.2016.
 */
public class ProfileNameValidator extends StringValidator {

    public ProfileNameValidator(int minLength, int maxLength) {
        super(minLength, maxLength);
    }

    @Override
    public boolean validate(String value) throws StringEmptyValidationException, StringTooShortValidationException, StringTooLongValidationException {

        checkIfEmpty(value);
        checkIfInRange(value);

        return true;
    }
}
