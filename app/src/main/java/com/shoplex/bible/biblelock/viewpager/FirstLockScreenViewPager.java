package com.shoplex.bible.biblelock.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.shoplex.bible.biblelock.ClearActivity;
import com.shoplex.bible.biblelock.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.shoplex.bible.biblelock.server.ServiceActivity.TAG;


/**
 * Created by qsk on 2017/4/12.
 */

public class FirstLockScreenViewPager extends BaseLockScreenViewPager implements View.OnClickListener {


    private TextView tv_time1;
    private String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String[] mothun = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private TextView tv_time2;
    private ImageView iv_menu;
    private Handler handler;
    private ImageView iv_lock_clear;
    private ShimmerTextView myShimmerTextView;
    private Shimmer shimmer;

    public FirstLockScreenViewPager(Activity context) {
        super(context);
    }

    @Override
    public View initView() {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String str = (String) msg.obj;
                String[] strData = str.split(" ");
                tv_time1.setText(strData[1]);

                String wek = "";
                String moh = "";
                switch (msg.arg1) {
                    case 0:
                        wek = week[msg.arg1];
                        break;
                    case 1:
                        wek = week[msg.arg1];
                        break;
                    case 2:
                        wek = week[msg.arg1];
                        break;
                    case 3:
                        wek = week[msg.arg1];
                        break;
                    case 4:
                        wek = week[msg.arg1];
                        break;
                    case 5:
                        wek = week[msg.arg1];
                        break;
                    case 6:
                        wek = week[msg.arg1];
                        break;
                }
                switch (msg.arg2) {
                    case 0:
                        moh = mothun[msg.arg2];
                        break;
                    case 1:
                        moh = mothun[msg.arg2];
                        break;
                    case 2:
                        moh = mothun[msg.arg2];
                        break;
                    case 3:
                        moh = mothun[msg.arg2];
                        break;
                    case 4:
                        moh = mothun[msg.arg2];
                        break;
                    case 5:
                        moh = mothun[msg.arg2];
                        break;
                    case 6:
                        moh = mothun[msg.arg2];
                        break;
                    case 7:
                        moh = mothun[msg.arg2];
                        break;
                    case 8:
                        moh = mothun[msg.arg2];
                        break;
                    case 9:
                        moh = mothun[msg.arg2];
                        break;
                    case 10:
                        moh = mothun[msg.arg2];
                        break;
                    case 11:
                        moh = mothun[msg.arg2];
                        break;
                }

                String arg = wek + ", " + moh + " " + strData[0];
                tv_time2.setText(arg);
            }
        };
        View view = View.inflate(mContent, R.layout.viewpager_first, null);
        tv_time1 = (TextView) view.findViewById(R.id.tv_time1);
        tv_time2 = (TextView) view.findViewById(R.id.tv_time2);
        iv_menu = (ImageView) view.findViewById(R.id.iv_menu);
        myShimmerTextView = (ShimmerTextView) view.findViewById(R.id.shimmer_tv);
        iv_lock_clear = (ImageView) view.findViewById(R.id.iv_lock_clear);

        shimmer = new Shimmer();
        shimmer.start(myShimmerTextView);
//        shimmer.setRepeatCount(0)
//                .setDuration(500)
//                .setStartDelay(300)
//                .setDirection(Shimmer.ANIMATION_DIRECTION_RTL)
//                .setAnimatorListener(new Animator.AnimatorListener(){
//
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
        ImageView tv = (ImageView) view.findViewById(R.id.iv_wifi);
        int[] res = {R.drawable.cehua1, R.drawable.cehua3, R.drawable.cehua};

        iv_menu.setOnClickListener(this);
        iv_lock_clear.setOnClickListener(this);

        initTime();
        return view;
    }


    public void initTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    while (true) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd HH:mm");
//                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Message msg = new Message();
                        msg.obj = sdf.format(new Date());

                        Calendar c = Calendar.getInstance();
                        int mMonth = c.get(Calendar.MONTH);//获取当前月份;
                        int mWeek = c.get(Calendar.DAY_OF_WEEK);//获取周;
                        if (Calendar.MONDAY == mWeek) {
                            msg.arg1 = 0;
                        } else if (Calendar.TUESDAY == mWeek) {
                            msg.arg1 = 1;
                        } else if (Calendar.WEDNESDAY == mWeek) {
                            msg.arg1 = 2;
                        } else if (Calendar.THURSDAY == mWeek) {
                            msg.arg1 = 3;
                        } else if (Calendar.FRIDAY == mWeek) {
                            msg.arg1 = 4;
                        } else if (Calendar.SATURDAY == mWeek) {
                            msg.arg1 = 5;
                        } else if (Calendar.SUNDAY == mWeek) {
                            msg.arg1 = 6;
                        }
                        switch (mMonth) {
                            case Calendar.JANUARY:
                                msg.arg2 = 0;
                                break;
                            case Calendar.FEBRUARY:
                                msg.arg2 = 1;
                                break;
                            case Calendar.MARCH:
                                msg.arg2 = 2;
                                break;
                            case Calendar.APRIL:
                                msg.arg2 = 3;
                                break;
                            case Calendar.MAY:
                                msg.arg2 = 4;
                                break;
                            case Calendar.JUNE:
                                msg.arg2 = 5;
                                break;
                            case Calendar.JULY:
                                msg.arg2 = 6;
                                break;
                            case Calendar.AUGUST:
                                msg.arg2 = 7;
                                break;
                            case Calendar.SEPTEMBER:
                                msg.arg2 = 8;
                                break;
                            case Calendar.OCTOBER:
                                msg.arg2 = 9;
                                break;
                            case Calendar.NOVEMBER:
                                msg.arg2 = 10;
                                break;
                            case Calendar.DECEMBER:
                                msg.arg2 = 11;
                                break;
                        }

                        handler.sendMessage(handler.obtainMessage(100, msg.arg1, msg.arg2, msg.obj));

                        Thread.sleep(1000 * 60);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"yuyao iv_lock_clear1111" );

        switch (v.getId()) {
            case R.id.iv_menu:
                showPopUp(iv_menu);
                break;
            case R.id.iv_lock_clear:
                Log.i(TAG,"yuyao iv_lock_clear2222" );
                Intent intent = new Intent(mContent, ClearActivity.class);
                mContent.startActivity(intent);

                break;
        }
    }

    private void showPopUp(View v) {
        LinearLayout layout = new LinearLayout(mContent);
        layout.setBackgroundColor(Color.GRAY);
        TextView tv = new TextView(mContent);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setText("back");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(tv);

        PopupWindow popupWindow = new PopupWindow(layout, 140, 80);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.finish();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        v.getLocationOnScreen(location);

        popupWindow.showAsDropDown(v, -100, 0);
    }

}
