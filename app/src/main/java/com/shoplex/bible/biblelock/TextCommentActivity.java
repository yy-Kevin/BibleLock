package com.shoplex.bible.biblelock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shoplex.bible.biblelock.databinding.ActivityTextCommentBinding;

/**
 * Created by qsk on 2017/4/6.
 */

public class TextCommentActivity extends AppCompatActivity{

    private ActivityTextCommentBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_comment);
    }
}
