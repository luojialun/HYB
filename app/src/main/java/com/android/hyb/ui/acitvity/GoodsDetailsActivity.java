package com.android.hyb.ui.acitvity;

import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.base.GlideApp;

import butterknife.BindView;

public class GoodsDetailsActivity extends BaseActivity {

    @BindView(R.id.content_iv)
    ImageView contentIv;

    @Override
    public int setViewId() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        GlideApp.with(this).load("http://b-ssl.duitang.com/uploads/item/201501/29/20150129204856_FZWve.jpeg").into(contentIv);
    }
}
