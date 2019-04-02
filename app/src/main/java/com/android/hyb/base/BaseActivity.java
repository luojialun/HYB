package com.android.hyb.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());
        unBinder = ButterKnife.bind(this);
        initView();
        initData();
        if (bindEventbus()) {
            EventBus.getDefault().register(this);
        }
    }

    public abstract int setViewId();

    public abstract void initView();

    public abstract void initData();

    public boolean bindEventbus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
        if (bindEventbus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
