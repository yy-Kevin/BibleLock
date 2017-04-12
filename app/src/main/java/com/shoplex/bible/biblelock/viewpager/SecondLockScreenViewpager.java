package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.shoplex.bible.biblelock.LockScreenActivity;
import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.fragment.Verseragment;
import com.shoplex.bible.biblelock.fragment.WallpageFragment;

/**
 * Created by qsk on 2017/4/6.
 */

public class SecondLockScreenViewpager extends BaseLockScreenViewPager implements View.OnClickListener {


    private TextView tv_wallpaper;
    private TextView tv_verse;
    private Drawable drawable;
    private Verseragment verseragment;
    private WallpageFragment wallpageFragment;
    private LockScreenActivity activity;

    public SecondLockScreenViewpager(Activity context) {
        super(context);

    }

    public View initView() {
        View view = View.inflate(mContent, R.layout.viewpager_second, null);
        activity = (LockScreenActivity) mContent;

        tv_wallpaper = (TextView) view.findViewById(R.id.tv_wallpaper);
        tv_verse = (TextView) view.findViewById(R.id.tv_verse);

        tv_verse.setOnClickListener(this);
        tv_wallpaper.setOnClickListener(this);

        return view;
    }

    public void initFragment() {

        verseragment = new Verseragment();

        activity.getFragmentManager().beginTransaction().replace(R.id.fl_lock_twopager, verseragment).commit();
    }

    @Override
    public void onClick(View v) {
        Drawable drawable = mContent.getResources().getDrawable(R.drawable.select_process);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        FragmentManager fm = activity.getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.tv_verse:
                tv_verse.setCompoundDrawables(null, null, null, drawable);
                tv_wallpaper.setCompoundDrawables(null, null, null, null);

                if (verseragment == null) {
                    verseragment = new Verseragment();
                }
                transaction.replace(R.id.fl_lock_twopager, verseragment);
                break;

            case R.id.tv_wallpaper:
                tv_wallpaper.setCompoundDrawables(null, null, null, drawable);
                tv_verse.setCompoundDrawables(null, null, null, null);

                if (wallpageFragment == null) {
                    wallpageFragment = new WallpageFragment();
                }
                transaction.replace(R.id.fl_lock_twopager, wallpageFragment);

                break;
        }

        // 事务提交
        transaction.commit();
    }
}
