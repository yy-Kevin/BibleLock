package com.shoplex.bible.biblelock.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.adapter.ImageFragmentAdapter;
import com.shoplex.bible.biblelock.bean.BibleData;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/28.
 */

public class ImageFragment extends Fragment {

    public static final String TAG ="ImageFragment" ;

    private LinearLayout ll_fragment_image;
    private ImageView iv_fragment_image;
    private TextView tv_fragment_image;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG,"yuyao = " + msg);
        }
    };
    private ListView lv_fragment_image;
    private SwipeRefreshLayout mSwipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_image, null);
        lv_fragment_image = (ListView) view.findViewById(R.id.lv_fragment_image);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
        ArrayList<BibleData> arrayList = new ArrayList();
        arrayList.add(new BibleData());
        arrayList.add(new BibleData());
        arrayList.add(new BibleData());
        ImageFragmentAdapter textAdapter = new ImageFragmentAdapter(getContext(),arrayList);
        lv_fragment_image.setAdapter(textAdapter);

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
//        ll_fragment_image = (LinearLayout) view.findViewById(R.id.ll_fragment_image);
//        iv_fragment_image = (ImageView) view.findViewById(R.id.iv_fragment_image);
//        tv_fragment_image = (TextView) view.findViewById(R.id.tv_fragment_image);
//
//        ll_fragment_image.setTranslationY(100);
//        int i = 0;
//
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Message message = mHandler.obtainMessage();
//                message.what = 0;
//                mHandler.sendMessage(message);
//            }
//        }, 1000);
//
//        AlphaAnimation aa = new AlphaAnimation(1.0f, 0.0f);
//        aa.setDuration(1500);
////        aa.setRepeatCount(5);
//        ll_fragment_image.startAnimation(aa);
//        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -150.0f);
//        animation.setDuration(1500);
////        animation.setRepeatCount(Integer.MAX_VALUE);
//
//        tv_fragment_image.setText("sdasdfasdfasa +" + i++);
//        ll_fragment_image.startAnimation(animation);

        return view;

    }
}
