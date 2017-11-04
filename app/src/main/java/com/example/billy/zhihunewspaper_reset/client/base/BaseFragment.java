package com.example.billy.zhihunewspaper_reset.client.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;


public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    View view;
    LayoutInflater inflater;
    protected final String TAG = this.getClass().getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        initParams(bundle);
        view = bindView(inflater);
        Fresco.initialize(view.getContext());

        initView(view);
        setListener();
        doBusiness();

        return view;
    }
    protected abstract void initParams(Bundle bundle);

    protected abstract View bindView(LayoutInflater inflater);

    protected abstract void initView(final View view);

    protected abstract void setListener();

    protected abstract void widgetClick(View v);

    protected abstract void doBusiness();

    /**
     * [绑定控件]
     *
     * @param resId
     *
     * @return
     */
    protected <T extends View> T $(int resId) {
        return (T) view.findViewById(resId);
    }

    protected void showToast(String msg) {
        Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getContext(),clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
