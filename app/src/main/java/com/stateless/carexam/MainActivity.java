package com.stateless.carexam;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;

import com.android.dbsupport.test.AppDB;
import com.android.support.ui.BaseFragmentActivity;
import com.android.support.utils.ColorUtils;
import com.android.support.widget.TitleView;
import com.stateless.carexam.view.ExamView;

public class MainActivity extends BaseFragmentActivity {


    private TitleView titleView;

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {

        titleView = $T(R.id.v_title);

        titleView.setTitle("标题");
        titleView.setBackgroundResource((R.color.title_view_bg));

        ExamView examView=$T(R.id.ev_exam);
        examView.setFragmentManager(getSupportFragmentManager());

        ExamView.ExamModel data = new ExamView.ExamModel();

        data.setCount(3);
        data.setName("214");
        data.setType(1);
        examView.setData(data);



    }

    @Override
    protected int getViewRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

    }



}
