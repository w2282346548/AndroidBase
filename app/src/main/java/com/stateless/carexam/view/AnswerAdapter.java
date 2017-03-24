package com.stateless.carexam.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.support.adapter.BaseAdapter;
import com.android.support.adapter.BaseViewHolder;
import com.stateless.carexam.R;

import java.util.List;

/**
 * Created by stateless on 2017/3/23.
 */

public class AnswerAdapter extends BaseAdapter<String> {


    public AnswerAdapter(Context context) {
        super(context);
    }

    public AnswerAdapter(Context mContext, List<String> mData) {
        super(mContext, mData);
    }

    @Override
    protected View createItemView(Context context, int viewType) {
        return new AnswerItemView(context);
    }

    @Override
    protected void onBindView(View itemView, int position, String itemData) {
        AnswerItemView answerItemView=(AnswerItemView)itemView;


        AnswerItemView.AnswerItemModel answerItemModel =new AnswerItemView.AnswerItemModel();
        answerItemModel.setOption("A");
        answerItemModel.setContent(itemData);

        answerItemView.setData(answerItemModel);

    }

}
