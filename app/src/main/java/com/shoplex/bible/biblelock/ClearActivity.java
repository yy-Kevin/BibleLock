package com.shoplex.bible.biblelock;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.shoplex.bible.biblelock.databinding.ActivityClearBinding;

/**
 * Created by qsk on 2017/4/21.
 */

public class ClearActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityClearBinding binding;
    private BatteryBroadcastReciver reciver;

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
        startAnimail(binding.ivJunk1);
        startAnimail(binding.ivMomory1);
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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        reciver = new BatteryBroadcastReciver();
        //创建一个过滤器
        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(reciver, intentFilter);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregisterReceiver(reciver);
    }

    public class BatteryBroadcastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                //得到系统当前电量
                int level=intent.getIntExtra("level", 0);
                //取得系统总电量
                int total=intent.getIntExtra("scale", 100);
                binding.tvBattery.setText("当前电量："+(level*100)/total+"%");
                binding.wlBattery.setPercent((level*100)/total);
                //当电量小于15%时触发
                if(level<15){
                    Toast.makeText(ClearActivity.this, "当前电量已小于15%",Toast.LENGTH_LONG).show();
                }

            }
        }

    }

    public void startAnimail(View view){
        RotateAnimation ra = new RotateAnimation(0, 359, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        ra.setRepeatCount(-1);
        //匀速
        LinearInterpolator lin = new LinearInterpolator();
        ra.setInterpolator(lin);
        view.startAnimation(ra);
    }

}
