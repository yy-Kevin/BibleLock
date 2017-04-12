package com.shoplex.bible.biblelock.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.shoplex.bible.biblelock.ImageCommentActivity;
import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.adapter.ImageFragmentAdapter;
import com.shoplex.bible.biblelock.bean.Comment;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/28.
 */

public class ImageFragment extends Fragment {

    public static final String TAG ="ImageFragment" ;
    private ListView lv_fragment_image;
    private SwipeRefreshLayout mSwipeRefresh;
    private ImageFragmentAdapter textAdapter;
    private Activity mActivity;
    private FloatingActionButton fab_action;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"yuyao onCreateView");
        View view = View.inflate(getContext(), R.layout.fragment_image, null);
        lv_fragment_image = (ListView) view.findViewById(R.id.lv_fragment_image);
        fab_action = (FloatingActionButton) view.findViewById(R.id.fab_action);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
        mActivity = getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.i(TAG,"yuyao onActivityCreated");
        ArrayList<Comment> arrayList = new ArrayList();
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());

        textAdapter = new ImageFragmentAdapter(getContext(),arrayList);
        lv_fragment_image.setAdapter(textAdapter);
        lv_fragment_image.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position%2 == 0){
                    Intent intent = new Intent(mActivity, ImageCommentActivity.class);
                    intent.putExtra("",position);
                    mActivity.startActivityForResult(intent,1);
                }
            }
        });

        fab_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity,"这是广告为值",Toast.LENGTH_LONG).show();
                fab_action.setVisibility(View.GONE);
            }
        });
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,"下拉刷新成功");
                        // 停止刷新
                        mSwipeRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}

