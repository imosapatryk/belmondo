package com.example.paciu.belmondo.Validators;

import com.example.paciu.belmondo.DataSource.Profiles.Validators.BirthdateValidator;
import com.example.paciu.belmondo.Validators.StringValidator.*;

import java.text.ParseException;

/**
 * Created by paciu on 07.03.2016.
 */
public interface IStringValidator extends IValidator<String> {
    @Override
    boolean validate(String object) throws StringEmptyValidationException, StringTooShortValidationException, StringTooLongValidationException, ParseException, BirthdateValidator.DateInTheFutureException;
}
