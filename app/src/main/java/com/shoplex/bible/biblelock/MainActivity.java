package com.shoplex.bible.biblelock;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.shoplex.bible.biblelock.fragment.ImageFragment;
import com.shoplex.bible.biblelock.fragment.TextFragment;
import com.shoplex.bible.biblelock.server.ServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageFragment imageFragment;
    private TextFragment textFragment;
    private Button btv_image;
    private Button btv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        if (savedInstanceState == null) {
            initFragment();
        }
        Intent intent = new Intent(this,ServiceActivity.class);
        startService(intent);
        btv_image.setOnClickListener(this);
        btv_text.setOnClickListener(this);
    }

    private void initView(){
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btv_image = (Button) findViewById(R.id.btv_image);
        btv_text = (Button) findViewById(R.id.btv_text);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFragment(){
        imageFragment = new ImageFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_content, imageFragment).commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.btv_image:
                if (imageFragment == null) {
                    imageFragment = new ImageFragment();
                }
                transaction.replace(R.id.fl_content, imageFragment);
                break;
            case R.id.btv_text:
                if (textFragment == null) {
                    textFragment = new TextFragment();
                }
                transaction.replace(R.id.fl_content, textFragment);
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }
}
