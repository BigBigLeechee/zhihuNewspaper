package com.example.billy.zhihunewspaper_reset.client.adapter.viewHolder;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

/**
 * Created by Billy on 2017/11/3.
 */

public class NewsWithImageViewHolder extends BaseViewHolder {

    @BindView(R.id.ll_public_newsItem)
    LinearLayout ll_newsItem;

    @BindView(R.id.tv_public_newText)
    TextView tv_newText;

    @BindView(R.id.sdv_public_newImage)
    SimpleDraweeView sdv_newImage;

    public NewsWithImageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        if(null != listener){
            ll_newsItem.setOnClickListener(listener);
        }
    }

    public TextView getTv_newText() {
        return tv_newText;
    }

    public SimpleDraweeView getSdv_newImage() {
        return sdv_newImage;
    }
}
