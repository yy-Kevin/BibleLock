package com.shoplex.bible.biblelock.viewpager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by qsk on 2017/4/13.
 */

public class LockImageview extends ImageView {
    private static final String TAG = "LockImageview";

    public LockImageview(Context context) {
        super(context);
    }

    public LockImageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LockImageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LockImageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG,"yuyao  lock dispatchTouchEvent");
        return false;
    }
}
