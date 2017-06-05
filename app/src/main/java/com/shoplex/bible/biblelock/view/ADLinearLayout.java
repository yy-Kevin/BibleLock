package com.shoplex.bible.biblelock.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by qsk on 2017/5/17.
 */

public class ADLinearLayout extends LinearLayout {
    public ADLinearLayout(Context context) {
        super(context);
    }

    public ADLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ADLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


}
