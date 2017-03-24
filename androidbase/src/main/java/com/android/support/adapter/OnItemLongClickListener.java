package com.android.support.adapter;

import android.view.View;

/**
 * OnItemLongClickListener for RecyclerView.
 * <p>
 * Created by stateless on 2017/3/23.
 */

public interface OnItemLongClickListener {
    void onItemLongClick(View itemView, int viewType, int position);
}
