package com.example.paciu.belmondo.SqliteDataSource.Activities;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteStatement;

import com.example.paciu.belmondo.SqliteDataSource.DataSource;
import com.example.paciu.belmondo.SqliteDataSource.NoSuchObjectException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 10.05.2016.
 */
public class SportActivityDataSource extends DataSource<SportActivityModel> {
    public SportActivityDataSource(Context context) {
        super(context);
    }

    private  String getAllQueryWithoutSemicolon = "SELECT  " +
            SportActivityDataSourceColumns.ID_COLUMN + ", " +
            SportActivityDataSourceColumns.TYPE_COLUMN + ", " +
            SportActivityDataSourceColumns.NAME_COLUMN + ", " +
            SportActivityDataSourceColumns.MET_COLUMN + ", " +
            SportActivityDataSourceColumns.UPPER_VALUE_MPERS_NAME_COLUMN + " " +
            "  FROM " + getTableName();

    @Override
    public String getTableName() {
        return "SPORTACTIVITIES";
    }

    @Override
    public List<SportActivityModel> getAll() {
        String sql = getAllQueryWithoutSemicolon;

        SQLiteCursor sqliteCursor = (SQLiteCursor) getDB().rawQuery(sql, null);
        List<SportActivityModel> sportActivities = getResultsFromCursor(sqliteCursor);
        return sportActivities;
    }

    public List<SportActivityModel> getAllByName(String name){
        String sql = getAllQueryWithoutSemicolon + " where " + SportActivityDataSourceColumns.NAME_COLUMN + "= ? order by " +
                SportActivityDataSourceColumns.UPPER_VALUE_MPERS_NAME_COLUMN + " asc;";
        SQLiteCursor sqliteCursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{name.toLowerCase()});
        List<SportActivityModel> results = getResultsFromCursor(sqliteCursor);
        return results;
    }

    @Override
    public List<SportActivityModel> getAllById(int id) {
        String sql = getAllQueryWithoutSemicolon + " where " + SportActivityDataSourceColumns.ID_COLUMN +
                " = ? ;";
        SQLiteCursor sqliteCursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{String.valueOf(id)});
        List<SportActivityModel> results = getResultsFromCursor(sqliteCursor);
        return results;
    }

    protected List<SportActivityModel> getResultsFromCursor(SQLiteCursor cursor){
        List<SportActivityModel> results = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                SportActivityModel result = fillResultFromCursor(cursor);
                results.add(result);
                cursor.moveToNext();
            }
        }
        return results;
    }

    protected SportActivityModel fillResultFromCursor(SQLiteCursor cursor){
        SportActivityModel result = new SportActivityModel();
        if(cursor.getColumnCount() > 0) {
            result.setId(cursor.getInt(0));
            result.setType(cursor.getInt(1));
            result.setName(cursor.getString(2));
            result.setMET(cursor.getFloat(3));
            result.setUpperValueInMPerSecond(cursor.getFloat(4));
        }
        return result;
    }

    @Override
    public SportActivityModel getById(int id) throws NoSuchObjectException {
        List<SportActivityModel> sportActivities  = getAllById(id);
        if(sportActivities.size() > 0) return sportActivities.get(0);
        throw new NoSuchActivityException("Sport activity not exists");
    }

    @Override
    public long insertNew(SportActivityModel object) {
        return -1;
    }

    @Override
    public long getIdByName(String name) {
        String sql = "SELECT ID FROM " + getTableName() + " where " + SportActivityDataSourceColumns.NAME_COLUMN + " = ?;";
        SQLiteStatement stmt = getDB().compileStatement(sql);
        stmt.bindString(1, name.toLowerCase());
        long value = stmt.simpleQueryForLong();
        return value;
    }

    public static class SportActivityDataSourceColumns{
        public static final String ID_COLUMN = "ID";
        public static final String TYPE_COLUMN = "TYPE";
        public static final String NAME_COLUMN = "NAME";
        public static final String MET_COLUMN = "MET";
        public static final String UPPER_VALUE_MPERS_NAME_COLUMN = "UPPER";
    }
}
