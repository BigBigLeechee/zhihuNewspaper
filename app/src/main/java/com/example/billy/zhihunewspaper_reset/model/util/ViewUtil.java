package com.example.billy.zhihunewspaper_reset.model.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Billy on 2017/10/29.
 *
 * 场景的工具
 */

public class ViewUtil {

    public static void startActivity(Context context, Class<?> cls, @Nullable Bundle bundle){
        //开启Activity
        startActivityWithoutFinishOldActivity(context,cls,bundle);
        //消除旧的界面
        ((AppCompatActivity)context).finish();
    }


    public static void startActivityWithoutFinishOldActivity(Context context,Class<?> cls,
                                                             @Nullable Bundle bundle){
        //开启新的界面
        Intent intent = new Intent();
        intent.setClass(context,cls);
        //加入信息
        if (bundle != null){
            intent.putExtras(bundle);
        }
        //跳转页面
        context.startActivity(intent);
    }

    private ViewUtil(){
    }
}
