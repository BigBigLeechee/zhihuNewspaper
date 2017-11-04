package com.example.billy.zhihunewspaper_reset.client.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.billy.zhihunewspaper_reset.R;
import com.example.billy.zhihunewspaper_reset.client.activity.activityInterface.OnFragmentChoose;
import com.example.billy.zhihunewspaper_reset.client.adapter.MoreThemeAdapter;
import com.example.billy.zhihunewspaper_reset.client.base.BaseActivity;
import com.example.billy.zhihunewspaper_reset.client.base.Thread.SafeHandler;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BaseTheme.ThemeBaseFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BaseTheme.ThemeID;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BigBusinessFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.CartoonNewsFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.DesignNewsFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.EconomicsNewsFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.EverydayPsychologyFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.FilmNewsFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.InternetSafeFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.MainFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.MusicNewsFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.NotBodingFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.PhysicalNewsFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.RecommendFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.StartGameFragment;
import com.example.billy.zhihunewspaper_reset.model.bean.Themes.Themes;
import com.example.billy.zhihunewspaper_reset.model.network.URLManager;
import com.example.billy.zhihunewspaper_reset.model.util.NetworkUtil;
import com.example.billy.zhihunewspaper_reset.model.util.ThreadUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_main_moreTheme)
    ImageView iv_moreTheme;
    @BindView(R.id.rl_main_moreThemeView)
    RelativeLayout rl_moreThemeView;

    @BindView(R.id.dl_main_drawerView)
    DrawerLayout dl_drawView;


    @BindView(R.id.rv_moreTheme_theme)
    RecyclerView rv_theme;

    @BindView(R.id.tv_main_title)
    TextView tv_title;
    @BindView(R.id.tv_moreTheme_username)
    TextView tv_username;
    @BindView(R.id.tv_moreTheme_currentTheme)
    TextView tv_currentTheme;

    @BindView(R.id.iv_moreTheme_profilePhoto)
    ImageView iv_profilePhoto;

    private MainFragment mMainFragment;

    private SparseArray<ThemeBaseFragment> mThemeFragmentArray;

    private FragmentManager mFragmentManager;

    private MoreThemeAdapter mMoreThemeAdapter;

    private Themes mThemes;
    private Handler mHandler;


    @Override
    public void initParams(Bundle params) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        initFragment();
        initMoreThemeView();

        mHandler = new Handler(this);

        //建立主题Fragment
        initThemeFragment();

    }

    @Override
    public void setListener() {
        iv_moreTheme.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) throws Exception {
        switch (v.getId()) {
            case R.id.iv_main_moreTheme:
                dl_drawView.openDrawer(rl_moreThemeView);
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setThemeMessage();
    }

    /**
     * 初始化更多主题的界面
     */
    private void initMoreThemeView(){

        LinearLayoutManager manager = new LinearLayoutManager(this){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {

                RecyclerView.LayoutParams params = new RecyclerView.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, 50);

                params.topMargin  = 10;
                return params;
            }
        };

        rv_theme.setLayoutManager(manager);
    }

    /**
     * 初始化Fragment
     */
    private void initFragment(){

        mMainFragment = new MainFragment();

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fragment_main, mMainFragment).commit();


    }

    /**
     * 获取主题信息
     */
    private void setThemeMessage(){
        NetworkUtil.get(URLManager.themes_url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                initInBackground(response.body().string());
            }
        });
    }

    /**
     * 在后台初始化数据
     * @param json  Json格式的数据
     */
    private void initInBackground(String json){
        mThemes = NetworkUtil.parseJson(json, Themes.class);

        setMoreThemeViewData();
    }

    /**
     * 建立更多主题的数据
     */
    final static int LOAD_MORE_THEME_VIEW = 1;
    private void setMoreThemeViewData(){
        mMoreThemeAdapter = new MoreThemeAdapter(mThemes.getOthers());

        mMoreThemeAdapter.setOnFragmentChoose(new OnFragmentChoose() {
            @Override
            public void onFragmentChoose(int id) {
                Log.i(TAG,"run" + id);

                //若该ID没有对象则返回错误信息
                if(null == mThemeFragmentArray.get(id)){
                    Log.i(TAG,"Null Fragment " + id);
                    return;
                }

                //改变Fragment的内容
                mFragmentManager.beginTransaction().replace(R.id.fragment_main,
                        mThemeFragmentArray.get(id)).commit();

                //关闭侧滑栏
                dl_drawView.closeDrawers();
            }
        });

        Message message = ThreadUtil.initMessage(mMoreThemeAdapter,LOAD_MORE_THEME_VIEW);
        mHandler.sendMessage(message);
    }


    /**
     * 初始化ThemeFragment的内容
     */
    private void initThemeFragment(){
        mThemeFragmentArray = new SparseArray<>();

        //建立信息之间的关系
        mThemeFragmentArray.put(ThemeID.EVERYDAY_PSYCHOLOGY_ID,new EverydayPsychologyFragment());
        mThemeFragmentArray.put(ThemeID.RECOMMEND_ID,new RecommendFragment());
        mThemeFragmentArray.put(ThemeID.INTERNET_SAFE_ID,new InternetSafeFragment());
        mThemeFragmentArray.put(ThemeID.CARTOON_NEWS_ID,new CartoonNewsFragment());
        mThemeFragmentArray.put(ThemeID.MUSIC_NEWS_ID,new MusicNewsFragment());
        mThemeFragmentArray.put(ThemeID.BIG_BUSINESS_ID,new BigBusinessFragment());
        mThemeFragmentArray.put(ThemeID.START_GAME_ID,new StartGameFragment());
        mThemeFragmentArray.put(ThemeID.FILM_NEWS_ID,new FilmNewsFragment());
        mThemeFragmentArray.put(ThemeID.NOT_BODING_ID,new NotBodingFragment());
        mThemeFragmentArray.put(ThemeID.PHYSICAL_NEWS_ID,new PhysicalNewsFragment());
        mThemeFragmentArray.put(ThemeID.DESIGN_NEWS_ID,new DesignNewsFragment());
        mThemeFragmentArray.put(ThemeID.ECONOMICS_NEWS_ID,new EconomicsNewsFragment());
    }

    static class Handler extends SafeHandler<MainActivity>{

        Handler(MainActivity object) {
            super(object);
        }

        @Override
        public void runInThread(MainActivity outerClass, Message msg) {
            switch (msg.what){
                case LOAD_MORE_THEME_VIEW:
                    outerClass.rv_theme.setAdapter((MoreThemeAdapter)msg.obj);
                    break;
            }

        }
    }

}
