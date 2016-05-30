package com.example.paciu.belmondo.SqliteDataSource.Activities;

import com.example.paciu.belmondo.SqliteDataSource.NoSuchObjectException;

/**
 * Created by paciu on 10.05.2016.
 */
public class NoSuchActivityException extends NoSuchObjectException {
    public NoSuchActivityException(String detailMessage) {
        super(detailMessage);
    }
}
