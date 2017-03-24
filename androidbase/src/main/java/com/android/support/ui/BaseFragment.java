package com.android.support.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by stateless on 2017/3/24.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Activity activity;

    private View view;

    protected LayoutInflater inflater;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        int contentResValue=getViewRes();
        if (contentResValue>0){
            view=inflater.inflate(contentResValue,container,false);
        }else {
            throw new IllegalArgumentException("没有设置视图Id");
        }
        this.inflater=inflater;

        init();

        initView(view);

        return view;
    }

    protected abstract void init();

    protected abstract void initView(View view);

    protected abstract int getViewRes();



    protected <T extends View> T $T(int id){
        T v=(T)view.findViewById(id);
        return v;
    }

    protected <T extends View> T $(int id){
        T view=$T(id);
        if (!(view instanceof AbsListView))
            view.setOnClickListener(this);
        return view;
    }


}
