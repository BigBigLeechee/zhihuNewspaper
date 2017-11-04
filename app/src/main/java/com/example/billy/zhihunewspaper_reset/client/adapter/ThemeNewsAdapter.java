package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.NewActivity;
import com.example.billy.zhihunewspaper_reset.client.adapter.viewHolder.BaseViewHolder;
import com.example.billy.zhihunewspaper_reset.client.adapter.viewHolder.NewsWithImageViewHolder;
import com.example.billy.zhihunewspaper_reset.client.adapter.viewHolder.NewsWithoutImageViewHolder;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.Stories;
import com.example.billy.zhihunewspaper_reset.model.network.URLManager;
import com.example.billy.zhihunewspaper_reset.model.util.ViewUtil;

import java.util.List;

/**
 * Created by Billy on 2017/11/3.
 *
 * Theme的新闻内容
 */

public class ThemeNewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    final static private int HAVE_IMAGE = 1;
    final static private int WITHOUT_IMAGE =2;

    private Context mContext;
    private List<Stories> mStoryList;

    public ThemeNewsAdapter(List<Stories> storyList){
        mStoryList = storyList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(null == mContext){
            mContext = parent.getContext();
        }

        if(WITHOUT_IMAGE == viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_view_without_image,null);
            return new NewsWithoutImageViewHolder(view);
        }
        if(HAVE_IMAGE == viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_view,null);
            return new NewsWithImageViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        if(holder instanceof NewsWithImageViewHolder){
            ((NewsWithImageViewHolder)holder).getSdv_newImage()
                    .setImageURI(mStoryList.get(position).getImages().get(0));

            ((NewsWithImageViewHolder) holder).getTv_newText()
                    .setText(mStoryList.get(position).getTitle());
        }

        if(holder instanceof NewsWithoutImageViewHolder){
            ((NewsWithoutImageViewHolder) holder).getTv_newText()
                    .setText(mStoryList.get(position).getTitle());
        }

        int index = position;
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("url", URLManager.news_base_url + mStoryList.get(position).getId());

                ViewUtil.startActivityWithoutFinishOldActivity(mContext, NewActivity.class,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(mStoryList.get(position).getImages() != null){
            return HAVE_IMAGE;
        }
        return WITHOUT_IMAGE;
    }
}
