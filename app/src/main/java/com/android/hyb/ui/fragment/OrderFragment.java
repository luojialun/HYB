package com.android.hyb.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {

    public int type;
    @BindView(R.id.test)
    TextView test;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public int setViewId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        test.setText(String.valueOf(type));
    }

}

