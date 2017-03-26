package com.android.dbsupport.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.dbsupport.DbOpenHelper;
import com.android.dbsupport.model.DataBase;

/**
 * Created by stateless on 2017/3/26.
 */

public class AppDB extends DataBase {

   public AppDB(){
        version=1;
        dataBaseName="app_db";
        Config config = new Config();
        tables.add(config);
    }

    @Override
    protected void onCreateDb(SQLiteDatabase db) {
        db.execSQL(Config.CONFIG_TABLE_CREATE);
    }

    @Override
    protected void onUpgradeDb(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DbOpenHelper getOpenHelper(Context context){
        DbOpenHelper instance = DbOpenHelper.getInstance(context, this);
        return instance;
    }
}
