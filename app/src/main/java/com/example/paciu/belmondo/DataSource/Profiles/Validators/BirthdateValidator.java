package com.example.paciu.belmondo.DataSource.Profiles.Validators;

import com.example.paciu.belmondo.utils.DateUtils;
import com.example.paciu.belmondo.Validators.StringValidator;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by paciu on 07.03.2016.
 */
public class BirthdateValidator extends StringValidator {

    private String format;

    public BirthdateValidator(String format){
        this.format = format;

    }

    protected void checkIfDateBeforeNow(Date date) throws DateInTheFutureException {
        boolean isDateBeforeNow = DateUtils.isDateBeforeNow(date);
        if(!isDateBeforeNow) throw new DateInTheFutureException("Birthdate cannot be in the future!");
    }

    @Override
    public boolean validate(String value) throws ParseException, DateInTheFutureException {
        Date birthdate = DateUtils.parseDateWithFormat(value, format);
        checkIfDateBeforeNow(birthdate);
        return true;
    }

    public static class DateInTheFutureException extends ValidationException{
        public DateInTheFutureException() {
            super();
        }

        public DateInTheFutureException(String message) {
            super(message);
        }
    }
}
