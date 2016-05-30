package com.example.paciu.belmondo.SqliteDataSource.Results;

import com.example.paciu.belmondo.SqliteDataSource.NoSuchObjectException;

/**
 * Created by paciu on 10.04.2016.
 */
public class NoSuchResultException extends NoSuchObjectException {
    public NoSuchResultException(String detailMessage) {
        super(detailMessage);
    }
}
