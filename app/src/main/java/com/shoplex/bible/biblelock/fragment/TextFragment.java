package com.shoplex.bible.biblelock.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.adapter.TextFragmentAdapter;
import com.shoplex.bible.biblelock.bean.BibleData;
import com.shoplex.bible.biblelock.bean.Comment;

import java.util.ArrayList;

/**
 * Created by qsk on 2017/3/28.
 */

public class TextFragment extends Fragment implements View.OnClickListener {


    private ListView lv_fragment_text;
    private EditText editText;
    private RelativeLayout relativeLayout;
    private Button comment_send;
    private TextView hide_down;
    private ArrayList<Comment> arrayList;
    private TextFragmentAdapter textAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_text, null);
        lv_fragment_text = (ListView) view.findViewById(R.id.lv_fragment_text);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_comment);
        hide_down = (TextView) view.findViewById(R.id.hide_down);
        editText = (EditText) view.findViewById(R.id.comment_content);
        comment_send = (Button) view.findViewById(R.id.comment_send);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public static final String TAG = "onCreateView";

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG,"");
                if (!hasFocus) {
                    Log.i(TAG,"ssss");
                    relativeLayout.setVisibility(View.GONE);
                    InputMethodManager im = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    im.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                }

            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                InputMethodManager im = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                im.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                return false;
            }
        });


        arrayList = new ArrayList();
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        textAdapter = new TextFragmentAdapter(getContext(), arrayList,relativeLayout);
        lv_fragment_text.setAdapter(textAdapter);

        hide_down.setOnClickListener(this);
        comment_send.setOnClickListener(this);
        hide_down.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_comment:
                relativeLayout.setVisibility(View.VISIBLE);
                editText.requestFocus();
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
//                rl_enroll.setVisibility(View.GONE);
                break;
            case R.id.hide_down:
                // 隐藏评论框
//                rl_enroll.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                break;
            case R.id.comment_send:
                sendComment();
        }
    }

    /**
     * 发送评论
     */
    public void sendComment(){
        if(editText.getText().toString().equals("")){
            Toast.makeText(getContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            // 生成评论数据
            Comment comment = new Comment();
            comment.setName("评论者"+(arrayList.size()+1)+"：");
            comment.setContent(editText.getText().toString());
            this.addComment(comment);
            // 发送完，清空输入框
            editText.setText("");
            Toast.makeText(getContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

    public void addComment(Comment comment){
        arrayList.add(comment);
        textAdapter.notifyDataSetChanged();
    }
}
