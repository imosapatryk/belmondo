package com.example.paciu.belmondo.SqliteDataSource;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/**
 * Created by paciu on 06.03.2016.
 */
public abstract class DataSource<T> implements IDataSource<T> {

    private SqliteController sqliteController;
    private SQLiteDatabase db;

    public DataSource(Context context){
        sqliteController = new SqliteController(context);
    }

    public void openDB(){
        if(db == null || !db.isOpen())
            db = sqliteController.getWritableDatabase();
    }

    public void closeDB(){
        if(db != null && db.isOpen())
            db.close();
    }

    protected SQLiteDatabase getDB(){
        return db;
    }

    public SQLiteCursor executeQuery(SQLiteQuery query){
        return null;
    }

    @Override
    public long insertNewWithOpenAndCloseDB(T object) {
        long result;

        openDB();
        result = insertNew(object);
        closeDB();

        return result;
    }
}
