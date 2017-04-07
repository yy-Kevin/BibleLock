package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qsk on 2017/4/6.
 */

public class LockScreenViewpager4 extends LockScreenViewPager0{


    public LockScreenViewpager4(Activity context){
        super(context);

    }
    public View initView(){
        TextView textView = new TextView(mContent);
        textView.setText("hehe");
        textView.setGravity(Gravity.CENTER);
        return  textView;
    }
}
