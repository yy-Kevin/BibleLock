package com.shoplex.bible.biblelock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;

import com.shoplex.bible.biblelock.databinding.ActivitySettingsBinding;
import com.shoplex.bible.biblelock.server.ServiceActivity;

/**
 * Created by qsk on 2017/4/11.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        binding.ibRotaus.setOnClickListener(this);
        binding.ibFeedbock.setOnClickListener(this);
        binding.ibSettings.setOnClickListener(this);


        binding.ibFeedbock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(SettingActivity.this,ServiceActivity.class);
                if (isChecked){
                    startService(intent);
                } else {
                    stopService(intent);
                }
            }
        });



        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        //在二级界面等Activity中，通过如下设置可以在Toolbar左边显示一个返回按钮：
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ib_rotaus:


                break;

            case R.id.ib_feedbock:

                 break;

            case R.id.ib_settings:


                break;
        }
    }
}
