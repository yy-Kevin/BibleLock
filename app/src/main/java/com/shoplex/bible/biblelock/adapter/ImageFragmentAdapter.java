package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.shoplex.bible.biblelock.MainActivity;
import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import static com.shoplex.bible.biblelock.location.LoactionUrl.TYPE_COMPANY;
import static com.shoplex.bible.biblelock.location.LoactionUrl.TYPE_TITLE;

/**
 * Created by qsk on 2017/3/28.
 */

public class ImageFragmentAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = "ImageFragmentAdapter";
    private ArrayList<Comment> mList;
    private Context mContext;

    private final MainActivity mainActivity;

    public ImageFragmentAdapter(Context context, ArrayList<Comment> list){
        mList = list;
        mContext = context;
        mainActivity = (MainActivity) mContext;

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
                String time = TimeUtils.formatDataForDisplay(mList.get(position).getTime());
                iHolder.tv_image_time.setText(time);

                iHolder.vf_viewfilpper.setInAnimation(mContext, R.anim.push_up_in);
                iHolder.vf_viewfilpper.setOutAnimation(mContext, R.anim.push_up_out);
                iHolder.vf_viewfilpper.startFlipping();

                iHolder.tv_image_share.setOnClickListener(this);
                iHolder.tv_image_like.setOnClickListener(this);
                break;
            case TYPE_TITLE:
                Log.i(TAG,"showNativeAd ");
                view  = View.inflate(mContext, R.layout.fragment_title_item,null);
                mainActivity.showNativeAd(view);

                break;
        }
        return view;
    }


    private List<FrameLayout> getData1() {
        List<FrameLayout> list = new ArrayList<FrameLayout>();
        for (int i = 0; i < 5; i++) {
            FrameLayout fl = (FrameLayout) new FrameLayout(mContext);
//            tv.setText("这是测试用的第 " + i + i + i + i + " 行测试数据：");
            View view = View.inflate(mContext,R.layout.comment_image_item,null);
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
                TimeUtils.shareMsg(mContext, "MainAcitvity" ,"快使用我们把","我们是最好的的",null);
                break;
        }
    }

    private class ImageHolder{
        ViewFlipper vf_viewfilpper;
        TextView tv_image_time;
        TextView tv_image_share;
        TextView tv_image_like;
    }

}

