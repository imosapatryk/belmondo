package com.example.paciu.belmondo.DataSource;

import com.example.paciu.belmondo.NoSuchObjectException;

import java.util.List;

/**
 * Created by paciu on 06.03.2016.
 */
public interface IDataSource<T>{

    String getTableName();

    List<T> getAll();

    T getById(int id) throws NoSuchObjectException;

    long insertNew(T object);

    long insertNewWithOpenAndCloseDB(T object);


}
