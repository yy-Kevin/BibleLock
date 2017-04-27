package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.utils.TimeUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

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
            holder.tv_text_share = (TextView) convertView.findViewById(R.id.tv_text_share);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
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

        if (position ==1 || position ==2){
//            holder.tv_text_like.setEnabled(false);
        }
        holder.tv_text_like.setOnClickListener(this);
        holder.tv_text_share.setOnClickListener(this);


        String time = TimeUtils.formatDataForDisplay(mList.get(position).getTime());
        holder.tv_time.setText(time);
        return convertView;
    }


    private class TextHolder {
        TextView tv_time;
        TextView tv_text_like;
        TextView tv_text_share;
        TextView tv_text_comment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_text_comment:
                Log.i(TAG,"tv_text_comment");

                break;
            case R.id.tv_text_like:
               notifyDataSetChanged();
                break;
            case R.id.tv_text_share:
                Log.i(TAG,"share share");
                TimeUtils.shareMsg(mContext, "MainAcitvity" ,"快使用我们把","我们是最好的的",null);
                break;
        }
    }

    public void selectLike(View v,boolean b){
        v.setSelected(!b);
    }
}
