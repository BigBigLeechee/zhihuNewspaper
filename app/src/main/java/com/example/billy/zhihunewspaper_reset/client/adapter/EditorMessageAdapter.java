package com.example.billy.zhihunewspaper_reset.client.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.EditorPersonActivity;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.Editors;
import com.example.billy.zhihunewspaper_reset.model.util.ViewUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/11/4.
 *
 * 编者信息的RecyclerView
 */

public class EditorMessageAdapter extends RecyclerView.Adapter<EditorMessageAdapter.ViewHolder> {

    //当ID为许多人的时候,不需要进行页面跳转
    final static private int MANY_PEOPLE_ID = 0;

    //个人主页的格式为urlHead + id + urlFoot
    final static private String urlHead = "https://news-at.zhihu.com/api/4/editor/";
    final static private String urlFoot = "/profile-page/android";

    private Context mContext;
    private List<Editors> mEditorList;

    public EditorMessageAdapter(List<Editors> editorList){
        mEditorList = editorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(null == mContext){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_editors_editor_message,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sdv_editorImage.setImageURI(mEditorList.get(position).getAvatar());
        holder.tv_editorDescription.setText(mEditorList.get(position).getBio());
        holder.tv_editorName.setText(mEditorList.get(position).getName());

        if(MANY_PEOPLE_ID == mEditorList.get(position).getId()){
            return;
        }

        final int index= position;
        holder.rl_editorMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(EditorPersonActivity.EDITOR_PERSON_ID,
                        urlHead + mEditorList.get(index).getId() + urlFoot);

                ViewUtil.startActivityWithoutFinishOldActivity(mContext, EditorPersonActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEditorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sdv_editors_editorImage)
        SimpleDraweeView sdv_editorImage;

        @BindView(R.id.tv_editors_editorDescription)
        TextView tv_editorDescription;
        @BindView(R.id.tv_editors_editorName)
         TextView tv_editorName;

        @BindView(R.id.rl_editors_editorMessage)
        RelativeLayout rl_editorMessage;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }
}
