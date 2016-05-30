package com.example.paciu.belmondo.SqliteDataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by paciu on 04.03.2016.
 */
public class SqliteController extends SQLiteOpenHelper {

    private static final int DB_VERSION = 20;

    private static final String DB_NAME = "com.example.paciu.belmondo.db";

    private static final String RESULTS_CREATE_QUERY = "CREATE TABLE RESULTS(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "USERID INTEGER, SPORTDATE TEXT NOT NULL, DURATION TEXT NOT NULL, DISCIPLINENAME TEXT NOT NULL, MAXSPEED REAL NOT NULL, " +
            "AVGSPEED REAL NOT NULL, DISTANCE REAL NOT NULL, CALORIES INTEGER NOT NULL, MAP BLOB NOT NULL, FOREIGN KEY(USERID) REFERENCES PROFILES(ID) ON DELETE CASCADE);";

    private static final String PROFILES_CREATE_QUERY = "CREATE TABLE PROFILES(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " NAME TEXT NOT NULL UNIQUE, BIRTHDATE TEXT NOT NULL, WEIGHT INTEGER NOT NULL, HEIGHT INTEGER NOT NULL, SEX INTEGER NOT NULL);";

    private static final String SPORTACTIVITIES_CREATE_QUERY = "CREATE TABLE SPORTACTIVITIES(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "TYPE INTEGER NOT NULL, NAME TEXT NOT NULL, MET REAL NOT NULL, UPPER REAL NOT NULL);";

    public SqliteController(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROFILES_CREATE_QUERY);
        db.execSQL(RESULTS_CREATE_QUERY);
        db.execSQL(SPORTACTIVITIES_CREATE_QUERY);
        db.execSQL(SPORTACTIVITIES_INIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table profiles");
        db.execSQL("drop table results");
        db.execSQL("drop table SPORTACTIVITIES");
        db.execSQL(PROFILES_CREATE_QUERY);
        db.execSQL(RESULTS_CREATE_QUERY);
        db.execSQL(SPORTACTIVITIES_CREATE_QUERY);
        db.execSQL(SPORTACTIVITIES_INIT);
    }

    private static final String SPORTACTIVITIES_INIT =
            "insert into SPORTACTIVITIES (type, name, met, upper) values" +
                    "(1,'walking',2, 0.89)," +
                    "(1,'walking',3.5, 1.1)," +
                    "(1,'walking',4.5, 1.34)," +
                    "(1,'walking', 5, 1.78)," +
                    "(1,'walking',8, 2.68)," +
                    "(2,'running',6, 1.78)," +
                    "(2,'running',8.3, 2.23)," +
                    "(2,'running',9, 2.32)," +
                    "(2,'running',9.8, 2.68)," +
                    "(2,'running',10.5, 3)," +
                    "(2,'running',11, 3.13)," +
                    "(2,'running',11.8, 3.35)," +
                    "(2,'running',11.8, 3.57)," +
                    "(2,'running',12.3, 3.84)," +
                    "(2,'running',12.8, 4.02)," +
                    "(2,'running',14.5, 4.47)," +
                    "(2,'running',16, 4.92)," +
                    "(2,'running',19, 5.36)," +
                    "(2,'running',19.8, 5.81)," +
                    "(2,'running',23, 6.25)," +
                    "(3, 'cycling', 3.5, 2.4)," +
                    "(3, 'cycling', 5.8, 4.2)," +
                    "(3, 'cycling', 6.8, 5.3)," +
                    "(3, 'cycling', 8, 6.21)," +
                    "(3, 'cycling', 10, 7.1)," +
                    "(3, 'cycling', 12, 8.49)," +
                    "(3, 'cycling', 15, 100);";
}
