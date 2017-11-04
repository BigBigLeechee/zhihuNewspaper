package com.example.billy.zhihunewspaper_reset.client.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/11/3.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected SparseArray<View> views;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        views = new SparseArray<>();
        views.put(itemView.getId(),itemView);
    }


    public abstract void setOnClickListener(View.OnClickListener listener);


}
