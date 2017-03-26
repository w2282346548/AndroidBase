package com.android.dbsupport.test;

import com.android.dbsupport.model.Table;

/**
 * Created by stateless on 2017/3/26.
 */

public class Config extends Table{


    public static final String TABLE_NAME = "config";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_KEY = "key";
    public static final String COLUMN_NAME_VALUE = "value";
    public static final String CONFIG_TABLE_CREATE = "CREATE TABLE "
            + Config.TABLE_NAME + " ("
            + Config.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Config.COLUMN_NAME_KEY + " TEXT, "
            + Config.COLUMN_NAME_VALUE + " TEXT); ";


    @Override
    public String getCreateTableSQL() {
        return CONFIG_TABLE_CREATE;
    }
}