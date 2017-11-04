package com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BaseTheme;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.EditorMessageActivity;
import com.example.billy.zhihunewspaper_reset.client.adapter.ThemeEditorsAdapter;
import com.example.billy.zhihunewspaper_reset.client.adapter.ThemeNewsAdapter;
import com.example.billy.zhihunewspaper_reset.client.adapter.ThemeTitleViewAdapter;
import com.example.billy.zhihunewspaper_reset.client.base.BaseFragment;
import com.example.billy.zhihunewspaper_reset.client.base.Thread.SafeHandler;
import com.example.billy.zhihunewspaper_reset.client.view.ClickableViewPager;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.EditorsMessage;
import com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail.ThemeDetail;
import com.example.billy.zhihunewspaper_reset.model.util.NetworkUtil;
import com.example.billy.zhihunewspaper_reset.model.util.ThreadUtil;
import com.example.billy.zhihunewspaper_reset.model.util.ViewUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Billy on 2017/11/2.
 * <p>
 * 主题Fragment的基础类
 */

public abstract class ThemeBaseFragment extends BaseFragment implements View.OnTouchListener {

    @BindView(R.id.cvp_theme_titleImage)
    ClickableViewPager cvp_titleImage;

    @BindView(R.id.rl_theme_editorMessage)
    RelativeLayout rl_editorMessage;

    @BindView(R.id.rv_theme_editors)
    RecyclerView rv_editors;
    @BindView(R.id.rv_theme_content)
    RecyclerView rv_content;

    private ThemeDetail mThemeDetail;

    private Handler mHandler;

    private Unbinder unbinder;

    private BaseThemeInterface mInterface;



    @Override
    protected View bindView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_theme, null);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this,view);

        initEditorMessage();
        initNewsMessage();

        mHandler = new Handler(this);
    }

    @Override
    protected void setListener() {
        rl_editorMessage.setOnClickListener(this);
        rv_editors.setOnTouchListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()){
            case R.id.rl_theme_editorMessage:
                goToEditorActivity();
                break;


        }
    }

    @Override
    protected void doBusiness() {

        setBaseThemeInterface(initBaseTheme());

        if(mInterface != null && mInterface.getThemeUrl() != null){
            loadDate(mInterface.getThemeUrl());
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 加载网络上的数据
     * @param url   数据的 url
     */
    private void loadDate(String url){

        NetworkUtil.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                analysisMessage(response.body().string());
            }
        });

    }

    /**
     * 前往EditorActivity的页面
     */
    private void goToEditorActivity(){
        //建立信息
        Bundle bundle = new Bundle();
        bundle.putSerializable(EditorMessageActivity.EDITOR_MESSAGE_PARAMS_ID,
                getEditorsMessage());

        //开启界面
        ViewUtil.startActivityWithoutFinishOldActivity(getContext(),
                EditorMessageActivity.class,
                bundle);
    }


    /**
     * 获取主编们的信息
     * @return  主编们的信息
     */
    private EditorsMessage getEditorsMessage(){
        EditorsMessage message = new EditorsMessage();
        message.setEditorList(mThemeDetail.getEditors());

        return message;
    }

    /**
     * 解析从网络获取到的数据
     * @param message  数据
     */
    private void analysisMessage(String message){
        mThemeDetail = NetworkUtil.parseJson(message,ThemeDetail.class);

        Log.i(TAG,mThemeDetail == null ? "Yes":"No");
        loadTitle();
        loadEditors();
        loadNews();
    }

    /**
     * 初始化主编模块
     */
    private void initEditorMessage(){
        //建立editor的布局
        LinearLayoutManager editorLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {

                //建立RecycleView的布局
                RecyclerView.LayoutParams params = new RecyclerView.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

                params.setMarginStart(10);
                params.setLayoutDirection(HORIZONTAL);

                return params;
            }
        };
        editorLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv_editors.setLayoutManager(editorLayoutManager);
    }

    /**
     * 初始化新闻的消息
     */
    private void initNewsMessage(){
        //建立content的布局
        LinearLayoutManager contentLayoutManager= new LinearLayoutManager(getActivity()){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {

                //建立RecycleView的布局属性
                RecyclerView.LayoutParams params = new RecyclerView.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT,200);
                params.topMargin = 20;

                return params;
            }
        };
        rv_content.setLayoutManager(contentLayoutManager);
    }

    /**
     * 加载主要的新闻信息内容
     */
    final static private int LOAD_NEWS = 3;
    private void loadNews(){
        ThemeNewsAdapter adapter = new ThemeNewsAdapter(mThemeDetail.getStories());

        Message message = ThreadUtil.initMessage(adapter,LOAD_NEWS);
        mHandler.sendMessage(message);
    }

    /**
     * 加载主编的信息
     */
    final static private int LOAD_EDITORS = 2;
    private void loadEditors(){
        ThemeEditorsAdapter adapter = new ThemeEditorsAdapter(mThemeDetail.getEditors());

        Message message = ThreadUtil.initMessage(adapter,LOAD_EDITORS);
        mHandler.sendMessage(message);
    }
    /**
     * 加载Title的信息
     */
    final static private int LOAD_TITLE = 1;
    private void loadTitle(){
        ThemeTitleViewAdapter adapter = new ThemeTitleViewAdapter(getContext(),mThemeDetail);

        Message message = ThreadUtil.initMessage(adapter,LOAD_TITLE);
        mHandler.sendMessage(message);
    }

    public void setBaseThemeInterface(BaseThemeInterface baseInterface){
        mInterface = baseInterface;
    }

    /**
     *
     * 抽象方法
     * 用于子类初始化变量以及建立BaseTheme的接口
     *
     */
    @Override
    protected abstract void initParams(Bundle bundle);
    public abstract BaseThemeInterface initBaseTheme();

    /**
     *
     * RecyclerView 点击事件
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //若点击手势是点击
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
           goToEditorActivity();
        }
        return false;
    }

    static class Handler extends SafeHandler<ThemeBaseFragment>{

        Handler(ThemeBaseFragment outerClass) {
            super(outerClass);
        }

        @Override
        public void runInThread(ThemeBaseFragment outerClass, Message msg) {
            switch (msg.what){
                case LOAD_TITLE:
                    outerClass.cvp_titleImage.setAdapter((ThemeTitleViewAdapter)msg.obj);
                    break;
                case LOAD_EDITORS:
                    outerClass.rv_editors.setAdapter((ThemeEditorsAdapter)msg.obj);
                    break;
                case LOAD_NEWS:
                    outerClass.rv_content.setAdapter((ThemeNewsAdapter)msg.obj);
                    break;
            }
        }
    }

}
