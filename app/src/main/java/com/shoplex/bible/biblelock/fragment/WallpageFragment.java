package com.shoplex.bible.biblelock.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shoplex.bible.biblelock.LockScreenActivity;
import com.shoplex.bible.biblelock.R;

/**
 * Created by qsk on 2017/3/28.
 */

public class WallpageFragment extends Fragment {


    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.viewpager_wallpager, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        final int[] datas = {R.drawable.wallpaper_2, R.drawable.wallpaper_3, R.drawable.wallpaper_5, R.drawable.wallpaper_6, R.drawable.wallpaper_2, R.drawable.wallpaper_2};
//创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
//创建并设置Adapter
        MyAdapter mAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new GridLayoutManager(mActivity, 2);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                LockScreenActivity activity = (LockScreenActivity)mActivity;
                activity.getFrameLayout().setBackgroundResource(datas[data]);
            }
        });

        return view;
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
        public int[] datas = null;

        public MyAdapter(int[] datas) {
            this.datas = datas;
        }

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }

        public  interface OnRecyclerViewItemClickListener {
            void onItemClick(View view , int data);

        }

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }


        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wallpaper_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            vh.mTextView.setOnClickListener(this);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setBackgroundResource(datas[position]);

            viewHolder.mTextView.setTag(position);
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.length;
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public  class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (ImageView) view.findViewById(R.id.iv_image);
            }
        }
    }
}
