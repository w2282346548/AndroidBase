package com.android.support.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by stateless on 2017/3/23.
 */

public interface IMulItemViewType<T> {

    /**
     * @return Will not be called if using a RecyclerView.
     */
    int getViewTypeCount();

    /**
     * Item view type, a non-negative integer is better.
     *
     * @param position current position of ViewHolder
     * @param t        model item
     * @return viewType
     */
    int getItemViewType(int position, T t);

//    /**
//     * Layout res.
//     *
//     * @param viewType {@link #getItemViewType(int, T)}
//     * @return {@link android.support.annotation.LayoutRes}
//     */
//    @LayoutRes
//    int getLayoutId(int viewType);

    View getItemView(int viewType);
}
