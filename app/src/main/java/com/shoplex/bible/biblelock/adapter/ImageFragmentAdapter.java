package com.shoplex.bible.biblelock.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.bean.BibleData;

import java.util.ArrayList;

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


        return view;
    }

    private class ImageHolder{

    }
}
