package com.android.dbsupport.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stateless on 2017/3/25.
 */

public interface ITable {


    String id="id";

    String getTableName();

    String getCreateTableSQL();

    List getFiledList();
//    List<String> filedList= Collections.emptyList();
    List<String> filedList= new ArrayList<>();

}
