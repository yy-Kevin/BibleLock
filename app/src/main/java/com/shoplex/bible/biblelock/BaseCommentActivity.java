package com.shoplex.bible.biblelock;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shoplex.bible.biblelock.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qsk on 2017/4/14.
 */

public class BaseCommentActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "BaseCommentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public List<LinearLayout> getData(LinearLayout linearLayout) {
        //从网络获取的 评论集合数据
        ArrayList<LinearLayout> list = new ArrayList();
        for (int i = 0; i < 5; i++) {

            View view = View.inflate(this, R.layout.comment_item,null);
            linearLayout.addView(view);
            list.add((LinearLayout) view);
        }
        return list;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_send:
                this.sendComment();
                break;
            case R.id.tv_text_share:
                TimeUtils.shareMsg(this,"","","","");
                break;
            case R.id.tv_text_comment:
                break;

        }
    }

    public void sendComment(){
        Log.i(TAG,"yuyao sendComment");
    }

}
