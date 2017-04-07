package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.BibleData;
import com.shoplex.bible.biblelock.bean.Comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qsk on 2017/3/28.
 */

public class TextFragmentAdapter extends BaseAdapter {

    private static final String TAG = "TextFragmentAdapter";
    private ArrayList<Comment> mList;
    private Context mContext;
    private TextHolder holder;
    private onClickListener listener;

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
            holder.commentText = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.tv_like = (TextView) convertView.findViewById(R.id.tv_like);
            holder.animation = (TextView) convertView.findViewById(R.id.animation);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (TextHolder) convertView.getTag();
        }

        holder.commentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);

                }
            }
        });

        String time = formatDataForDisplay(mList.get(position).getTime());
        holder.tv_time.setText(time);
        return convertView;
    }

//    @Override
//    public void onClick(View v) {
//        Log.i(TAG,"yuyao v = " +v);
//
//        switch (v.getId()) {
//            case R.id.tv_comment:
//                if(listener)
//                mComment.setVisibility(View.VISIBLE);
//                // 弹出输入法
//                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                break;
//        }
//    }

    private class TextHolder {
        TextView commentText;
        TextView tv_like;
        TextView animation;
        TextView tv_time;
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
