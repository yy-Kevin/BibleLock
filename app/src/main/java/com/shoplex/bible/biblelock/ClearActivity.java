package com.shoplex.bible.biblelock;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.shoplex.bible.biblelock.databinding.ActivityClearBinding;

/**
 * Created by qsk on 2017/4/21.
 */

public class ClearActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityClearBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_clear);

        setSupportActionBar(binding.toolbarClear);
        ActionBar actionBar = getSupportActionBar();
        //在二级界面等Activity中，通过如下设置可以在Toolbar左边显示一个返回按钮：
        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.sethome

        binding.rlClearBattery.setOnClickListener(this);
    }

    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.ggb.deepcleaner&hl=en" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
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
        goToMarket(this,"");
    }
}
