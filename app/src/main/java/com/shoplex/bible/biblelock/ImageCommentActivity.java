package com.shoplex.bible.biblelock;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.databinding.ActivityImageCommentBinding;
import com.shoplex.bible.biblelock.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qsk on 2017/4/6.
 */

public class ImageCommentActivity extends BaseCommentActivity {

    private ActivityImageCommentBinding binding;
    private List<LinearLayout> filpperList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_comment);

        //加载评论
        getData(binding.llImageComment);
        initActionBar();
        binding.tvTextShare.setOnClickListener(this);
        binding.commentSend.setOnClickListener(this);
        binding.tvImageComment.setOnClickListener(this);

        binding.vfViewfilpper.setInAnimation(this, R.anim.push_up_in);
        binding.vfViewfilpper.setOutAnimation(this, R.anim.push_up_out);
        binding.vfViewfilpper.startFlipping();

        initViewFilpper();
        getDate();
    }

    private void initActionBar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private List<LinearLayout> initViewFilpper(){
        filpperList = new ArrayList<LinearLayout>();

        for (int i = 0; i < 2; i++) {
            LinearLayout fl = (LinearLayout) new LinearLayout(this);
//            tv.setText("这是测试用的第 " + i + i + i + i + " 行测试数据：");
            View view = View.inflate(this,R.layout.comment_image_item,null);
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

    /**
     * 发送评论
     */
    @Override
    public void sendComment() {
        if (binding.commentContent.getText().toString().equals("")) {
            Toast.makeText(this, "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 生成评论数据
            Comment comment = new Comment();
            comment.setContent(binding.commentContent.getText().toString());


            View view2 = View.inflate(this, R.layout.comment_item,null);

            TextView comment2 = (TextView) view2.findViewById(R.id.tv_image_comment_textview);
            comment2.setText(binding.commentContent.getText().toString());
            binding.llImageComment.addView(view2);

            // 发送完，清空输入框
            binding.commentContent.setText("");
            Toast.makeText(this, "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }


    public void showEditText(){

        binding.commentContent.setFocusable(true);
        binding.commentContent.setFocusableInTouchMode(true);
        binding.commentContent.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)binding.commentContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(binding.commentContent, 0);
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
            case R.id.tv_image_comment:
                showEditText();
                break;

        }
    }
}
