package com.android.support.model;

import java.util.List;

/**
 * Created by stateless on 2017/3/26.
 */

public class BaseJava {

    public static boolean objectNotNull(Object object) {
        if (object == null) {
            return false;
        }
        return true;
    }

    public static boolean objectIsNull(Object object) {
        return !objectNotNull(object);
    }

    public static boolean strNotEmpty(String str) {
        if (objectIsNull(str) || str.length() == 0) {
            return false;
        }
        return true;
    }

    public static boolean strIsEmpty(String str) {
        return !strNotEmpty(str);
    }

    public static boolean listNotEmpty(List list) {
        if (objectIsNull(list) || list.size() == 0) {
            return false;
        }
        return true;
    }

    public static boolean listIsEmpty(List list) {
        return !listNotEmpty(list);
    }
}
