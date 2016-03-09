package com.example.paciu.belmondo.DataSource;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.List;

/**
 * Created by paciu on 04.03.2016.
 */
public class SqliteController extends SQLiteOpenHelper {

    private static final int DB_VERSION = 4;

    private static final String DB_NAME = "com.example.paciu.belmondo.db";

    private static final String PROFILES_CREATE_QUERY = "CREATE TABLE PROFILES(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " NAME TEXT NOT NULL UNIQUE, BIRTHDATE TEXT NOT NULL, WEIGHT INTEGER NOT NULL, HEIGHT INTEGER NOT NULL);";

    public SqliteController(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROFILES_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table profiles");
        db.execSQL(PROFILES_CREATE_QUERY);
    }
}
