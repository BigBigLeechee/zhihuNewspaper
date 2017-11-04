package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.os.Debug;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.ThemeDetail;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 2017/11/2.
 */

public class ThemeTitleViewAdapter extends PagerAdapter {

    private List<View> mTitleView;

    public ThemeTitleViewAdapter(Context context,ThemeDetail detail){

        mTitleView = new ArrayList<>();

        View titleView = View.inflate(context, R.layout.item_title_view,null);

        SimpleDraweeView sdv_titleImage = titleView.findViewById(R.id.sdv_public_titleImage);
        TextView tv_titleText = titleView.findViewById(R.id.tv_public_titleText);

        sdv_titleImage.setImageURI(detail.getImage());
        tv_titleText.setText(detail.getDescription());

        mTitleView.add(titleView);
    }

    @Override
    public int getCount() {
        return mTitleView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mTitleView.get(position));
        return mTitleView.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mTitleView.get(position));
    }
}
