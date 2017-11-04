package com.example.billy.zhihunewspaper_reset.client.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.billy.zhihunewspaper_reset.R;


/**
 * Created by Billy on 2017/10/30.
 *
 * ViewPager 的圆点指示器
 */

public class CircleIndicator extends LinearLayout {

    private ViewPager mViewPager;

    private int mLastPosition = -1;

    private int mAnimatorResId = R.anim.scale_with_alpha;

    private int mSelectBackgroundResId = 0;
    private int mUnSelectBackgroundResId = 0;

    private int mIndicatorWidth = 10;
    private int mIndicatorHeight = 10;

    private int mIndicatorMargin = 10;

    private Animator mAnimatorBigger;
    private Animator mAnimatorRefuse;

    private Animator mAnimatorImmediateBigger;
    private Animator mAnimatorImmediateRefuse;

    public CircleIndicator(Context context) {
        super(context);

        init(context);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }


    /**
     * 初始化
     */
    private void init(Context context){
        //建立动画
        mAnimatorBigger = createAnimatorBigger(context);
        mAnimatorRefuse = createAnimatorRefuse(context);

        //建立立即完成的动画
        mAnimatorImmediateBigger = createAnimatorBigger(context);
        mAnimatorImmediateBigger.setDuration(0);

        mAnimatorImmediateRefuse = createAnimatorRefuse(context);
        mAnimatorImmediateRefuse.setDuration(0);

        //建立选择中的圆点的图片
        mSelectBackgroundResId = mSelectBackgroundResId == 0 ?
                R.drawable.circle_indicator : mSelectBackgroundResId;

        //建立未选择中的圆点的图片
        mUnSelectBackgroundResId = mUnSelectBackgroundResId == 0 ?
                R.drawable.circle_indicator : mUnSelectBackgroundResId;


    }

    /**
     * 建立数据
     *
     * @param indicatorHeight   指示器的高度
     * @param indicatorWidth    指示器的宽度
     * @param indicatorMargin   指示器之间的距离
     */
    public void setParams(int indicatorHeight,int indicatorWidth,int indicatorMargin){

        if(indicatorHeight > 0){
            mIndicatorHeight = indicatorHeight;
        }

        if (indicatorWidth > 0){
            mIndicatorWidth = indicatorWidth;
        }

        if(indicatorMargin > 0){
            mIndicatorMargin = indicatorMargin;
        }

    }

    /**
     * 建立圆点放大的动画
     * @return  圆点放大的动画
     */
    @SuppressLint("ResourceType")
    private Animator createAnimatorBigger(Context context){
        return AnimatorInflater.loadAnimator(context,mAnimatorResId);
    }

    /**
     * 建立圆点缩小的动画
     * @return   圆点缩小的动画
     */
    private Animator createAnimatorRefuse(Context context){
        @SuppressLint("ResourceType")
        Animator animatorRefuse = AnimatorInflater.loadAnimator(context,mAnimatorResId);
        animatorRefuse.setInterpolator(new ReverseInterpolator());

        return animatorRefuse;
    }

    /**
     * 绑定ViewPager
     * @param viewPager  需要圆点指示器的ViewPager
     */
    public void bindViewPager(ViewPager viewPager){
        mViewPager = viewPager;

        if(mViewPager != null && mViewPager.getAdapter() != null){
            mLastPosition = -1;
            createIndicator();

            //建立监听器
            mViewPager.removeOnPageChangeListener(mInternalPagerChangeListener);
            mViewPager.addOnPageChangeListener(mInternalPagerChangeListener);

            //首先先完成当前显示的界面的信息
            mInternalPagerChangeListener.onPageSelected(mViewPager.getCurrentItem());
        }

    }

    /**
     * ViewPager 的监听事件
     * 监听当页面发生变动的情况
     */
    private final ViewPager.OnPageChangeListener mInternalPagerChangeListener =
            new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    //页面不存在则退出
                    if (mViewPager.getAdapter() == null && mViewPager.getAdapter().getCount() <= 0) {
                        return;
                    }

                    //去除正在运行的动画
                    if (mAnimatorBigger.isRunning()) {
                        mAnimatorBigger.end();
                        mAnimatorBigger.cancel();
                    }

                    if (mAnimatorRefuse.isRunning()) {
                        mAnimatorRefuse.end();
                        mAnimatorRefuse.cancel();
                    }

                    //若是过去的Position则恢复圆点的大小
                    View currentIndicator;
                    if (mLastPosition >= 0 && (currentIndicator = getChildAt(mLastPosition)) != null) {
                        currentIndicator.setBackgroundResource(mUnSelectBackgroundResId);
                        //开始动画
                        mAnimatorRefuse.setTarget(currentIndicator);
                        mAnimatorRefuse.start();
                    }

                    View selectedIndicator = getChildAt(position);
                    if (selectedIndicator != null) {
                        selectedIndicator.setBackgroundResource(mSelectBackgroundResId);
                        //开始动画
                        mAnimatorBigger.setTarget(selectedIndicator);
                        mAnimatorBigger.start();
                    }

                    mLastPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };

    /**
     * 建立圆点指示器
     */
    private void createIndicator(){
        //去除所有的View
        removeAllViews();

        //获取场景的总数
        int viewCount = mViewPager.getAdapter().getCount();
        if(viewCount <= 0){
            return;
        }

        //获取当前的焦点的Item
        int currentItem = mViewPager.getCurrentItem();
        //获取当前的Layout的布局模式
        int orientation = getOrientation();

        for(int i = 0;i < viewCount;i++){
            //建立相对应的圆点
            if(currentItem == i){
                addIndicator(orientation,mSelectBackgroundResId,mAnimatorImmediateBigger);
            }else{
                addIndicator(orientation, mUnSelectBackgroundResId,mAnimatorImmediateRefuse);
            }
        }

    }

    /**
     * 增加圆点指示器
     *
     * @param orientation  场景的布局方式
     * @param backgroundDrawableResId  圆点的Drawable
     * @param animator 需要进行的动画
     */
    private void addIndicator(int orientation, @DrawableRes int backgroundDrawableResId,
                              Animator animator){

        //去除正在运行的动画
        if(animator.isRunning()){
            animator.end();
            animator.cancel();
        }

        //建立指示器
        View indicator = new View(getContext());
        indicator.setBackgroundResource(backgroundDrawableResId);

        //将指示器加入到场景当中
        addView(indicator,mIndicatorWidth,mIndicatorHeight);

        LayoutParams params = (LayoutParams) indicator.getLayoutParams();

        //根据不同的排列方式建立不同的距离
        if (orientation == HORIZONTAL) {
            params.leftMargin = mIndicatorMargin;
            params.rightMargin = mIndicatorMargin;
        } else {
            params.topMargin = mIndicatorMargin;
            params.bottomMargin = mIndicatorMargin;
        }
        //图片居中
        params.gravity = Gravity.CENTER;

        //将建立好的数据加入到indicator当中
        indicator.setLayoutParams(params);

        //开始进行动画
        animator.setTarget(indicator);
        animator.start();

    }

    /**
     * 使得动画作用相反的Interpolator
     */
    private class ReverseInterpolator implements TimeInterpolator {
        @Override
        public float getInterpolation(float v) {
            return Math.abs(1 - v);
        }
    }
}
