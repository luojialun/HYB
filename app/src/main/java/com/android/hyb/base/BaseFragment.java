package com.android.hyb.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.hyb.widget.dialog.ShowDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    private Unbinder unBinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(setViewId(), null);
        unBinder = ButterKnife.bind(this, view);
        initView();
        initData();
        if (bindEventbus()) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    public abstract int setViewId();

    public abstract void initView();

    public abstract void initData();

    public boolean bindEventbus() {
        return false;
    }

    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    public void showProgress() {
        ShowDialog.showDialog(getActivity(), "", true, null);
    }

    public void dissmissProgress() {
        ShowDialog.dissmiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
        if (bindEventbus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
