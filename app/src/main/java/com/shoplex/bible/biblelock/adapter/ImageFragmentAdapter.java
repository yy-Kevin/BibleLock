package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.Comment;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by qsk on 2017/3/28.
 */

public class ImageFragmentAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = "ImageFragmentAdapter";
    private ArrayList<Comment> mList;
    private Context mContext;
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_COMPANY = 1;

    public ImageFragmentAdapter(Context context, ArrayList<Comment> list){
        mList = list;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        position = position + 1;
        if (position%2 == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_COMPANY;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        if (mList == null){
            return 0;
        }
        return mList.size()*2;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ImageHolder iHolder;
        switch (getItemViewType(position)){
            case TYPE_COMPANY:
                if (convertView == null){
                    view  = View.inflate(mContext, R.layout.fragment_image_item,null);
                    iHolder = new ImageHolder();
                    iHolder.vf_viewfilpper = (ViewFlipper) view.findViewById(R.id.vf_viewfilpper);
                    iHolder.tv_image_share = (TextView) view.findViewById(R.id.tv_image_share);
                    iHolder.tv_image_like = (TextView) view.findViewById(R.id.tv_image_like);
                    iHolder.tv_image_time = (TextView) view.findViewById(R.id.tv_image_time);
                    view.setTag(iHolder);
                } else {
                    view = convertView;
                    iHolder = (ImageHolder) view.getTag();
                }

                List<FrameLayout> list = this.getData1();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    iHolder.vf_viewfilpper.addView(list.get(i));
                }
                position = position%2;
                String time = formatDataForDisplay(mList.get(position).getTime());
                iHolder.tv_image_time.setText(time);

                iHolder.vf_viewfilpper.setInAnimation(mContext, R.anim.push_up_in);
                iHolder.vf_viewfilpper.setOutAnimation(mContext, R.anim.push_up_out);
                iHolder.vf_viewfilpper.startFlipping();

                iHolder.tv_image_share.setOnClickListener(this);
                iHolder.tv_image_like.setOnClickListener(this);
                break;
            case TYPE_TITLE:
                view  = View.inflate(mContext, R.layout.fragment_title_item,null);

                break;
        }
        return view;
    }

    private List<TextView> getData() {
        List<TextView> list = new ArrayList<TextView>();
        for (int i = 0; i < 5; i++) {
            TextView tv = (TextView) new TextView(mContext);
            tv.setText("这是测试用的第 " + i + i + i + i + " 行测试数据：");
            list.add(tv);
        }
        return list;
    }

    private List<FrameLayout> getData1() {
        List<FrameLayout> list = new ArrayList<FrameLayout>();
        for (int i = 0; i < 5; i++) {
            FrameLayout fl = (FrameLayout) new FrameLayout(mContext);
//            tv.setText("这是测试用的第 " + i + i + i + i + " 行测试数据：");
            View view = View.inflate(mContext,R.layout.comment_item,null);
            fl.addView(view);
            list.add(fl);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_image_like:
                Log.i(TAG,"like like");
                break;
            case R.id.tv_image_share:
                Log.i(TAG,"share share");
                shareMsg(mContext, "MainAcitvity" ,"快使用我们把","我们是最好的的",null);
                break;
        }
    }

    private class ImageHolder{
        ViewFlipper vf_viewfilpper;
        TextView tv_image_time;
        TextView tv_image_share;
        TextView tv_image_like;
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

    /**
     * 分享功能
     * @param mContext
     * @param activityTitle
     * @param msgTitle
     * @param msgText
     * @param imgPath
     */
    public void shareMsg(Context mContext, String activityTitle, String msgTitle, String msgText,
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
        this.mContext.startActivity(Intent.createChooser(intent, activityTitle));
    }
}

