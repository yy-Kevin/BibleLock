package com.shoplex.bible.biblelock;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.shoplex.bible.biblelock.databinding.ActivityTextCommentBinding;
import com.shoplex.bible.biblelock.utils.TimeUtils;

/**
 * Created by qsk on 2017/4/6.
 */

public class TextCommentActivity extends BaseCommentActivity implements View.OnClickListener{

    private ActivityTextCommentBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_comment);
        initActionBar();
        //加载评论
        getData(binding.llTextComment);
        binding.commentSend.setOnClickListener(this);
        binding.tvTextComment.setOnClickListener(this);
        binding.tvTextShare.setOnClickListener(this);
    }


    private void initActionBar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);

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
                binding.edittextComment.setFocusable(true);
                binding.edittextComment.setFocusableInTouchMode(true);
                binding.edittextComment.requestFocus();
                InputMethodManager inputManager = (InputMethodManager)binding.edittextComment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(binding.edittextComment, 0);
                break;

        }
    }

}
