package com.shoplex.bible.biblelock.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shoplex.bible.biblelock.MainActivity;
import com.shoplex.bible.biblelock.R;

/**
 * Created by qsk on 2017/3/28.
 */

public class VerseFragment extends Fragment {

    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.viewpager_verse,null);
        RelativeLayout rl_verse_more = (RelativeLayout) view.findViewById(R.id.rl_verse_more);

        
        rl_verse_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        });
        return view;
    }

}
