package com.shoplex.bible.biblelock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.CompoundButton;

import com.shoplex.bible.biblelock.databinding.ActivitySettingsBinding;
import com.shoplex.bible.biblelock.server.ServiceActivity;
import com.shoplex.bible.biblelock.utils.SharedPreferencesUtils;

/**
 * Created by qsk on 2017/4/11.
 */

public class SettingActivity extends AppCompatActivity{

    private ActionBar actionBar;
    private ActivitySettingsBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        intent = new Intent(SettingActivity.this,ServiceActivity.class);

        boolean isChecked = (boolean) SharedPreferencesUtils.get(SettingActivity.this,"isChecked",false);
        if (isChecked){
            binding.ibLockScreen.setChecked(true);
            startService(intent);
        }else {
            binding.ibLockScreen.setChecked(false);
            stopService(intent);
        }

        binding.ibLockScreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    startService(intent);
                    SharedPreferencesUtils.put(SettingActivity.this,"isChecked",isChecked);
                } else {
                    stopService(intent);
                    SharedPreferencesUtils.put(SettingActivity.this,"isChecked",isChecked);
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
}
