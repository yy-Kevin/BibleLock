package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.BibleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qsk on 2017/3/28.
 */

public class ImageFragmentAdapter extends BaseAdapter {

    private ArrayList<BibleData> mList;
    private Context mContext;
    public ImageFragmentAdapter(Context context, ArrayList<BibleData> list){
        mList = list;
        mContext = context;
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
        View view = View.inflate(mContext, R.layout.fragment_image_item,null);
        ViewFlipper viewFilpper = (ViewFlipper) view.findViewById(R.id.vf_viewfilpper);

        List<TextView> list = this.getData();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            viewFilpper.addView(list.get(i));
        }


        viewFilpper.setInAnimation(mContext, R.anim.push_up_in);
        viewFilpper.setOutAnimation(mContext, R.anim.push_up_out);

        viewFilpper.startFlipping();


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

    private class ImageHolder{

    }
}
