package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.view.View;

/**
 * Created by qsk on 2017/4/7.
 */

public abstract class BaseLockScreenViewPager {

    public View mRootView;
    public Activity mContent;

    public BaseLockScreenViewPager(Activity context){
        this.mContent = context;
        mRootView = initView();
    }

    public abstract View initView();
    public void initFragment(){};
}
