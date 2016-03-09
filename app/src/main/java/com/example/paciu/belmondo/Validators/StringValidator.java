package com.example.paciu.belmondo.Validators;

/**
 * Created by paciu on 07.03.2016.
 */
public abstract class StringValidator extends Validator<String> implements IStringValidator {

    private int minLength = -1;
    private int maxLength = -1;

    public StringValidator(){}

    public StringValidator(int minLength, int maxLength){
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    protected void checkIfEmpty(String value) throws StringEmptyValidationException {
        if(value.isEmpty()) throw new StringEmptyValidationException();
    }

    protected void checkIfInRange(String value) throws StringTooShortValidationException, StringTooLongValidationException {
        if(minLength != -1 && value.length() < minLength) throw new StringTooShortValidationException();
        if(maxLength != -1 && value.length() > maxLength) throw new StringTooLongValidationException();
    }


    public static class StringEmptyValidationException extends ValidationException {

        public StringEmptyValidationException(){
            super();
        }

        public StringEmptyValidationException(String message){
            super(message);
        }
    }

    public static class StringTooLongValidationException extends ValidationException {

        public StringTooLongValidationException(){
            super();
        }

        public StringTooLongValidationException(String message){
            super(message);
        }
    }

    public static class StringTooShortValidationException extends ValidationException {

        public StringTooShortValidationException(){
            super();
        }

        public StringTooShortValidationException(String message){
            super(message);
        }
    }
}
