package com.example.paciu.belmondo.SqliteDataSource.Results;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteStatement;

import com.example.paciu.belmondo.SqliteDataSource.NoSuchObjectException;
import com.example.paciu.belmondo.Result.LatLngSerializableImpl;
import com.example.paciu.belmondo.SqliteDataSource.DataSource;
import com.example.paciu.belmondo.Utils.ByteUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 10.04.2016.
 */
public class ResultDataSource extends DataSource<ResultModel> {

    private  String getAllQueryWithoutSemicolon = "SELECT  " +
            ResultDataSourceColumns.ID_COLUMN + ", " +
            ResultDataSourceColumns.USER_ID_COLUMN + ", " +
            ResultDataSourceColumns.DATETIME_COLUMN + ", " +
            ResultDataSourceColumns.DURATION_COLUMN + ", " +
            ResultDataSourceColumns.DISCIPLINE_NAME_COLUMN + ", " +
            ResultDataSourceColumns.MAX_SPEED_COLUMN + ", " +
            ResultDataSourceColumns.AVG_SPEED_COLUMN + ", " +
            ResultDataSourceColumns.DISTANCE_COLUMN + ", " +
            ResultDataSourceColumns.CALORIES_COLUMN + ", " +
            ResultDataSourceColumns.MAP_COLUMN +
            "  FROM " + getTableName();

    public ResultDataSource(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return "RESULTS";
    }

    @Override
    public List<ResultModel> getAll() {
        String sql = getAllQueryWithoutSemicolon + " Order by " + ResultDataSourceColumns.ID_COLUMN + " desc;";

        SQLiteCursor sqliteCursor = (SQLiteCursor) getDB().rawQuery(sql, null);
        List<ResultModel> results = getResultsFromCursor(sqliteCursor);
        return results;
    }

    @Override
    public List<ResultModel> getAllById(int userId) {
        String sql = getAllQueryWithoutSemicolon + " where " + ResultDataSourceColumns.USER_ID_COLUMN +
                " = ? Order by " + ResultDataSourceColumns.ID_COLUMN +  " desc;";
        SQLiteCursor sqliteCursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{String.valueOf(userId)});
        List<ResultModel> results = getResultsFromCursor(sqliteCursor);
        return results;
    }

    protected List<ResultModel> getResultsFromCursor(SQLiteCursor cursor){
        List<ResultModel> results = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                ResultModel result = fillResultFromCursor(cursor);
                results.add(result);
                cursor.moveToNext();
            }
        }
        return results;
    }

    protected ResultModel fillResultFromCursor(SQLiteCursor cursor){
        ResultModel result = new ResultModel();
        if(cursor.getColumnCount() > 0) {
            result.setId(cursor.getInt(0));
            result.setUserId(cursor.getInt(1));
            result.setDateTime(cursor.getString(2));
            result.setDuration(cursor.getString(3));
            result.setDisciplineName(cursor.getString(4));
            result.setMaxSpeed(cursor.getFloat(5));
            result.setAvgSpeed(cursor.getFloat(6));
            result.setDistance(cursor.getFloat(7));
            result.setCalories(cursor.getInt(8));
            result.setMapCoords((List<LatLngSerializableImpl>)ByteUtils.fromByteArray(cursor.getBlob(9)));
        }
        return result;
    }


    @Override
    public ResultModel getById(int id) throws NoSuchObjectException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + ResultDataSourceColumns.ID_COLUMN + " = ? );";

        SQLiteCursor cursor = (SQLiteCursor)getDB().rawQuery(sql, new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            ResultModel result = fillResultFromCursor(cursor);
            return result;
        } else throw new NoSuchResultException("Result does not exists");
    }

    @Override
    public long insertNew(ResultModel object) {
        String sql = "INSERT INTO " + getTableName() + "(" +
               ResultDataSourceColumns.USER_ID_COLUMN + ", " +
                ResultDataSourceColumns.DATETIME_COLUMN + ", " +
                ResultDataSourceColumns.DURATION_COLUMN + ", " +
                ResultDataSourceColumns.DISCIPLINE_NAME_COLUMN + ", " +
                ResultDataSourceColumns.MAX_SPEED_COLUMN + ", " +
                ResultDataSourceColumns.AVG_SPEED_COLUMN + ", " +
                ResultDataSourceColumns.DISTANCE_COLUMN + ", " +
                ResultDataSourceColumns.CALORIES_COLUMN + ", " +
                ResultDataSourceColumns.MAP_COLUMN + ") VALUES (?, ?, ?, ?,?, ?, ?, ?, ?)";

        SQLiteStatement stmt = getDB().compileStatement(sql);
        bindAllAttributesToStatement(stmt, object);

        long result = stmt.executeInsert();
        return result;
    }

    @Override
    public long getIdByName(String name) {
        return -1;
    }

    protected void bindAllAttributesToStatement(SQLiteStatement stmt, ResultModel object){
        stmt.bindString(1, String.valueOf(object.getUserId()));
        stmt.bindString(2, object.getDateTime());
        stmt.bindString(3, object.getDuration());
        stmt.bindString(4, object.getDisciplineName());
        stmt.bindDouble(5, object.getMaxSpeed());
        stmt.bindDouble(6, object.getAvgSpeed());
        stmt.bindDouble(7, object.getDistance());
        stmt.bindString(8, String.valueOf(object.getCalories()));
        stmt.bindBlob(9, ByteUtils.toByteArray(object.getMapCoords()));
    }

    public static class ResultDataSourceColumns{
        public static final String ID_COLUMN = "ID";
        public static final String USER_ID_COLUMN = "USERID";
        public static final String DATETIME_COLUMN = "SPORTDATE";
        public static final String DURATION_COLUMN = "DURATION";
        public static final String DISCIPLINE_NAME_COLUMN = "DISCIPLINENAME";
        public static final String MAX_SPEED_COLUMN = "MAXSPEED";
        public static final String AVG_SPEED_COLUMN = "AVGSPEED";
        public static final String DISTANCE_COLUMN = "DISTANCE";
        public static final String CALORIES_COLUMN = "CALORIES";
        public static final String MAP_COLUMN = "MAP";

    }
}
