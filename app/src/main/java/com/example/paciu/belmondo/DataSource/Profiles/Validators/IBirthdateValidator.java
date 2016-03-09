package com.example.paciu.belmondo.DataSource.Profiles.Validators;

import com.example.paciu.belmondo.DataSource.Profiles.Validators.BirthdateValidator;
import com.example.paciu.belmondo.Validators.IValidator;

import java.text.ParseException;

/**
 * Created by paciu on 07.03.2016.
 */
public interface IBirthdateValidator extends IValidator<String> {
    @Override
    boolean validate(String value) throws BirthdateValidator.DateInTheFutureException, ParseException;
}
