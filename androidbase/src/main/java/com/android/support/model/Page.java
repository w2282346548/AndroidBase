package com.android.support.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stateless on 2017/3/26.
 */

public class Page<E> implements Serializable {

    private boolean hasMore = true;

    @JSONField(name = "page")
    private int currentPage;

    private int nextPage;

    @JSONField(name = "totalpage")
    private int totalPage;

    private String resultCode;

    private List<E> dataList = new ArrayList();

    @JSONField(name = "list")
    private String listStr="";

    private int totalSize;

    public void initPage() {
        this.currentPage = 0;
        this.nextPage = 0;
        this.totalPage = 1;
        dataList.clear();
        hasMore = true;
    }

    public void initPage(Page<E> page) {
        this.dataList.clear();
        this.currentPage = page.getCurrentPage();
        this.nextPage = page.getCurrentPage() + 1;
        this.totalPage = page.getTotalPage();
        //this.sinceTime = page.getSinceTime();
        this.totalSize = page.getTotalSize();
        //this.sid = page.getSid();
        if (BaseJava.listNotEmpty(page.getDataList())) {
            this.dataList.addAll(page.getDataList());
        }
        this.resultCode = page.getResultCode();

        if (BaseJava.listIsEmpty(page.getDataList())) {
            hasMore = false;
        }

    }

    public void initPage(List<E> list, int page) {
        this.dataList.clear();
        this.currentPage = page;
        this.nextPage = page + 1;
        if (BaseJava.listIsEmpty(list)) {
            hasMore = false;
        } else {
            this.dataList.addAll(list);
        }
    }

    public boolean isRefresh() {
        if (currentPage == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNeedCache() {
        if (currentPage == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasMore() {
//        if (nextPage <= totalPage && totalPage != 1) {
//            return true;
//        } else {
//            return false;
//        }

        return hasMore;
    }

    public Page() {
        super();
        initPage();
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getListStr() {
        return listStr;
    }

    public void setListStr(String listStr) {
        this.listStr = listStr;
    }

    public List<E> getDataList() {
        return dataList;
    }

    public List<E> getList() {
        return getDataList();
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

//    public LinkTuRequestParams getParams() {
//        RequestParams params = new RequestParams();
//        params.put(Constants.PARAM_PAGE, nextPage + "");
//        //params.put(Constants.PARAM_SID, sid + "");
//        // params.put(Constants.PARAM_SINCE_TIME, sinceTime);
//        return params;
//    }


}


