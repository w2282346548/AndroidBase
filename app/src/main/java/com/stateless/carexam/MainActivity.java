package com.stateless.carexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.support.ui.BaseFragmentActivity;
import com.stateless.carexam.view.ExamView;

public class MainActivity extends BaseFragmentActivity {



    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
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
