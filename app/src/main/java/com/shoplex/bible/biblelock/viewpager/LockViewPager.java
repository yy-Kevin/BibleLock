package com.shoplex.bible.biblelock.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by qsk on 2017/4/7.
 */

public class LockViewPager extends ViewPager {

    private static final String TAG = "LockViewPager";

    /**
     * 上一次x坐标
     */
    private float beforeX;

    public LockViewPager(Context context) {
        super(context);
    }

    public LockViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    private boolean isCanScroll = true;
    private int position = 0;

    //----------禁止左右滑动------------------
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (isCanScroll) {
//            return super.onTouchEvent(ev);
//        } else {
//            return false;
//        }
//
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent arg0) {
//        // TODO Auto-generated method stub
//        if (isCanScroll) {
//            return super.onInterceptTouchEvent(arg0);
//        } else {
//            return false;
//        }
//
//    }

//-------------------------------------------


    //-----禁止左滑-------左滑：上一次坐标 > 当前坐标
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,"yuyao dis 1111 ");
        Log.i(TAG,"yuyao dis isCanScroll  =" + isCanScroll);
        Log.i(TAG,"yuyao dis getCurrentItem()  =" + getCurrentItem());

        if (getCurrentItem() == 0) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    beforeX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG,"yuyao dis 2222");

                    float motionValue = ev.getX() - beforeX;
                    if (motionValue < 0) {//不能禁止左滑
                        Log.i(TAG,"yuyao dis 3333");

                        getParent().requestDisallowInterceptTouchEvent(true);
                        return super.dispatchTouchEvent(ev);
                    }
                    beforeX = ev.getX();
                    break;
            }
            Log.i(TAG,"yuyao dis 4444");

            return super.dispatchTouchEvent(ev);
        } else if (getCurrentItem() != 0){
            Log.i(TAG,"yuyao dis 5555");

            getParent().requestDisallowInterceptTouchEvent(true);
            return super.dispatchTouchEvent(ev);
        }
        Log.i(TAG,"yuyao dis 6666");

        return super.dispatchTouchEvent(ev);

    }


    public boolean isScrollble() {
        return isCanScroll;
    }

    /**
     * 设置 是否可以滑动
     *
     * @param isCanScroll
     */
    public void setScrollble(boolean isCanScroll, int position) {
        this.position = position;
        this.isCanScroll = isCanScroll;
    }
}
