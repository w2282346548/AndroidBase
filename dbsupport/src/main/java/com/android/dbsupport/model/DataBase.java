package com.android.dbsupport.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stateless on 2017/3/26.
 */

public abstract class DataBase implements OnSqliteListener{
    protected String dataBaseName;
    protected int version=1;

    protected List<Table> tables;

    public DataBase() {
        tables=new ArrayList<>();
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateDb(db);
    }

    protected abstract void onCreateDb(SQLiteDatabase db);

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgradeDb(db,oldVersion,newVersion);
    }

    protected abstract void onUpgradeDb(SQLiteDatabase db, int oldVersion, int newVersion);
}
