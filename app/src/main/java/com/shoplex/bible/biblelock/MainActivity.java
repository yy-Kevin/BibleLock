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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;

import com.shoplex.bible.biblelock.databinding.ActivityMainBinding;
import com.shoplex.bible.biblelock.fragment.ImageFragment;
import com.shoplex.bible.biblelock.fragment.TextFragment;
import com.shoplex.bible.biblelock.server.ServiceActivity;
import com.shoplex.bible.biblelock.utils.DensityUtil;
import com.shoplex.bible.biblelock.utils.SharedPreferencesUtils;
import com.shoplex.bible.biblelock.utils.ToastUtil;
import com.zhy.autolayout.AutoLayoutActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        int i1 = DensityUtil.dip2px(this,360);
        int i2 = DensityUtil.dip2px(this,640);

        Log.i(TAG,"i1 ==========="  + i1 );
        Log.i(TAG,"i2 ==========="  + i2 );
        initView();
        if (savedInstanceState == null) {
            initFragment();
        }

        //判断是否开启锁屏服务
        Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
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
                Log.i(TAG, "yuyao ib_toolbar");
                ToastUtil.showToast(this, "此处是广告位");
                break;
        }
        // 事务提交
        transaction.commit();
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
            showPopwindow(R.layout.popwindow_exit, Gravity.BOTTOM);
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 显示 退出的popupWindow
     */
    private View showPopwindow(int layout, int gravity) {
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
                System.out.println("popWindow消失");
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

