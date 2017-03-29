package com.shoplex.bible.biblelock.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.adapter.TextFragmentAdapter;
import com.shoplex.bible.biblelock.bean.BibleData;
import com.shoplex.bible.biblelock.bean.Comment;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/28.
 */

public class TextFragment extends Fragment {


    private ListView lv_fragment_text;
    private EditText editText;
    private RelativeLayout relativeLayout;
    private Button comment_send;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_text, null);
        lv_fragment_text = (ListView) view.findViewById(R.id.lv_fragment_text);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_comment);
        editText = (EditText) view.findViewById(R.id.comment_content);
        comment_send = (Button) view.findViewById(R.id.comment_send);

        ArrayList<Comment> arrayList = new ArrayList();
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        TextFragmentAdapter textAdapter = new TextFragmentAdapter(getContext(),arrayList,relativeLayout);
        lv_fragment_text.setAdapter(textAdapter);
        return view;
    }
}
