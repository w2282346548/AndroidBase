package com.android.support.adapter;

import android.support.v7.widget.RecyclerView;

import com.android.support.adapter.animation.BaseAnimation;

/**
 * Created by stateless on 2017/3/23.
 */

public interface IAnimation {

    void enableLoadAnimation();

    void enableLoadAnimation(long duration, BaseAnimation animation);

    void cancelLoadAnimation();

    void setOnlyOnce(boolean onlyOnce);

    void addLoadAnimation(RecyclerView.ViewHolder holder);

}
