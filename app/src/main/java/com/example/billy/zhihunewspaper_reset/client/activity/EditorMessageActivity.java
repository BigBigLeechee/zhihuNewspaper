package com.example.billy.zhihunewspaper_reset.client.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.adapter.EditorMessageAdapter;
import com.example.billy.zhihunewspaper_reset.client.base.BaseActivity;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.Editors;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.EditorsMessage;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.ThemeDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/11/4.
 *
 * 编者信息的Activity
 */

public class EditorMessageActivity extends BaseActivity {

    final static public String EDITOR_MESSAGE_PARAMS_ID = "editor";

    @BindView(R.id.rv_editors_editorMessage)
    RecyclerView rv_editorMessage;

    private List<Editors>  mEditorMessageList;

    @Override
    public void initParams(Bundle params) {

        EditorsMessage message = (EditorsMessage) params.getSerializable(EDITOR_MESSAGE_PARAMS_ID);

        if(message != null){
            mEditorMessageList = message.getEditorList();
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_editor;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_editorMessage.setLayoutManager(manager);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) throws Exception {

    }

    @Override
    public void doBusiness(Context mContext) {
        parseMessage();
    }

    private void parseMessage(){
        EditorMessageAdapter adapter = new EditorMessageAdapter(mEditorMessageList);
        rv_editorMessage.setAdapter(adapter);
    }

}
