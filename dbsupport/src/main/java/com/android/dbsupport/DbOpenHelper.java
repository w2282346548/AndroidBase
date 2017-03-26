package com.android.dbsupport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.dbsupport.model.DataBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stateless on 2017/3/26.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static Map<String, DbOpenHelper> dbMaps = new HashMap<String, DbOpenHelper>();
//    private OnSqliteUpdateListener onSqliteUpdateListener;

    private DataBase nowDb;
    private String nowDbName;

    public DbOpenHelper(Context context, DataBase dataBase){
        super(context, dataBase.getDataBaseName(), null, dataBase.getVersion());
        nowDb=dataBase;
        nowDbName=dataBase.getDataBaseName();
    }

    public static DbOpenHelper getInstance(Context context, DataBase dataBase) {
        DbOpenHelper dbOpenHelper = dbMaps.get(dataBase.getDataBaseName());
        if (dbOpenHelper == null) {
            dbOpenHelper = new DbOpenHelper(context, dataBase);
        }
        dbMaps.put(dataBase.getDataBaseName(), dbOpenHelper);
        return dbOpenHelper;
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
//        for (Table table:nowDb.getTables()){
//            db.execSQL(table.getCreateTableSQL());
//        }

        nowDb.onCreate(db);
    }

    public void execSQL(String sql, Object[] bindArgs) {

        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.execSQL(sql, bindArgs);
        }
    }

    public Cursor rawQuery(String sql, String[] bindArgs) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, bindArgs);
            return cursor;
        }
    }

    public void insert(String table, ContentValues contentValues) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.insert(table, null, contentValues);
        }
    }

    public void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.update(table, values, whereClause, whereArgs);
        }
    }

    public void delete(String table, String whereClause, String[] whereArgs) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.delete(table, whereClause, whereArgs);
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            // Cursor cursor = database.rawQuery("select * from "
            // + TableName.TABLE_NAME_USER + " where userId =" + userId, null);
            Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
            return cursor;
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy,String limit) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            // Cursor cursor = database.rawQuery("select * from "
            // + TableName.TABLE_NAME_USER + " where userId =" + userId, null);
            Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            return cursor;
        }
    }

    public Cursor query(String tableName, String sqlString) {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery("select * from " + tableName + " " + sqlString, null);

            return cursor;
        }
    }

    public void clear() {
        DbOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        dataBaseOpenHelper.close();
        dbMaps.remove(dataBaseOpenHelper);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (onSqliteUpdateListener != null) {
//            onSqliteUpdateListener.onSqliteUpdateListener(db, oldVersion, newVersion);
//        }
        nowDb.onUpgrade(db, oldVersion, newVersion);
    }

//    public interface OnSqliteUpdateListener {
//        void onSqliteUpdateListener(SQLiteDatabase db, int oldVersion, int newVersion);
//    }
}
