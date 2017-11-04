package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.NewActivity;
import com.example.billy.zhihunewspaper_reset.model.bean.News_latest.Stories;
import com.example.billy.zhihunewspaper_reset.model.network.URLManager;
import com.example.billy.zhihunewspaper_reset.model.util.ViewUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/10/25.
 *
 * 主页的新闻的Adapter
 */

public class MainNewsAdapter extends RecyclerView.Adapter<MainNewsAdapter.ViewHolder> {

    private List<Stories> mStoryList;
    private Context mContext;

    public MainNewsAdapter(List<Stories> stories){
        mStoryList = stories;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(null == mContext){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_newText.setText(mStoryList.get(position).getTitle());

        Uri uri = Uri.parse(mStoryList.get(position).getImages().get(0));
        holder.sdv_newImage.setImageURI(uri);

        final int index = position;
        holder.ll_newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = URLManager.news_base_url + mStoryList.get(index).getId();

                Bundle bundle = new Bundle();
                bundle.putString("url",url);

                ViewUtil.startActivityWithoutFinishOldActivity(mContext, NewActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ll_public_newsItem)
        LinearLayout ll_newsItem;

        @BindView(R.id.tv_public_newText)
        TextView tv_newText;

        @BindView(R.id.sdv_public_newImage)
        SimpleDraweeView sdv_newImage;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
