package com.android.hyb.ui.acitvity;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.base.GlideApp;
import com.android.hyb.util.ToastUtils;
import com.android.hyb.widget.pop.ShareCodePop;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class GoodsDetailsActivity extends BaseActivity {

    @BindView(R.id.content_iv)
    ImageView contentIv;
    @BindView(R.id.desc_tv)
    TextView descTv;

    @Override
    public int setViewId() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void initView() {
        descTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                copy(descTv.getText().toString());
                return false;
            }
        });
    }

    @Override
    public void initData() {
        GlideApp.with(this).load("http://b-ssl.duitang.com/uploads/item/201501/29/20150129204856_FZWve.jpeg").into(contentIv);
    }

    @OnClick({R.id.desc_tv, R.id.shop_ll, R.id.share_code_ll, R.id.buy_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.desc_tv:
                ToastUtils.show(this, "长按复制内容");
                break;
            case R.id.shop_ll:
                finish();
                break;
            case R.id.share_code_ll:
                ShareCodePop shareCodePop=new ShareCodePop(this);
                shareCodePop.showPopupWindow();
                break;
            case R.id.buy_tv:
                readyGo(OrderDetailsActivity.class);
                break;
        }
    }

    public void copy(String content) {
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content);
    }
}
