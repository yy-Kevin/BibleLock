package com.shoplex.bible.biblelock;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.shoplex.bible.biblelock.fragment.DrawerFragment;
import com.shoplex.bible.biblelock.fragment.ImageFragment;
import com.shoplex.bible.biblelock.fragment.TextFragment;
import com.shoplex.bible.biblelock.server.ServiceActivity;
import com.shoplex.bible.biblelock.utils.FaceBookShareUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ImageFragment imageFragment;
    private TextFragment textFragment;
    private Button btv_image;
    private Button btv_text;
    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private DrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        if (savedInstanceState == null) {
            initFragment();
        }
        Intent intent = new Intent(this,ServiceActivity.class);
        startService(intent);

        btv_image.setOnClickListener(this);
        btv_text.setOnClickListener(this);

        btv_image.setSelected(true);
        btv_text.setSelected(false);
    }


    private void initView(){
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btv_image = (Button) findViewById(R.id.btv_image);
        btv_text = (Button) findViewById(R.id.btv_text);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        //初始化抽屉布局
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initFragment(){
        imageFragment = new ImageFragment();
        drawerFragment = new DrawerFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_content, imageFragment).commit();
        getFragmentManager().beginTransaction().replace(R.id.left, drawerFragment).commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.btv_image:
                btv_image.setSelected(true);
                btv_text.setSelected(false);
                if (imageFragment == null) {
                    imageFragment = new ImageFragment();
                }
                transaction.replace(R.id.fl_content, imageFragment);
                break;
            case R.id.btv_text:
                btv_image.setSelected(false);
                btv_text.setSelected(true);
                if (textFragment == null) {
                    textFragment = new TextFragment();
                }
                transaction.replace(R.id.fl_content, textFragment);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        Log.i(TAG,"item.getItemId() +" + item.getItemId());
        switch (item.getItemId()){
            case 16908332:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
        return true;
    }
}
