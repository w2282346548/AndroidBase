package com.android.support.adapter;

import android.view.View;

/**
 * OnItemClickListener for RecyclerView.
 * <p>
 * Created by stateless on 2017/3/23.
 */

public interface OnItemClickListener {

    void onItemClick(View itemView, int viewType, int position);
}
