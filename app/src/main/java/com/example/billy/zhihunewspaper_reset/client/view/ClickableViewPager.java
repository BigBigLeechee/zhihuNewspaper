package com.example.billy.zhihunewspaper_reset.client.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.billy.zhihunewspaper_reset.client.view.viewInterface.OnItemClickListener;

/**
 * Created by Billy on 2017/10/31.
 *
 * 拥有点击事件的ViewPager
 */

public class ClickableViewPager extends ViewPager {

    private OnItemClickListener onItemClickListener;

    public ClickableViewPager(Context context) {
        super(context);

        setupListener();
    }

    public ClickableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupListener();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    /**
     * 建立监听器
     */
    private void setupListener(){

        final GestureDetector tapGestureDetector = new GestureDetector(getContext(),new TapGestureDetector());

        setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tapGestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
    }

    //根据手势来建立点击事件
    private class TapGestureDetector extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(getCurrentItem());
            }

            return true;
        }
    }
}
