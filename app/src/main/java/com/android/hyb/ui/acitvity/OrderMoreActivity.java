package com.android.hyb.ui.acitvity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.util.ConstUtils;

public class OrderMoreActivity extends BaseActivity {

    private int id = 0;

    @Override
    public int setViewId() {
        return R.layout.activity_order_more;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initParams() {
        super.initParams();
        id = getIntent().getIntExtra(ConstUtils.ID, 0);
    }

    @Override
    public void initView() {

    }

}
