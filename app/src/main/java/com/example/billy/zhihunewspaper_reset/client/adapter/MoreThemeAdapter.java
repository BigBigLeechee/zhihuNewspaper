package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.activityInterface.OnFragmentChoose;
import com.example.billy.zhihunewspaper_reset.model.bean.Themes.Others;
import com.example.billy.zhihunewspaper_reset.model.network.URLManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/10/29.
 *
 * 更多主题的Adapter
 */

public class MoreThemeAdapter extends RecyclerView.Adapter<MoreThemeAdapter.ViewHolder> {
    
    private List<Others> mOtherList;
    private Context mContext;

    private OnFragmentChoose mOnFragmentChoose;

    public MoreThemeAdapter(List<Others> otherList){
        mOtherList = otherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_theme_themes_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_themeName.setText(mOtherList.get(position).getName());

        final int index = position;
        holder.ll_themeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", URLManager.base_theme_url + mOtherList.get(index).getId());
                mOnFragmentChoose.onFragmentChoose(mOtherList.get(index).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mOtherList.size();
    }

    public void setOnFragmentChoose(OnFragmentChoose listener){
        mOnFragmentChoose = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ll_moreTheme_themeItem)
        LinearLayout ll_themeItem;

        @BindView(R.id.tv_moreTheme_themeName)
        TextView tv_themeName;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
