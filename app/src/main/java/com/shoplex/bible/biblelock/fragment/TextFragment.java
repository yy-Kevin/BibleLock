package com.shoplex.bible.biblelock.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoplex.bible.biblelock.R;
import com.shoplex.bible.biblelock.TextCommentActivity;
import com.shoplex.bible.biblelock.adapter.TextFragmentAdapter;
import com.shoplex.bible.biblelock.bean.Comment;
import com.shoplex.bible.biblelock.utils.ToastUtil;

import java.util.ArrayList;

import static com.shoplex.bible.biblelock.server.ServiceActivity.TAG;

/**
 * Created by qsk on 2017/3/28.
 */

public class TextFragment extends Fragment implements View.OnClickListener {


    private ListView lv_fragment_text;
    private EditText editText;
    private RelativeLayout relativeLayout;
    private TextView hide_down;
    private ArrayList<Comment> arrayList;
    private TextFragmentAdapter textAdapter;
    private InputMethodManager imm;
    private RelativeLayout rl_comment;
    private PopupWindow popupWindow;
    private View popup;
    private View view;
    private Activity mActivity;
    private FloatingActionButton fab_action;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mActivity, R.layout.fragment_text, null);
        fab_action = (FloatingActionButton) view.findViewById(R.id.fab_action_text);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_text);

        popup = View.inflate(mActivity, R.layout.popupwindow,null);
        lv_fragment_text = (ListView) view.findViewById(R.id.lv_fragment_text);
        imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);

        rl_comment = (RelativeLayout) popup.findViewById(R.id.rl_comment);
        hide_down = (TextView) popup.findViewById(R.id.hide_down);
        editText = (EditText) popup.findViewById(R.id.comment_content);

        popupWindow = new PopupWindow(mActivity);
        popupWindow.setContentView(popup);
        popupWindow.setFocusable(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        arrayList = new ArrayList();
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());
        arrayList.add(new Comment());

        textAdapter = new TextFragmentAdapter(mActivity, arrayList);
        lv_fragment_text.setAdapter(textAdapter);
        hide_down.setOnClickListener(this);
        hide_down.setOnClickListener(this);

        fab_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mActivity,"这是广告位置");
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


        textAdapter.setOnclicListener(new TextFragmentAdapter.onClickListener() {
            @Override
            public void onItemClick(int postition) {
                Log.i("当前被编辑条目位置", postition+"");
//                popupWindow.showAtLocation(lv_fragment_text, Gravity.BOTTOM,0,0);
//                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                Intent intent = new Intent(mActivity, TextCommentActivity.class);
                intent.putExtra("postition" ,postition);
                mActivity.startActivityForResult(intent,0);


            }
        });

        lv_fragment_text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mActivity, TextCommentActivity.class);
                intent.putExtra("postition" ,position);
                mActivity.startActivityForResult(intent,0);
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hide_down:
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 发送评论
     */
    public void sendComment() {
        if (editText.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 生成评论数据
            Comment comment = new Comment();
            comment.setName("评论者" + (arrayList.size() + 1) + "：");
            comment.setContent(editText.getText().toString());
            this.addComment(comment);
            // 发送完，清空输入框
            editText.setText("");
            Toast.makeText(getActivity(), "评论成功！", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        }
    }

    public void addComment(Comment comment) {
        arrayList.add(comment);
        textAdapter.notifyDataSetChanged();
    }
}
