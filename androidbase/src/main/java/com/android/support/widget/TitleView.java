package com.android.support.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.support.R;
import com.android.support.ui.BaseView;

/**
 * Created by stateless on 2017/3/25.
 */

public class TitleView extends BaseView<Object> {

    private TextView titleView;
    private ImageView leftButton;
    private ImageView rightButton;
    private TextView rightText;
    private TextView leftText;
    private FrameLayout backLayout;
    private FrameLayout rightlayout;

    public TextView getTitleView() {
        return titleView;
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        super.setBackgroundResource(resid);

//        initPalette();
    }


    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        backLayout = $(R.id.back_button);
        rightlayout = $(R.id.right_button);


        titleView = $T(R.id.tv_title);

        leftButton = $T(R.id.back_button_img);

        rightButton = $T(R.id.right_button_img);

        rightText = $T(R.id.right_button_text);

        leftText = $T(R.id.back_button_text);

        rightlayout.setVisibility(INVISIBLE);
        leftText.setVisibility(GONE);
        leftButton.setVisibility(VISIBLE);


    }


    @Override
    protected void update() {

    }

    @Override
    protected int getViewRes() {
        return R.layout.view_title;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.back_button) {
            finishActivity();
        } else if (i == R.id.right_button) {

        }
    }

    public void setRightButtonView(int resId) {
        setRightButtonView(resId, null);
    }

    public void setRightButtonView(int resId, OnClickListener onClickListener) {
        rightButton.setImageResource(resId);
        rightlayout.setOnClickListener(null == onClickListener ? new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        } : onClickListener);

        rightlayout.setVisibility(VISIBLE);
        rightButton.setVisibility(VISIBLE);
        rightText.setVisibility(GONE);
    }

    public void setRightButtonTextView(String text, OnClickListener onClickListener) {
        rightText.setText(text);
        rightlayout.setOnClickListener(onClickListener);

        rightlayout.setVisibility(VISIBLE);
        rightButton.setVisibility(GONE);
        rightText.setVisibility(VISIBLE);
    }


    public void setLeftButtonView(int resId) {
        setRightButtonView(resId, null);
    }

    public void setLeftButtonView(int resId, OnClickListener onClickListener) {
        leftButton.setImageResource(resId);
        backLayout.setOnClickListener(null == onClickListener ? new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        } : onClickListener);

        backLayout.setVisibility(VISIBLE);
        leftButton.setVisibility(VISIBLE);
        leftText.setVisibility(GONE);
    }

    public void setLeftButtonTextView(String text, OnClickListener onClickListener) {
        leftText.setText(text);
        leftButton.setOnClickListener(onClickListener == null ? new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        } : onClickListener);

        backLayout.setVisibility(VISIBLE);
        leftButton.setVisibility(GONE);
        leftText.setVisibility(VISIBLE);
    }

    public void setLeftButtonTextView(String text) {
        setLeftButtonTextView(text, null);
    }


    public void setTitle(String title) {
        titleView.setText(title);
    }

    private void finishActivity() {
        ((Activity) context).finish();
    }
}
