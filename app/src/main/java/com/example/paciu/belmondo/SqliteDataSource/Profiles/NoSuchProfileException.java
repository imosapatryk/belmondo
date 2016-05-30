package com.example.paciu.belmondo.SqliteDataSource.Profiles;

import com.example.paciu.belmondo.SqliteDataSource.NoSuchObjectException;

/**
 * Created by paciu on 04.03.2016.
 */
public class NoSuchProfileException extends NoSuchObjectException {
    public NoSuchProfileException(){
        super("Profile with given name does not exist!");
    }
}
