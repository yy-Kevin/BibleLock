package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by qsk on 2017/4/7.
 */

public abstract class LockScreenViewPager0 {

    public View mRootView;
    public Activity mContent;

    public  LockScreenViewPager0(Activity context){
        this.mContent = context;
        mRootView = initView();
    }

    public abstract View initView();
}
