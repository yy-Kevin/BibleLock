package com.shoplex.bible.biblelock;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shoplex.bible.biblelock.bean.Comment;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/4/6.
 */

public class ImageCommentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ImageCommentActivity";
    private LinearLayout linearlayout;
    private LinearLayout ll_linearlayout;
    private ArrayList<Comment> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_comment);

        linearlayout = (LinearLayout) findViewById(R.id.lv_comment_image);

//        arrayList = new ArrayList();
//        arrayList.add(new Comment());
//        arrayList.add(new Comment());
//        arrayList.add(new Comment());
//        arrayList.add(new Comment());
//        listview.setAdapter(new CommentAdapter());

//        linearlayout.addView();
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        Log.i(TAG, "item.getItemId() +" + item.getItemId());
        switch (item.getItemId()) {
            case 16908332:
                Log.i(TAG,"YUYAO ");
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && ll_linearlayout != null){
                    imm.hideSoftInputFromWindow(ll_linearlayout.getWindowToken(), 0);
                }
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }

        return onTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.ll_linearlayout:
//                Log.i(TAG,"YUYAO ");
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//                break;
//        }
    }

    private class CommentAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(ImageCommentActivity.this,R.layout.comment_image_item,null);
            ImageView comment_image_item = (ImageView) view.findViewById(R.id.comment_image_item);
            TextView comemnt_text_item = (TextView) view.findViewById(R.id.comemnt_text_item);

            comemnt_text_item.setText("aaaaa bbbbb cccc ddddd eeeee fffff gggggggg fffffff aaaaaa dddddddd dddddddddd dddddddddd ddddddddd");
            return view;
        }
    }
}
