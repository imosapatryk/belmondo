package com.example.paciu.belmondo.DataSource.Profiles;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.paciu.belmondo.DataSource.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 06.03.2016.
 */
public class ProfileDataSource extends DataSource<Profile> {

    public ProfileDataSource(Context context) {
        super(context);
    }

    public boolean profileExistsWithOpenAndCloseDB(String name){
        boolean profileExists;

        openDB();
        profileExists = profileExists(name);
        closeDB();

        return profileExists;
    }

    public boolean profileExists(String name){
        String sql = "SELECT EXISTS ( SELECT ID FROM " + getTableName() + " WHERE " + ProfileDataSourceColumns.NAME_COLUMN + " = ? );";

        SQLiteDatabase d = getDB();
        SQLiteStatement stmt = getDB().compileStatement(sql);
        stmt.bindString(1, name);

        long result = stmt.simpleQueryForLong();
        return result > 0;
    }

    @Override
    public long insertNew(Profile object) {
        String sql = "INSERT INTO " + getTableName() + "("
                + ProfileDataSourceColumns.NAME_COLUMN + ", "
                + ProfileDataSourceColumns.BIRTHDATE_COLUMN + ", "
                + ProfileDataSourceColumns.WEIGHT_COLUMN + ", "
                + ProfileDataSourceColumns.HEIGHT_COLUMN + ") VALUES (?, ?, ?, ?)";

        SQLiteStatement stmt = getDB().compileStatement(sql);
        bindAllAttributesToStatement(stmt, object);

        long result = stmt.executeInsert();
        return result;
    }

    protected void bindAllAttributesToStatement(SQLiteStatement stmt, Profile object){
        stmt.bindString(1, object.getName());
        stmt.bindString(2, object.getBirthDate().toString());
        stmt.bindDouble(3, object.getWeight());
        stmt.bindDouble(4, object.getHeight());
    }

    public Profile getProfile(String name) throws NoSuchProfileException {
        String sql = "SELECT * FROM PROFILES WHERE NAME = ?;";

        SQLiteCursor cursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{name});

        if(cursor.moveToNext()){
            Profile profile = fillProfileFromCursor(cursor);
            return profile;
        } else throw new NoSuchProfileException();
    }

    @Override
    public List<Profile> getAll() {

        String sql = "SELECT  " +
                ProfileDataSourceColumns.ID_COLUMN + ", " +
                ProfileDataSourceColumns.NAME_COLUMN + ", " +
                ProfileDataSourceColumns.BIRTHDATE_COLUMN + ", " +
                ProfileDataSourceColumns.WEIGHT_COLUMN + ", " +
                ProfileDataSourceColumns.HEIGHT_COLUMN +
                "  FROM " + getTableName() + ";";

        SQLiteCursor sqliteCursor = (SQLiteCursor) getDB().rawQuery(sql, null);

        List<Profile> profiles = getProfilesFromCursor(sqliteCursor);
        return profiles;
    }

    protected List<Profile> getProfilesFromCursor(SQLiteCursor cursor){
        List<Profile> profiles = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                Profile profile = fillProfileFromCursor(cursor);
                profiles.add(profile);
            }
        }

        return profiles;
    }

    @Override
    public Profile getById(int id) throws NoSuchObjectException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + ProfileDataSourceColumns.ID_COLUMN + " = ? );";

        SQLiteCursor cursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            Profile profile = fillProfileFromCursor(cursor);
            return profile;
        } else throw new NoSuchProfileException();
    }

    protected Profile fillProfileFromCursor(SQLiteCursor cursor){
        Profile profile = new Profile();
        if(cursor.getColumnCount() > 0) {
            profile.setId(cursor.getInt(0));
            profile.setName(cursor.getString(1));
            profile.setBirthDate(cursor.getString(2));
            profile.setWeight(cursor.getInt(3));
            profile.setHeight(4);
        }
        return profile;
    }

    @Override
    public String getTableName() {
        return "PROFILES";
    }

    public static class ProfileDataSourceColumns{
        public static final String ID_COLUMN = "ID";
        public static final String NAME_COLUMN = "NAME";
        public static final String BIRTHDATE_COLUMN = "BIRTHDATE";
        public static final String WEIGHT_COLUMN = "WEIGHT";
        public static final String HEIGHT_COLUMN = "HEIGHT";
    }


}
