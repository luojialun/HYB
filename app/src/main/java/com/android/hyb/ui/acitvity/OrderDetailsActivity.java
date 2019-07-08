package com.android.hyb.ui.acitvity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.util.ConstUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.root_ll)
    LinearLayout rootLl;
//    @BindView(R.id.pay_method_tv)
//    TextView payMethodTv;
//    @BindView(R.id.pay_password_view)
//    View payPasswordView;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;

    private GoodsResponse.GoodsBean goodsBean;

    @Override
    public int setViewId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initParams() {
        goodsBean = (GoodsResponse.GoodsBean) getIntent().getSerializableExtra(ConstUtils.Bean);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        if (null != goodsBean) {
            tvGoodsName.setText(goodsBean.getName());
            tvGoodsPrice.setText("¥" +goodsBean.getPresentPrice());
        }
    }

    @OnClick({R.id.submit_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_tv:
                if (null != goodsBean) {
                    submit();
                }
                break;
        }
    }

    private void submit() {
        //支付成功之后去后台下单，此为下单接口。
        Intent intent = new Intent(this, PayConfirmActivity.class);
        intent.putExtra(ConstUtils.TYPE, 1);
        intent.putExtra(ConstUtils.ID, goodsBean.getId());
        startActivity(intent);
        finish();

    }

    @Override
    public boolean bindEventbus() {
        return false;
    }

}
