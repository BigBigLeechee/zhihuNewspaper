package com.example.billy.zhihunewspaper_reset.client.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Billy on 2017/11/4.
 *
 * 用户的个人简要信息的页面
 */

public class EditorPersonActivity extends BaseActivity {

    final static public String EDITOR_PERSON_ID = "editor_person";

    @BindView(R.id.wv_editorPerson_message)
    WebView wv_message;

    private String url;

    @Override
    public void initParams(Bundle params) {
        url = params.getString(EDITOR_PERSON_ID);
        Log.i(TAG,url);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_editor_person;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) throws Exception {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void doBusiness(Context mContext) {
        wv_message.loadUrl(url);

        wv_message.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                wv_message.loadUrl(url);
                return true;
            }
        });

        WebSettings setting = wv_message.getSettings();
        setting.setJavaScriptEnabled(true);
    }
}
