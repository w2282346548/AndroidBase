package com.android.support.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;


/**
 * Created by stateless on 2017/3/24.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity implements View.OnClickListener{

    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getViewRes());

        activity=this;

        init();

        initView();


    }

    protected abstract void init();

    protected abstract void initView();

    protected abstract int getViewRes();



    protected <T extends View> T $T(int id){
        T view=(T)findViewById(id);
        return view;
    }

    protected <T extends View> T $(int id){
        T view=$T(id);
        if (!(view instanceof AbsListView))
            view.setOnClickListener(this);
        return view;
    }

}
