package com.example.paciu.belmondo.SqliteDataSource;

import com.example.paciu.belmondo.SqliteDataSource.Results.NoSuchResultException;

import java.util.List;

/**
 * Created by paciu on 06.03.2016.
 */
public interface IDataSource<T>{

    String getTableName();

    List<T> getAll();

    List<T> getAllById(int userId);

    T getById(int id) throws NoSuchObjectException, NoSuchResultException;

    long insertNew(T object);

    long insertNewWithOpenAndCloseDB(T object);

    long getIdByName(String name);
}
