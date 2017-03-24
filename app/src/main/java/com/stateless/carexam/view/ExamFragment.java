package com.stateless.carexam.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.support.ui.BaseFragment;
import com.stateless.carexam.R;

import java.util.ArrayList;

/**
 * Created by stateless on 2017/3/23.
 */

public  class ExamFragment extends BaseFragment {

    int count;

    public static ExamFragment newInstance(int count) {

        Bundle args = new Bundle();

        ExamFragment fragment = new ExamFragment();

        args.putInt("count",count);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        count=getArguments().getInt("count");
    }
    @Override
    protected void initView(View view) {
        LinearLayout root=(LinearLayout)view.findViewById(R.id.root);
        QuestionView qv_question=(QuestionView)view.findViewById(R.id.qv_question);

        ArrayList<QuestionView.QuestionViewModel> data = new ArrayList<>();
        QuestionView.QuestionViewModel model = new QuestionView.QuestionViewModel();

        model.setName("12");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");

        model.setOptions(strings);

        data.add(model);
        data.add(model);
        data.add(model);
        qv_question.setData(data);

    }

    @Override
    protected int getViewRes() {
        return R.layout.view_fragment_exam;
    }

    @Override
    public void onClick(View v) {

    }
}
