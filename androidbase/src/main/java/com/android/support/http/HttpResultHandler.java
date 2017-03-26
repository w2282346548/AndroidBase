package com.android.support.http;

import com.android.support.model.Result;
import com.android.support.utils.JSONUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by stateless on 2017/3/26.
 */

public abstract class HttpResultHandler extends AsyncHttpResponseHandler {

    private String cacheKey;
    private String cacheValue;
    private long timeout;

    private long startTime;

    public static final int STATE_OK = 200;


    public HttpResultHandler() {
    }

    public HttpResultHandler(String cacheKey) {
        initCache(cacheKey);
    }

    public HttpResultHandler(String cacheKey, long timeout) {
        initCache(cacheKey, timeout);
    }

    public void initCache(String cacheKey) {
        initCache(cacheKey, 0);
    }

    public void initCache(String cacheKey, long timeout) {
        this.timeout = timeout;
        this.cacheKey = cacheKey;
    }


    @Override
    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
        String resultData = new String(responseBody);
//        LogUtil.log("resultData is :" + resultData);

        if(!TextUtils.isEmpty(resultData) && statusCode == STATE_OK) {

            Result baseResult = JSONUtil.parseObject(responseBody, Result.class);
            if (baseResult != null) {
                if (baseResult.isResult()) {
//                    CacheUtil.saveResultCache(baseResult, cacheKey, timeout);
                    if (TextUtils.isEmpty(cacheValue)
//                            || !CacheUtil.isDataEqualsCache(cacheKey, baseResult)
                            ) {
                        baseResult.setNeedRefresh(true);
                    } else {
                        baseResult.setNeedRefresh(false);
                    }
                }
            } else {
                baseResult = new Result();
                baseResult.setMsg("没有解析出结果！");
                baseResult.setResult(false);
            }

            baseResult.setRequestEnd(true);
            onSuccess(baseResult);

        } else {

            Result baseResult = new Result();
            baseResult.setMsg("请求服务器失败！");
            baseResult.setResult(false);
            baseResult.setRequestEnd(true);
            onSuccess(baseResult);

        }
    }

    @Override
    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

        Result baseResult = new Result();
        baseResult.setMsg("请求服务器失败！");
        baseResult.setResult(false);
        baseResult.setRequestEnd(true);
        onSuccess(baseResult);
    }

    @Override
    public void onStart() {
        super.onStart();
        startTime = System.currentTimeMillis();
        getCache();
    }

    public void getCache() {
        if (!TextUtils.isEmpty(cacheKey)) {
//            cacheValue = CacheDB.getCacheValue(cacheKey);
            if (!TextUtils.isEmpty(cacheValue)) {
                Result result = JSONUtil.parseObject(cacheValue, Result.class);
                result.setNeedRefresh(true);


                onSuccess(result);
            }
        }
    }

    public abstract void onSuccess(Result result);

}
