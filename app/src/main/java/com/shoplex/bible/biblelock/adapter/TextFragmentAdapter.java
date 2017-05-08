package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shoplex.bible.biblelock.MainActivity;
import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.utils.TimeUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import static com.shoplex.bible.biblelock.location.LoactionUrl.TYPE_COMPANY;
import static com.shoplex.bible.biblelock.location.LoactionUrl.TYPE_TITLE;

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
    private final MainActivity mainActivity;
    private View convertView;

    public TextFragmentAdapter(Context context, ArrayList<Comment> list) {
        mList = list;
        mContext = context;
        mainActivity = (MainActivity) mContext;
    }

    public interface onClickListener {
        void onItemClick(int postition);
    }

    public void setOnclicListener(onClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        position = position + 1;
        if (position % 3 == 0) {
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
        if (mList == null) {
            return 0;
        }
        int s = mList.size() / 2;
        return mList.size() + s;
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

        switch (getItemViewType(position)) {
            case TYPE_COMPANY:
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
                final int finalPosition = position;
                holder.tv_text_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onItemClick(finalPosition);

                        }
                    }
                });
                holder.tv_text_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "like like");
                        holder.tv_text_like.setEnabled(true);
                        notifyDataSetChanged();

                    }
                });
                holder.tv_text_share.setOnClickListener(this);

                position = position - position / 2;
                String time = TimeUtils.formatDataForDisplay(mList.get(position).getTime());
                holder.tv_time.setText(time);
                break;

            case TYPE_TITLE:

                convertView = View.inflate(mContext, R.layout.fragment_title_item, null);
                mainActivity.showNativeAd(convertView);


                break;
        }


        if (position == 1 || position == 3) {
//            holder.tv_text_like.setEnabled(true);
        }


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
        Log.i(TAG, "1 11");

        switch (v.getId()) {
//            case R.id.tv_text_like:
//                Log.i(TAG, "like like");
//                holder.tv_text_like.setEnabled(true);
//                break;

            case R.id.tv_text_share:
                TimeUtils.shareMsg(mContext, "MainAcitvity", "快使用我们把", "我们是最好的的", null);
                break;
        }
    }

    public void selectLike(View v, boolean b) {
        v.setSelected(!b);
    }
}
