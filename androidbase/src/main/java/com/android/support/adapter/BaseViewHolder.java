package com.android.support.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by stateless on 2017/3/23.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> childViews = new SparseArray<>();

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public static BaseViewHolder get(View convertView, View itemView) {
        BaseViewHolder holder;
        if (convertView == null) {
            holder = new BaseViewHolder(itemView);
            convertView = itemView;
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder) convertView.getTag();
        }
        return holder;
    }

    /**
     * Deprecated. Use {@link #findViewById(int)} instead for a better understanding.
     * @param id
     * @return T
     */
    @Deprecated
    public <T extends View>T getView(int id) {
        return findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int id) {
        View childView = childViews.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            if (childView != null)
                childViews.put(id, childView);
        }
        return (T) childView;
    }

}
