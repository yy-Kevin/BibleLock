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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shoplex.bible.biblelock.ImageCommentActivity;
import com.shoplex.bible.biblelock.MainActivity;
import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.adapter.ImageFragmentAdapter;
import com.shoplex.bible.biblelock.api.ApiManager;
import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.bean.Result;
import com.shoplex.bible.biblelock.progress.ProgressSubscriber;
import com.shoplex.bible.biblelock.progress.SubscriberOnNextListener;
import com.shoplex.bible.biblelock.utils.ToastUtil;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qsk on 2017/3/28.
 */

public class ImageFragment extends Fragment {
    private static final String TGA = "ImageFragment";
    private long lastClickTime = 0;
    public static final String TAG ="ImageFragment" ;
    private ListView lv_fragment_image;
    private SwipeRefreshLayout mSwipeRefresh;
    private ImageFragmentAdapter textAdapter;
    private Activity mActivity;
    private FloatingActionButton fab_action;
    private SubscriberOnNextListener getTopMovieOnNext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"yuyao onCreateView");
        View view = View.inflate(getActivity(), R.layout.fragment_image, null);
        lv_fragment_image = (ListView) view.findViewById(R.id.lv_fragment_image);
        fab_action = (FloatingActionButton) view.findViewById(R.id.fab_action);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
        mActivity = getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.i(TAG,"yuyao onActivityCreated");
        //请求网路
        initInnet();
        ArrayList<Comment> arrayList = new ArrayList();
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());

        textAdapter = new ImageFragmentAdapter(getActivity(),arrayList);
        lv_fragment_image.setAdapter(textAdapter);
        lv_fragment_image.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TGA,"yuyao  position = " + position);
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
                ToastUtil.showToast(mActivity,"这是广告位置");
                MainActivity mainActivity = (MainActivity) mActivity;
                View view = mainActivity.showPopwindowad(R.layout.popupwindow_ad, Gravity.CENTER);
                mainActivity.showNativeAd(view);
                fab_action.setVisibility(View.GONE);
            }
        });


        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initInnet();
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

    private void initInnet(){
        getTopMovieOnNext = new SubscriberOnNextListener<Result>() {
            @Override
            public void onNext(Result result) {
                Log.i(TAG,"result = " + result.getData().getIntroduction());
            }
        };

        ProgressSubscriber process = new ProgressSubscriber(getTopMovieOnNext, getActivity());


        ApiManager.apiManager.getReslut(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(process);

    }

}

