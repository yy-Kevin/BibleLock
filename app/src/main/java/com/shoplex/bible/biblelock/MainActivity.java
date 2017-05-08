package com.shoplex.bible.biblelock;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.shoplex.bible.biblelock.databinding.ActivityMainBinding;
import com.shoplex.bible.biblelock.fragment.ImageFragment;
import com.shoplex.bible.biblelock.fragment.TextFragment;
import com.shoplex.bible.biblelock.server.ServiceActivity;
import com.shoplex.bible.biblelock.utils.SharedPreferencesUtils;
import com.shoplex.bible.biblelock.utils.ToastUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ImageFragment imageFragment;
    private TextFragment textFragment;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private ImageView ib_toolbar;
    private RotateAnimation operatingAnim;
    private boolean isExit;
    private ActivityMainBinding binding;
    private NativeAd nativeAd;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();

        if (savedInstanceState == null) {
            initFragment();
        }

        //判断是否开启锁屏服务
        intent = new Intent(MainActivity.this, ServiceActivity.class);
        boolean isChecked = (boolean) SharedPreferencesUtils.get(MainActivity.this, "isChecked", false);
        if (isChecked) {
            startService(intent);
        } else {
            stopService(intent);
        }


        binding.btvImage.setOnClickListener(this);
        binding.btvText.setOnClickListener(this);
        ib_toolbar.setOnClickListener(this);
        startAnimation(ib_toolbar);

        binding.btvImage.setSelected(true);
        binding.btvText.setSelected(false);
    }


    private void initView() {
//        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        ib_toolbar = (ImageView) toolbar.findViewById(R.id.ib_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        //在二级界面等Activity中，通过如下设置可以在Toolbar左边显示一个返回按钮：
        actionBar.setDisplayHomeAsUpEnabled(true);
        //去掉第一个界面的 toolbar返回按钮
        toolbar.setNavigationIcon(null);
        actionBar.setTitle("");
        // 通过代码的方式 给三个小点 换图标
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.menu_icon));
//
//        //初始化抽屉布局
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.drawer_open, R.string.drawer_close);
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initFragment() {
        imageFragment = new ImageFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_content, imageFragment).commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.btv_image:
                binding.btvImage.setSelected(true);
                binding.btvText.setSelected(false);
                if (imageFragment == null) {
                    imageFragment = new ImageFragment();
                }
                transaction.replace(R.id.fl_content, imageFragment);
                isExit = false;
                break;
            case R.id.btv_text:
                binding.btvImage.setSelected(false);
                binding.btvText.setSelected(true);
                if (textFragment == null) {
                    textFragment = new TextFragment();
                }
                transaction.replace(R.id.fl_content, textFragment);
                isExit = false;
                break;
            case R.id.ib_toolbar:

                View view = showPopwindowad(R.layout.popupwindow_ad,Gravity.CENTER);
                showNativeAd(view);

                break;
        }
        // 事务提交
        transaction.commit();
    }
    public void showNativeAd(final View view) {
        Log.i(TAG,"1848266248745233  ");

        nativeAd = new NativeAd(this, "YOUR_PLACEMENT_ID");
        nativeAd.loadAd();

        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback

                Log.i(TAG,"onError onError " + error.toString());

            }

            @Override
            public void onAdLoaded(Ad ad) {

                Log.i(TAG,"1848266248745233 1848266248745233 ");
                if (nativeAd != null ) {
                    nativeAd.unregisterView();
                }

                // Add the Ad view into the ad container.
                LinearLayout nativeAdContainer = (LinearLayout) view.findViewById(R.id.native_ad_container);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.activity_ad, nativeAdContainer, false);
                nativeAdContainer.addView(adView);

                // Create native UI using the ad metadata.
                ImageView nativeAdIcon = (ImageView) adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = (TextView) adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = (MediaView) adView.findViewById(R.id.native_ad_media);
                TextView nativeAdSocialContext = (TextView) adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = (TextView) adView.findViewById(R.id.native_ad_body);
                Button nativeAdCallToAction = (Button) adView.findViewById(R.id.native_ad_call_to_action);

                // Set the Text.
                nativeAdTitle.setText(nativeAd.getAdTitle());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdBody.setText(nativeAd.getAdBody());
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());

                // Download and display the ad icon.
                NativeAd.Image adIcon = nativeAd.getAdIcon();
                NativeAd.downloadAndDisplayImage(adIcon, nativeAdIcon);

                // Download and display the cover image.
                nativeAdMedia.setNativeAd(nativeAd);

                // Add the AdChoices icon
                LinearLayout adChoicesContainer = (LinearLayout) adView.findViewById(R.id.ad_choices_container);
                AdChoicesView adChoicesView = new AdChoicesView(MainActivity.this, nativeAd, true);
                adChoicesContainer.addView(adChoicesView);

                // Register the Title and CTA button to listen for clicks.
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);
                nativeAd.registerViewForInteraction(nativeAdContainer,clickableViews);

            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.i(TAG,"onAdClicked onAdClicked ");
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.i(TAG,"onLoggingImpression onLoggingImpression ");


            }
        });

//        nativeAd.loadAd(NativeAd.MediaCacheFlag.ALL);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        //怎么改啊 nilai

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            // 星星评论
            case R.id.action_setting:
                View view = showRateUsPopwindow(R.layout.popwindow_rateus, Gravity.CENTER);
                RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
                Object rating = SharedPreferencesUtils.get(MainActivity.this, "RATING", (float) 0);
                ratingBar.setRating((float) rating);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        ToastUtil.showToast(MainActivity.this, "评价了" + rating + "星");
                        SharedPreferencesUtils.put(MainActivity.this, "RATING", rating);
                    }
                });

                break;

            // 反馈信息
            case R.id.action_setting12:
                intent = new Intent(this, FeedBackActivity.class);
                startActivity(intent);
                break;
            // 设置信息
            case R.id.action_setting13:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    /**
     * 无限旋转的Button 点击弹出广告
     *
     * @param view
     */
    public void startAnimation(View view) {
        operatingAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        operatingAnim.setDuration(1000);
        operatingAnim.setRepeatCount(Animation.INFINITE);
        view.setAnimation(operatingAnim);
    }

    @Override
    protected void onDestroy() {
        if (operatingAnim != null) {
            operatingAnim.cancel();
            operatingAnim = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 在2秒内按下返回键两次才退出
     */
    private void exitBy2Click() {
        if (!isExit) {
            isExit = true;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.popwindow_exit, null);
            ToggleButton viewById = (ToggleButton) view.findViewById(R.id.switch1);

            boolean isChecked = (boolean) SharedPreferencesUtils.get(MainActivity.this,"isChecked",false);
            if (isChecked){
                viewById.setChecked(true);
            }else {
                viewById.setChecked(false);
            }

            showPopwindow(view, Gravity.BOTTOM);
            showNativeAd(view);

            viewById.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.i(TAG,"isChecked = " + isChecked);
                    if (isChecked){
                        startService(intent);
                        SharedPreferencesUtils.put(MainActivity.this,"isChecked",isChecked);
                    }else {
                        stopService(intent);
                        SharedPreferencesUtils.put(MainActivity.this,"isChecked",isChecked);
                    }
                }
            });

        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 显示 退出的popupWindow
     */
    private View showPopwindow(View view, int gravity) {
        // 利用layoutInflater获得View
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(layout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        backgroundAlpha(0.5f);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(view,
                gravity, 0, 0);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        return view;
    }

    /**
     * 显示 广告的popupWindow
     */
    public View showPopwindowad(int layout, int gravity) {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        backgroundAlpha(0.5f);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(view,
                gravity, 0, 0);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        return view;
    }

    /**
     * 显示 评论的popupWindow
     */
    private View showRateUsPopwindow(int layout, int gravity) {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view);
        window.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        backgroundAlpha(0.5f);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(view,
                gravity, 0, 0);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
                System.out.println("popWindow消失");
            }
        });
        return view;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}

