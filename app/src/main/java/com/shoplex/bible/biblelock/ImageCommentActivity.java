package com.shoplex.bible.biblelock;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.databinding.ActivityImageCommentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qsk on 2017/4/6.
 */

public class ImageCommentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ImageCommentActivity";
    private ActivityImageCommentBinding binding;
    private List<LinearLayout> list;
    private View view;
    private TextView comment;
    private List<LinearLayout> filpperList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_comment);
        getData();
        initActionBar();
        binding.commentSend.setOnClickListener(this);

        binding.vfViewfilpper.setInAnimation(this, R.anim.push_up_in);
        binding.vfViewfilpper.setOutAnimation(this, R.anim.push_up_out);
        binding.vfViewfilpper.startFlipping();

        initViewFilpper();
        getDate();
    }
    private List<LinearLayout> initViewFilpper(){
        filpperList = new ArrayList<LinearLayout>();

        for (int i = 0; i < 2; i++) {
            LinearLayout fl = (LinearLayout) new LinearLayout(this);
//            tv.setText("这是测试用的第 " + i + i + i + i + " 行测试数据：");
            View view = View.inflate(this,R.layout.comment_item,null);
            fl.addView(view);
            filpperList.add(fl);
        }

        return  filpperList;
    }

    private void getDate(){
        int size = filpperList.size();
        for (int i = 0; i < size; i++) {

            binding.vfViewfilpper.addView(filpperList.get(i));
        }
    }
    private void initActionBar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    private List<LinearLayout> getData() {

        //从网络获取的 评论集合数据

        list = new ArrayList();
        for (int i = 0; i < 5; i++) {

            View view = View.inflate(this, R.layout.comment_item,null);
            comment = (TextView) view.findViewById(R.id.tv_image_comment_textview);
            binding.lvCommentImage.addView(view);
            list.add((LinearLayout) view);
        }
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        Log.i(TAG, "item.getItemId() +" + item.getItemId());
        switch (item.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return true;
    }

    /**
     * 发送评论
     */
    public void sendComment() {
        if (binding.commentContent.getText().toString().equals("")) {
            Toast.makeText(this, "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 生成评论数据
            Comment comment = new Comment();
            comment.setName("评论者" + (list.size() + 1) + "：");
            comment.setContent(binding.commentContent.getText().toString());


            View view2 = View.inflate(this, R.layout.comment_item,null);

            TextView comment2 = (TextView) view2.findViewById(R.id.tv_image_comment_textview);
            comment2.setText(binding.commentContent.getText().toString());
            binding.lvCommentImage.addView(view2);

            // 发送完，清空输入框
            binding.commentContent.setText("");
            Toast.makeText(this, "评论成功！", Toast.LENGTH_SHORT).show();
        }
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
        switch (v.getId()){
            case R.id.comment_send:
                sendComment();
                break;

        }
    }
}
