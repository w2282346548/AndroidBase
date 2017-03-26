package com.android.dbsupport;

import android.content.Context;
import android.content.Intent;

import com.android.dbsupport.model.DataBase;
import com.android.dbsupport.test.AppDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stateless on 2017/3/26.
 */

public class DBManager {

    private static DBManager instance;

    private DbOpenHelper openAppDbHelper;

    public static DBManager getInstance(Context context) {
        if (instance==null){
            instance=new DBManager(context);
        }
        return instance;
    }

    private DBManager(Context context){
         openAppDbHelper = new AppDB().getOpenHelper(context);
    }


    public DbOpenHelper getOpenAppDbHelper() {
        return openAppDbHelper;
    }
}
