package com.example.billy.zhihunewspaper_reset.model.util;

import android.os.Message;

/**
 * Created by Billy on 2017/10/30.
 */

public class ThreadUtil {

    public static Message initMessage(Object obj,int what){
        Message msg = Message.obtain();

        msg.what = what;
        msg.obj = obj;

        return msg;
    }

    private ThreadUtil(){
    }
}
