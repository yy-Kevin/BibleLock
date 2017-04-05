package com.shoplex.bible.biblelock.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.shoplex.bible.biblelock.LockScreenActivity;

/**
 * Created by qsk on 2017/3/27.
 */

public class ServiceActivity extends Service {

    public static final String TAG = "ServiceActivity";
    private Intent mLockIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mScreenOffFilter = new IntentFilter();
        mScreenOffFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenOffReceiver, mScreenOffFilter);
    }

    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == Intent.ACTION_SCREEN_OFF) {
                Log.i(TAG,"yuyao 111"  );
                mLockIntent = new Intent(context, LockScreenActivity.class);
                mLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(mLockIntent);
            }
        }
    };

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        System.out.println("service onDestroy");
        unregisterReceiver(mScreenOffReceiver);
        if(mLockIntent!=null){
            System.out.println("serviceIntent not null");
            startService(mLockIntent);
        }
    }
}
