package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.model.bean.News_latest.Top_stories;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 2017/10/30.
 *
 * Main模块的抬头的ViewPaper
 */

public class MainTitleViewAdapter extends PagerAdapter {

    private Context mContext;
    private List<View> mTitleViewList;

    public MainTitleViewAdapter(Context context, List<Top_stories> storyList){

        mContext = context;
        mTitleViewList = new ArrayList<>();

        initView(storyList);
    }


    @Override
    public int getCount() {
        return mTitleViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mTitleViewList.get(position));
        return mTitleViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mTitleViewList.get(position));
    }

    private void initView(List<Top_stories> storyList){
        for(Top_stories topStory : storyList){

            View view = View.inflate(mContext, R.layout.item_title_view,null);

            SimpleDraweeView image = view.findViewById(R.id.sdv_public_titleImage);
            TextView text = view.findViewById(R.id.tv_public_titleText);

            image.setImageURI(topStory.getImage());
            text.setText(topStory.getTitle());

            mTitleViewList.add(view);
        }
    }
}
