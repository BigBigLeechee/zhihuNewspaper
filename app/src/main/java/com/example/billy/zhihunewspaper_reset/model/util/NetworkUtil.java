package com.example.billy.zhihunewspaper_reset.model.util;


import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Billy on 2017/10/29.
 *
 * 网络的工具
 */

public class NetworkUtil {

    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    public static void get(String url, Callback callback){

        Request request = new Request.Builder()
                .url(url).build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static <T> T parseJson(String json,Type cls){
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, cls);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    private NetworkUtil(){
    }
}
