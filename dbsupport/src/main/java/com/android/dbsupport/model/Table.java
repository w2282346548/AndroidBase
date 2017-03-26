package com.android.dbsupport.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stateless on 2017/3/25.
 */

public class Table implements ITable{

    private String avatar;

    private String name;

    public String getId() {
        return id;
    }


    @Override
    public String getTableName() {
        return "config";
    }

    @Override
    public String getCreateTableSQL() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("create table ").append(getTableName()).append(" ( ");

        for (String filed:getFiledList()) {
            stringBuilder.append(filed);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public List<String> getFiledList() {
        filedList.add(id);
        filedList.add("avatar");
        filedList.add("name");
        return filedList;
    }
}
