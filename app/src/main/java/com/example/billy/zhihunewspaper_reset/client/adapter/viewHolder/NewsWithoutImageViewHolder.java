package com.example.billy.zhihunewspaper_reset.client.adapter.viewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;

import butterknife.BindView;

/**
 * Created by Billy on 2017/11/3.
 */

public class NewsWithoutImageViewHolder extends BaseViewHolder {

    @BindView(R.id.ll_public_newItemWithoutImage)
    LinearLayout ll_newsItem;

    @BindView(R.id.tv_public_newTextWithoutImage)
    TextView tv_newText;

    public NewsWithoutImageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        if(listener!= null){
            ll_newsItem.setOnClickListener(listener);
        }
    }

    public TextView getTv_newText() {
        return tv_newText;
    }

}
