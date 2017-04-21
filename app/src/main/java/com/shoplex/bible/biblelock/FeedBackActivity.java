package com.shoplex.bible.biblelock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.shoplex.bible.biblelock.databinding.ActivityFeedbackBinding;
import com.shoplex.bible.biblelock.utils.SharedPreferencesUtils;
import com.shoplex.bible.biblelock.utils.ToastUtil;

/**
 * Created by qsk on 2017/4/21.
 */

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FeedBackActivity";
    private ActivityFeedbackBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback);

        setSupportActionBar(binding.toolbarFeedback);
        ActionBar actionBar = getSupportActionBar();
        //在二级界面等Activity中，通过如下设置可以在Toolbar左边显示一个返回按钮：
        actionBar.setDisplayHomeAsUpEnabled(true);

        binding.etFeedback.addTextChangedListener(textWatcher);
        binding.btFeedback.setOnClickListener(this);

        String feedback = (String) SharedPreferencesUtils.get(FeedBackActivity.this,"feedback","");
        if (feedback != null){
            binding.etFeedback.setText(feedback);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG,"onTextChanged s = " + s);

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i(TAG,"beforeTextChanged s = " + s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.i(TAG,"afterTextChanged s = " + s);
            String feedback = binding.etFeedback.getText().toString();
            SharedPreferencesUtils.put(FeedBackActivity.this,"feedback",feedback);

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_feedback:

                if (binding.etFeedback.getText().toString().equals("")) {
                    ToastUtil.showToast(this, "评论不能为空！");
                } else {
                    String s = binding.etFeedback.getText().toString();
                    Log.i(TAG,"yuyao s" + s);
                    ToastUtil.showToast(this,"反馈成功，非常感谢您的反馈，您的建议将是我们前进的动力");
                    binding.etFeedback.setText("");
                    SharedPreferencesUtils.put(FeedBackActivity.this,"feedback","");
                }

                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
