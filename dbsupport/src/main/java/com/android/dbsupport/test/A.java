package com.android.dbsupport.test;

import com.android.dbsupport.model.Table;

/**
 * Created by stateless on 2017/3/26.
 */

public class A {
    public static void main(String[] args) {
        Table table=new Table();
        System.out.printf(""+table.getCreateTableSQL());
    }
}
