package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;

import butterknife.BindView;

public class UploadActivity extends BaseActivity {

    @BindView(R.id.goods_category_et)
    EditText goodsCategoryEt;
    @BindView(R.id.goods_name_et)
    EditText goodsNameEt;
    @BindView(R.id.goods_detail_et)
    EditText goodsDetailEt;
    @BindView(R.id.goods_price_et)
    EditText goodsPriceEt;
    @BindView(R.id.upload_tv)
    TextView uploadTv;

    @Override
    public int setViewId() {
        return R.layout.activity_upload;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
