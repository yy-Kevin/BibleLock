package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.TextCommentActivity;
import com.shoplex.bible.biblelock.bean.Comment;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qsk on 2017/3/28.
 */

public class TextFragmentAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = "TextFragmentAdapter";
    private ArrayList<Comment> mList;
    private Context mContext;
    private TextHolder holder;
    private onClickListener listener;
    private boolean isLike = false;

    public TextFragmentAdapter(Context context, ArrayList<Comment> list) {
        mList = list;
        mContext = context;
    }

    public interface onClickListener {
        void onItemClick(int postition);
    }

    public void setOnclicListener(onClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.fragment_text_item, null);
            holder = new TextHolder();

            holder.tv_text_like = (TextView) convertView.findViewById(R.id.tv_text_like);
            holder.tv_text_comment = (TextView) convertView.findViewById(R.id.tv_text_comment);
            holder.animation = (TextView) convertView.findViewById(R.id.animation);
            holder.tv_text_share = (TextView) convertView.findViewById(R.id.tv_text_share);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (TextHolder) convertView.getTag();
        }


        holder.tv_text_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);

                }
            }
        });

        holder.tv_text_like.setOnClickListener(this);
        holder.tv_text_share.setOnClickListener(this);
        holder.tv_text_like.setSelected(isLike);

        String time = formatDataForDisplay(mList.get(position).getTime());
        holder.tv_time.setText(time);
        return convertView;
    }


    private class TextHolder {
        TextView animation;
        TextView tv_time;
        TextView tv_text_like;
        TextView tv_text_share;
        TextView tv_text_comment;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_text_comment:
                Log.i(TAG,"tv_text_comment");

                break;
            case R.id.tv_text_like:
                selectLike(v,isLike);
                if (isLike){
                    isLike = false;
                }else {
                    isLike = true;
                }
                Log.i(TAG,"like like");
                break;
            case R.id.tv_text_share:
                Log.i(TAG,"share share");
                shareMsg(mContext, "MainAcitvity" ,"快使用我们把","我们是最好的的",null);
                break;
        }
    }

    public void selectLike(View v,boolean b){

        v.setSelected(!b);

    }

    /**
     * 分享功能
     * @param context
     * @param activityTitle
     * @param msgTitle
     * @param msgText
     * @param imgPath
     */
    public void shareMsg(Context context, String activityTitle, String msgTitle, String msgText,
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
}
