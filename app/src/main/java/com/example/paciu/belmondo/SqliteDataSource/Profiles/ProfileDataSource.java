package com.example.paciu.belmondo.SqliteDataSource.Profiles;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.paciu.belmondo.Profile.Sex;
import com.example.paciu.belmondo.SqliteDataSource.DataSource;
import com.example.paciu.belmondo.SqliteDataSource.NoSuchObjectException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 06.03.2016.
 */
public class ProfileDataSource extends DataSource<ProfileModel> {

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

    public long update(ProfileModel object){
        String sql = "UPDATE " + getTableName()
                + " SET "
                + ProfileDataSourceColumns.BIRTHDATE_COLUMN + " = ?, "
                + ProfileDataSourceColumns.WEIGHT_COLUMN + " = ?, "
                + ProfileDataSourceColumns.HEIGHT_COLUMN + " = ?, "
                + ProfileDataSourceColumns.SEX_COLUMN + " = ? "
                + " WHERE "
                + ProfileDataSourceColumns.ID_COLUMN + " = ?";
        SQLiteStatement stmt = getDB().compileStatement(sql);
        stmt.bindString(1, object.getBirthDate());
        stmt.bindDouble(2, object.getWeight());
        stmt.bindDouble(3, object.getHeight());
        stmt.bindDouble(4, object.getSex().toInt());
        long result = stmt.executeUpdateDelete();
        return result;
    }

    @Override
    public long insertNew(ProfileModel object) {
        String sql = "INSERT INTO " + getTableName() + "("
                + ProfileDataSourceColumns.NAME_COLUMN + ", "
                + ProfileDataSourceColumns.BIRTHDATE_COLUMN + ", "
                + ProfileDataSourceColumns.WEIGHT_COLUMN + ", "
                + ProfileDataSourceColumns.HEIGHT_COLUMN + ", "
                + ProfileDataSourceColumns.SEX_COLUMN + ") VALUES (?, ?, ?, ?, ?)";

        SQLiteStatement stmt = getDB().compileStatement(sql);
        bindAllAttributesToStatement(stmt, object);
        long result = stmt.executeInsert();

        return result;
    }

    @Override
    public long getIdByName(String name) {
        String sql = "SELECT ID FROM " + getTableName() + " where " + ProfileDataSourceColumns.NAME_COLUMN + " = ?;";
        SQLiteStatement stmt = getDB().compileStatement(sql);
        stmt.bindString(1, name);
        long value = stmt.simpleQueryForLong();
        return value;
    }

    protected void bindAllAttributesToStatement(SQLiteStatement stmt, ProfileModel object){
        stmt.bindString(1, object.getName());
        stmt.bindString(2, object.getBirthDate());
        stmt.bindDouble(3, object.getWeight());
        stmt.bindDouble(4, object.getHeight());
        stmt.bindDouble(5, object.getSex().toInt());
    }

    public ProfileModel getProfile(String name) throws NoSuchProfileException {
        String sql = "SELECT * FROM PROFILES WHERE NAME = ?;";

        SQLiteCursor cursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{name});

        if(cursor.moveToNext()){
            ProfileModel profile = fillProfileFromCursor(cursor);
            return profile;
        } else throw new NoSuchProfileException();
    }

    @Override
    public List<ProfileModel> getAll() {

        String sql = "SELECT  " +
                ProfileDataSourceColumns.ID_COLUMN + ", " +
                ProfileDataSourceColumns.NAME_COLUMN + ", " +
                ProfileDataSourceColumns.BIRTHDATE_COLUMN + ", " +
                ProfileDataSourceColumns.WEIGHT_COLUMN + ", " +
                ProfileDataSourceColumns.HEIGHT_COLUMN + ", " +
                ProfileDataSourceColumns.SEX_COLUMN +
                "  FROM " + getTableName() + ";";

        SQLiteCursor sqliteCursor = (SQLiteCursor) getDB().rawQuery(sql, null);
        List<ProfileModel> profiles = getProfilesFromCursor(sqliteCursor);
        return profiles;
    }

    @Override
    public List<ProfileModel> getAllById(int userId) {
        return null;
    }

    protected List<ProfileModel> getProfilesFromCursor(SQLiteCursor cursor){
        List<ProfileModel> profiles = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                ProfileModel profile = fillProfileFromCursor(cursor);
                profiles.add(profile);
                cursor.moveToNext();
            }
        }
        return profiles;
    }

    @Override
    public ProfileModel getById(int id) throws NoSuchObjectException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + ProfileDataSourceColumns.ID_COLUMN + " = ? );";

        SQLiteCursor cursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            ProfileModel profile = fillProfileFromCursor(cursor);
            return profile;
        } else throw new NoSuchProfileException();
    }

    protected ProfileModel fillProfileFromCursor(SQLiteCursor cursor){
        ProfileModel profile = new ProfileModel();
        if(cursor.getColumnCount() > 0) {
            profile.setId(cursor.getInt(0));
            profile.setName(cursor.getString(1));
            profile.setBirthDate(cursor.getString(2));
            profile.setWeight(cursor.getInt(3));
            profile.setHeight(cursor.getInt(4));
            profile.setSex((Sex.fromInt(cursor.getInt(5))));
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
        public static final String SEX_COLUMN = "SEX";

    }


}
