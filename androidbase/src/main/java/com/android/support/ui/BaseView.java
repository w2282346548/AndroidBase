package com.android.support.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

/**
 * Created by stateless on 2017/3/24.
 */

public abstract class BaseView<T> extends RelativeLayout implements View.OnClickListener{

    protected Context context;
    protected T data;

    public BaseView(Context context) {
        this(context,null);
    }

    public BaseView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        int res=getViewRes();
        LayoutInflater.from(context).inflate(res,this);
        initView();
    }

    public void setData(T data) {
        this.data = data;
        update();
    }

    protected abstract void initView();

    protected abstract void update();

    protected abstract int getViewRes();

    protected <V extends View> V $T(int id){
        V v=(V)this.findViewById(id);
        return v;
    }

    protected <V extends View> V $(int id){
        V v=$T(id);
        if (!(v instanceof AbsListView))
            v.setOnClickListener(this);
        return v;
    }


}
