package com.example.paciu.belmondo.Validators;

/**
 * Created by paciu on 07.03.2016.
 */
public abstract class Validator<T> implements IValidator<T> {

    public abstract static class ValidationException extends Exception{

        public ValidationException(){
            super();
        }

        public ValidationException(String message){
            super(message);
        }
    }
}
