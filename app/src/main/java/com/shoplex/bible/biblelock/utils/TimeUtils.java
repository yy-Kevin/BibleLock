package com.shoplex.bible.biblelock.utils;

/**
 * Created by qsk on 2017/4/13.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public  class TimeUtils {


    /**
     * 分享功能
     * @param context
     * @param activityTitle
     * @param msgTitle
     * @param msgText
     * @param imgPath
     */
    public static void shareMsg(Context context, String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }



    public static String formatDataForDisplay(String strData) {
        Date date = new Date();

        Calendar date1 = Calendar.getInstance();
        date1.get(Calendar.HOUR_OF_DAY);//得到24小时机制的
//        date.get(Calendar.HOUR);//   得到12小时机制的
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date issueDate = null;
        try {
            issueDate = myFormatter.parse(strData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currTime1 = date1.getTime();
        long currTime = currTime1.getTime();
        long issueTime = issueDate.getTime();
        long diff = currTime - issueTime;
        diff = diff / 1000;//秒
        if (diff / 60 < 1) {
            return "刚刚";
        }
        if (diff / 60 >= 1 && diff / 60 <= 60) {
            return diff / 60 + "分钟前";
        }
        if (diff / 3600 > 0 && diff / 3600 <= 24) {
            return diff / 3600 + "小时前";
        }
        if (diff / (3600 * 24) > 0 && diff / (3600 * 24) < 2) {
            return "昨天";
        }
        if (diff / (3600 * 24) > 1 && diff / (3600 * 24) < 3) {
            return "前天";
        }
        if (diff / (3600 * 24) > 2) {
            return formatter.format(issueDate);
        }
        return "";
    }
}
