package com.stateless.carexam.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.support.ui.BaseView;
import com.stateless.carexam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stateless on 2017/3/23.
 */

public class QuestionView extends BaseView<List<QuestionView.QuestionViewModel>> {


    private RecyclerView listView;

    public QuestionView(Context context) {
        super(context);
    }

    public QuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuestionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {

//        ListView listView=(ListView)findViewById(R.id.list);
//
        listView = $T(R.id.list);


    }

    @Override
    protected void update() {
        LinearLayoutManager layout = new LinearLayoutManager(context);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layout);

        AnswerAdapter adapter = new AnswerAdapter(context);
        ArrayList<String> items = new ArrayList<>();
        for (QuestionViewModel model:data){
            items.addAll(model.getOptions());
        }
        adapter.addAll(items);
        listView.setAdapter(adapter);
    }

    @Override
    protected int getViewRes() {
        return R.layout.view_question;
    }

    @Override
    public void onClick(View v) {

    }


    public static class QuestionViewModel{
        private String  name;

        private List<String>options;

        public void setOptions(List<String> options) {
            this.options = options;
        }

        public List<String> getOptions() {
            return options;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
