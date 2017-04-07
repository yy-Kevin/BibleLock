package com.shoplex.bible.biblelock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.shoplex.bible.biblelock.databinding.ActivityLockBinding;
import com.shoplex.bible.biblelock.viewpager.LockScreenViewPager0;
import com.shoplex.bible.biblelock.viewpager.LockScreenViewpager;
import com.shoplex.bible.biblelock.viewpager.LockScreenViewpager1;
import com.shoplex.bible.biblelock.viewpager.LockScreenViewpager3;
import com.shoplex.bible.biblelock.viewpager.LockScreenViewpager4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by qsk on 2017/3/27.
 */

public class LockScreenActivity extends SwipeBackActivity {

    private static final String TAG = "LockScreenActivity";
    private ActivityLockBinding binding;
    private ViewPager viewPager;
    private ArrayList<LockScreenViewPager0> arrayList;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        setContentView(R.layout.activity_lock);
        getSwipeBackLayout().setSwipeMode(SwipeBackLayout.FULL_SCREEN_LEFT);
//        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.FULL_SCREEN_LEFT);
//        getSwipeBackLayout().setSwipeMode(SwipeBackLayout.EDGE_ALL);

        viewPager = (ViewPager) findViewById(R.id.vp_viewpager_lock);
        initData();
        viewPager.setAdapter(new LockScreenAdapter());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);

//        String str1 = formatDataForDisplay("2001.12.12-08:23:21");
        Log.i(TAG,"yuyao str = " + str );
//        Log.i(TAG,"yuyao str1 = " + str1 );

    }

    private void initData(){

        arrayList = new ArrayList();
        arrayList.add(new LockScreenViewpager1(this));
        arrayList.add(new LockScreenViewpager(this));
        arrayList.add(new LockScreenViewpager3(this));
        arrayList.add(new LockScreenViewpager4(this));

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        Log.i(TAG,"back key = " + key);

        switch (key) {
            case KeyEvent.KEYCODE_BACK: {
                Log.i(TAG,"back");

                return true;
            }
            case KeyEvent.KEYCODE_MENU:{
                Log.i(TAG,"menu");
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
    }

    private class LockScreenAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            LockScreenViewPager0 view = arrayList.get(position);
            View rootView = view.initView();
            container.addView(rootView);
            Log.i(TAG,"yuyao instantiateitem");
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View)object);

        }
    }
}
