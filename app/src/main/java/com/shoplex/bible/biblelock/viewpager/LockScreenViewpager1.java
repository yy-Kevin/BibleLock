package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qsk on 2017/4/6.
 */

public class LockScreenViewpager1  extends LockScreenViewPager0{


    public LockScreenViewpager1(Activity context){
        super(context);

    }
    public View initView(){
        TextView textView = new TextView(mContent);
        textView.setText("hehe11");
        textView.setGravity(Gravity.CENTER);
        return  textView;
    }
}
