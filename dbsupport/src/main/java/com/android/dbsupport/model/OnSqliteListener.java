package com.android.dbsupport.model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by stateless on 2017/3/26.
 */

public interface OnSqliteListener {
    void onCreate(SQLiteDatabase db);
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

}
