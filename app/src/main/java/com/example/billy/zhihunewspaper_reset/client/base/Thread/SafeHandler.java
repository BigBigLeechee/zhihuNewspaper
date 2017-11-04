package com.example.billy.zhihunewspaper_reset.client.base.Thread;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Billy on 2017/10/30.
 *
 * 安全的线程
 */

public abstract class SafeHandler<T> extends Handler {

    private WeakReference<T> mWeakObjectReference;

    public SafeHandler(T outerClass){
        mWeakObjectReference = new WeakReference<>(outerClass);
    }

    @Override
    public void handleMessage(Message msg) {
        T object = mWeakObjectReference.get();

        if(object != null){
            runInThread(object,msg);
        }
    }

    public abstract void runInThread(T outerClass,Message msg);
}
