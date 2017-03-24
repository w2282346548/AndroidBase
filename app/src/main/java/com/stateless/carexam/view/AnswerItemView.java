package com.stateless.carexam.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.support.ui.BaseView;
import com.stateless.carexam.R;

/**
 * Created by stateless on 2017/3/24.
 */

public class AnswerItemView extends BaseView<AnswerItemView.AnswerItemModel> {

    private TextView tvOption;
    private TextView tvContent;

    public AnswerItemView(Context context) {
        super(context);
    }

    public AnswerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        tvOption = $T(R.id.tv_option);
        tvContent = $T(R.id.tv_content);
    }

    @Override
    protected void update() {
        tvOption.setText(data.getOption());
        tvContent.setText(data.getContent());

    }

    @Override
    protected int getViewRes() {
        return R.layout.item_answer;
    }

    @Override
    public void onClick(View v) {

    }

   public static class AnswerItemModel{
        private String option;
        private String content;
        private String trueOption;


        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTrueOption() {
            return trueOption;
        }

        public void setTrueOption(String trueOption) {
            this.trueOption = trueOption;
        }
    }
}
