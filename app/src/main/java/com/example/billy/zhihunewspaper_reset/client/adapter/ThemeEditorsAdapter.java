package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.Editors;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/11/2.
 */

public class ThemeEditorsAdapter extends RecyclerView.Adapter<ThemeEditorsAdapter.ViewHolder> {

    private List<Editors> mEditorList;
    private Context mContext;

    public ThemeEditorsAdapter(List<Editors> editorList){
        mEditorList = editorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_theme_editors,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sdv_avatar.setImageURI(mEditorList.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return mEditorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sdv_theme_editorAvatar)
        SimpleDraweeView sdv_avatar;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
