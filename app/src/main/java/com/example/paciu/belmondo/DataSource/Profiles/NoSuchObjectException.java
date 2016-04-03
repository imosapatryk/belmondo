package com.example.paciu.belmondo.DataSource.Profiles;

/**
 * Created by paciu on 06.03.2016.
 */
public abstract class NoSuchObjectException extends Exception {
    public NoSuchObjectException(String detailMessage) {
        super(detailMessage);
    }
}
