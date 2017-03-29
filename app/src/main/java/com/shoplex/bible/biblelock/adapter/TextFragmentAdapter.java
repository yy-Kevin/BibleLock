package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
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

import java.util.ArrayList;

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
            holder = new TextHolder();
            holder.commentText = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.tv_like = (TextView) convertView.findViewById(R.id.tv_like);
            holder.animation = (TextView) convertView.findViewById(R.id.animation);
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

                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
//                rl_enroll.setVisibility(View.GONE);
                break;

            case R.id.tv_like:
                android.view.animation.Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.applaud_animation);
                holder.animation.setVisibility(View.VISIBLE);
                holder.animation.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.animation.setVisibility(View.GONE);
                    }
                }, 1000);

//            case R.id.hide_down:
//                // 隐藏评论框
////                rl_enroll.setVisibility(View.VISIBLE);
////                holder.rl_comment.setVisibility(View.GONE);
//                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
//                InputMethodManager im = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//                im.hideSoftInputFromWindow(holder.comment_content.getWindowToken(), 0);
//                break;
//            case R.id.comment_send:
//                sendComment();
//                break;
//            default:
//                break;
        }
    }

    private class TextHolder{
        TextView commentText;
        TextView tv_like;
        TextView animation;
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
}
