package com.android.support.model;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.support.utils.JSONUtil;

import java.util.List;

/**
 * Created by stateless on 2017/3/26.
 */

public class Result
{
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_DEFAULT = -1;

    private boolean needRefresh;

    private int code = CODE_DEFAULT;

    private boolean result;

    private String msg;

    private String data;

    private boolean requestEnd;


    public String getDataStr(String key) {
        if (!TextUtils.isEmpty(data)) {
            JSONObject obj = JSON.parseObject(data);
            String dataStr = obj.getString(key);
            return dataStr;
        }
        return "";
    }

    public int getDataInt(String key) {
        if (!TextUtils.isEmpty(data)) {
            JSONObject obj = JSON.parseObject(data);
            int dataStr = obj.getIntValue(key);
            return dataStr;
        }
        return 0;
    }

    public boolean getDataBoolean(String key) {
        if (!TextUtils.isEmpty(data)) {
            JSONObject obj = JSON.parseObject(data);
            boolean dataStr = obj.getBoolean(key);
            return dataStr;
        }
        return false;
    }


    public <T > T getEntity(Class clazz) {
        if (BaseJava.strNotEmpty(this.data)) {
            return (T) JSONUtil.parseObject(this.data, clazz);
        }
        return null;
    }

    public <T> T getEntity(String key, Class clazz) {
        JSONObject obj = JSON.parseObject(data);
        String dataStr = obj.getString(key);
        if (BaseJava.strNotEmpty(dataStr)) {
            return (T) JSONUtil.parseObject(dataStr, clazz);
        }
        return null;
    }

    public Page getPage(Class clazz) {
        Page resultPage = JSONUtil.parseObject(data, Page.class);
        List list = JSONUtil.parseArray(resultPage.getListStr(), clazz);
        resultPage.setDataList(list);

        return resultPage;
    }

    public Page getPage(String key, Class clazz) {
        Page resultPage = JSONUtil.parseObject(data, Page.class);
        List list = JSONUtil.parseArray(getDataStr(key), clazz);
        resultPage.setDataList(list);
        return resultPage;
    }

    public List getList(String key, Class clazz) {
        List list = JSONUtil.parseArray(getDataStr(key), clazz);
        return list;
    }

    public List getList(Class clazz) {
        return getList("list", clazz);
    }

    public List getListForData(Class clazz) {
        List list = JSONUtil.parseArray(data, clazz);
        return list;
    }

    public boolean isNeedRefresh() {
        return needRefresh;
    }

    public void setNeedRefresh(boolean needRefresh) {
        this.needRefresh = needRefresh;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isResult() {
        if (code == CODE_SUCCESS) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public void setResult(boolean result) {
        if (result) {
            code = CODE_SUCCESS;
        } else {
            code = CODE_DEFAULT;
        }
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isRequestEnd() {
        return requestEnd;
    }

    public void setRequestEnd(boolean requestEnd) {
        this.requestEnd = requestEnd;
    }
}
