package com.shoplex.bible.biblelock.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shoplex.bible.biblelock.presenter.LoadPresenter;

/**
 * Created by qsk on 2017/3/28.
 */

public class LoadActivity extends AppCompatActivity implements ILoadView {

    private LoadPresenter loadPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadPresenter = new LoadPresenter(this);
    }



    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadError() {

    }
}
