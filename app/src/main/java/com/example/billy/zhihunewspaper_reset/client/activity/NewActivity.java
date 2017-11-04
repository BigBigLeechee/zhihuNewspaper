package com.example.billy.zhihunewspaper_reset.client.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.base.BaseActivity;
import com.example.billy.zhihunewspaper_reset.client.base.Thread.SafeHandler;
import com.example.billy.zhihunewspaper_reset.model.bean.News.News;
import com.example.billy.zhihunewspaper_reset.model.util.NetworkUtil;
import com.example.billy.zhihunewspaper_reset.model.util.ThreadUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Billy on 2017/10/29.
 *
 * 新闻的Activity
 */

public class NewActivity extends BaseActivity {

    @BindView(R.id.wv_news_news)
    WebView wv_news;

    private String url;
    private Handler mHandler;

    @Override
    public void initParams(Bundle params) {
        url = params.getString("url");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_news;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        mHandler = new Handler(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) throws Exception {

    }

    @Override
    public void doBusiness(Context mContext) {
        Log.i(TAG,url);

        parseUrl();
    }

    private void parseUrl(){

        NetworkUtil.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                News news = NetworkUtil.parseJson(response.body().string(),News.class);
                if (news != null) {
                    loadWebView(news.getShare_url());
                }
            }
        });
    }

    private void loadWebView(String shareUrl){
        Message message = ThreadUtil.initMessage(shareUrl,1);
        mHandler.sendMessage(message);
    }

    static class Handler extends SafeHandler<NewActivity>{

        Handler(NewActivity outerClass) {
            super(outerClass);
        }

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public void runInThread(final NewActivity outerClass, Message msg) {

            outerClass.wv_news.loadUrl((String)msg.obj);
            outerClass.wv_news.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    outerClass.wv_news.loadUrl(url);
                    return true;
                }
            });

            WebSettings webSettings = outerClass.wv_news.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(wv_news.canGoBack()){
                wv_news.goBack();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
