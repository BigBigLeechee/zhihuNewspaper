package com.example.billy.zhihunewspaper_reset.client.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.NewActivity;
import com.example.billy.zhihunewspaper_reset.client.adapter.MainNewsAdapter;
import com.example.billy.zhihunewspaper_reset.client.adapter.MainTitleViewAdapter;
import com.example.billy.zhihunewspaper_reset.client.base.BaseFragment;
import com.example.billy.zhihunewspaper_reset.client.base.Thread.SafeHandler;
import com.example.billy.zhihunewspaper_reset.client.view.CircleIndicator;
import com.example.billy.zhihunewspaper_reset.client.view.ClickableViewPager;
import com.example.billy.zhihunewspaper_reset.client.view.viewInterface.OnItemClickListener;
import com.example.billy.zhihunewspaper_reset.model.bean.News_latest.Latest;
import com.example.billy.zhihunewspaper_reset.model.network.URLManager;
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
 * Created by Billy on 2017/10/25.
 *
 * 主页的Fragment
 */

public class MainFragment extends BaseFragment{


    @BindView(R.id.cvp_main_titleImage)
    ClickableViewPager cvp_titleImage;
    @BindView(R.id.tv_main_data)
    TextView tv_data;
    @BindView(R.id.rv_main_content)
    RecyclerView rv_content;

    @BindView(R.id.ci_main_circleIndicator)
    CircleIndicator ci_viewGround;

    private Unbinder unbinder;

    private MainNewsAdapter mMainNewsAdapter;

    //当前的新闻信息
    private Latest mLatest;
    private Handler mHandler;

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    protected View bindView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this,view);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        rv_content.setLayoutManager(manager);
        rv_content.setAdapter(mMainNewsAdapter);

        mHandler = new Handler(this);
    }

    @Override
    protected void setListener() {
        cvp_titleImage.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Bundle bundle = new Bundle();
                bundle.putString("url",
                        URLManager.news_base_url + mLatest.getTop_stories().get(position).getId());

                ViewUtil.startActivityWithoutFinishOldActivity(getContext(),
                        NewActivity.class,bundle);
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
    }

    @Override
    protected void doBusiness() {
        loadNewsData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 加载主页的数据
     */
    private void loadNewsData(){
        NetworkUtil.get(URLManager.news_latest_url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                init(response.body().string());
            }
        });
    }

    /**
     * 初始化页面
     */
    private void init(String json){

        mLatest = NetworkUtil.parseJson(json,Latest.class);

        setDate();
        loadStory();
        loadTitleImage();
    }

    /**
     * 加载故事模块
     */
    final static int LOAD_STORY = 1;
    private void loadStory(){

        mMainNewsAdapter = new MainNewsAdapter(mLatest.getStories());

        Message message = ThreadUtil.initMessage(mMainNewsAdapter,LOAD_STORY);
        mHandler.sendMessage(message);
    }

    /**
     * 建立日期
     */
    final static int SET_DATE = 2;
    private void setDate(){
        Message message = ThreadUtil.initMessage(mLatest.getData(),SET_DATE);
        mHandler.sendMessage(message);
    }

    /**
     * 建立标题的Image
     */
    final static int LOAD_TITLE_IMAGE = 3;
    private void loadTitleImage(){

        MainTitleViewAdapter adapter = new MainTitleViewAdapter(getContext(),mLatest.getTop_stories());

        Message message = ThreadUtil.initMessage(adapter,LOAD_TITLE_IMAGE);
        mHandler.sendMessage(message);
    }

    /**
     * 计算日期
     * @param defaultDate  默认的日期输入数据
     * @return 日期字符串
     */
    private String calculatorDate(String defaultDate){

        int date = Integer.valueOf(defaultDate);
        //计算日期
        String day = date % 100 + "";
        date /= 100;

        //计算月份
        String month = date % 100 + "";
        date /= 100;

        //计算年份
        String year = date + "";

        return year + "年" + month + "月" + day + "日";
    }

    static class Handler extends SafeHandler<MainFragment>{

        Handler(MainFragment object) {
            super(object);
        }

        @Override
        public void runInThread(MainFragment outerClass, Message msg) {
            switch (msg.what){
                case LOAD_STORY:
                    outerClass.rv_content.setAdapter((MainNewsAdapter)msg.obj);
                    break;
                case SET_DATE:
                    outerClass.tv_data.setText(outerClass.calculatorDate((String)msg.obj));
                    break;
                case LOAD_TITLE_IMAGE:
                    outerClass.cvp_titleImage.setAdapter((MainTitleViewAdapter)msg.obj);
                    outerClass.ci_viewGround.bindViewPager(outerClass.cvp_titleImage);
                    break;
            }
        }
    }

}

