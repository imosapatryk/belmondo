package com.example.paciu.belmondo;

/**
 * Created by paciu on 04.03.2016.
 */
public class NoSuchProfileException extends NoSuchObjectException{
    public NoSuchProfileException(){
        super("Profile with given name does not exist!");
    }
}
