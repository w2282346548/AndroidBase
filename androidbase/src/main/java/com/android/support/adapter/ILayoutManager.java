package com.android.support.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by stateless on 2017/3/23.
 */

public interface ILayoutManager {

    boolean hasLayoutManager();

    RecyclerView.LayoutManager getLayoutManager();
}
