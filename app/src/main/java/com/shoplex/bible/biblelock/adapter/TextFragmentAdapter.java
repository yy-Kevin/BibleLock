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

public class TextFragmentAdapter extends BaseAdapter implements View.OnClickListener{

    private static final String TAG = "TextFragmentAdapter";
    private ArrayList<Comment> mList;
    private Context mContext;
    private TextHolder holder;
    private RelativeLayout mComment;
    public TextFragmentAdapter(Context context, ArrayList<Comment> list,RelativeLayout button){
        mList = list;
        mContext = context;
        mComment = button;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.fragment_text_item,null);
//            ViewDataBinding convertView1 = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.fragment_text_item, parent, false);
            holder = new TextHolder();
            holder.commentText = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.tv_like = (TextView) convertView.findViewById(R.id.tv_like);
            holder.animation = (TextView) convertView.findViewById(R.id.animation);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
//            holder.hide_down = (TextView) convertView.findViewById(R.id.hide_down);
//            holder.rl_comment = (RelativeLayout) convertView.findViewById(R.id.rl_comment);
//            holder.comment_content = (EditText) convertView.findViewById(R.id.comment_content);
//            holder.comment_send = (Button) convertView.findViewById(R.id.comment_send);

            convertView.setTag(holder);

        }else {
            holder = (TextHolder) convertView.getTag();
        }

        holder.commentText.setOnClickListener(this);
        holder.tv_like.setOnClickListener(this);

        String time  = formatDataForDisplay(mList.get(position).getTime());
        holder.tv_time.setText(time);

//        holder.hide_down.setOnClickListener(this);
//        holder.comment_send.setOnClickListener(this);


        return convertView;
    }

    /**
     * 添加一条评论,刷新列表
     * @param comment
     */
    public void addComment(Comment comment){
        mList.add(comment);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"yuyao v = " +v);

        switch (v.getId()) {
            case R.id.tv_comment:
                mComment.setVisibility(View.VISIBLE);
//                editText.requestFocus();
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    private class TextHolder{
        TextView commentText;
        TextView tv_like;
        TextView animation;
        TextView tv_time;
        TextView hide_down;
        RelativeLayout rl_comment;
        EditText comment_content;
        Button comment_send;
    }

    /**
     * 发送评论
     */
    public void sendComment(){
        if(holder.comment_content.getText().toString().equals("")){
            Toast.makeText(mContext, "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            // 生成评论数据
            Comment comment = new Comment();
            comment.setName("评论者"+(mList.size()+1)+"：");
            comment.setContent(holder.comment_content.getText().toString());
            this.addComment(comment);
            // 发送完，清空输入框
            holder.comment_content.setText("");
            Toast.makeText(mContext, "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

    public static String formatDataForDisplay(String strData) {
        Date date = new Date();

        Calendar date1 = Calendar.getInstance();
        date1.get(Calendar.HOUR_OF_DAY   );//得到24小时机制的
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
