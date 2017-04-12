package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shoplex.bible.biblelock.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.facebook.GraphRequest.TAG;

/**
 * Created by qsk on 2017/4/12.
 */

public class FirstLockScreenViewPager extends BaseLockScreenViewPager {


    private Handler handler;
    private TextView tv_time;

    public FirstLockScreenViewPager(Activity context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContent, R.layout.viewpager_first, null);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        initTime();
        return view;
    }



    public void initTime(){
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.i(TAG,"YUYAO 111 cityName = " + (String)msg.obj);
                    tv_time.setText((String)msg.obj);
                }
            };

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            Log.i(TAG,"YUYAO ");
                            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
//                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String str=sdf.format(new Date());
                            Log.i(TAG,"str "+str);
                            handler.sendMessage(handler.obtainMessage(100,str));
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
}
