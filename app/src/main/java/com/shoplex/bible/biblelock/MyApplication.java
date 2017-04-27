package com.shoplex.bible.biblelock;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by qsk on 2017/4/17.
 */

public class MyApplication extends Application{

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
    }



    public static MyApplication getInstance() {
        return instance;
    }
}
