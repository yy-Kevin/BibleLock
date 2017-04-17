package com.shoplex.bible.biblelock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.shoplex.bible.biblelock.databinding.ActivityTextCommentBinding;

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

        binding.tvTextShare.setOnClickListener(this);
    }


    private void initActionBar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //设置ToolBar,使ToolBar生效
        actionBar.setDisplayHomeAsUpEnabled(true);

    }



}
