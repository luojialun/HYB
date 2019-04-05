package com.android.hyb.ui.acitvity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.util.ToastUtils;
import com.android.hyb.widget.pop.ListSingleSelectPop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.root_ll)
    LinearLayout rootLl;
    @BindView(R.id.pay_password_et)
    EditText payPasswordEt;
    @BindView(R.id.pay_method_tv)
    TextView payMethodTv;
    @BindView(R.id.pay_password_rl)
    RelativeLayout payPasswordRl;
    @BindView(R.id.pay_password_view)
    View payPasswordView;

    private ListSingleSelectPop pop;

    @Override
    public int setViewId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.pay_method_rl, R.id.submit_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_method_rl:
                List<String> contentList = new ArrayList<>();
                contentList.add("余额支付");
                contentList.add("支付宝支付");
                contentList.add("微信支付");
                pop = new ListSingleSelectPop(this, contentList);
                pop.setOnItemClickListener(new ListSingleSelectPop.OnItemClickListener() {
                    @Override
                    public void setOnItemClick(int position, String typeContent) {
                        payMethodTv.setText(typeContent);
                        switch (position) {
                            case 0:
                                payPasswordRl.setVisibility(View.VISIBLE);
                                payPasswordView.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                            case 2:
                                payPasswordRl.setVisibility(View.GONE);
                                payPasswordView.setVisibility(View.GONE);
                                break;
                        }
                    }
                });
                pop.showPopupWindow(rootLl);
                break;
            case R.id.submit_tv:
                if (TextUtils.isEmpty(payPasswordEt.getText().toString())) {
                    ToastUtils.show(this, "请输入支付密码");
                } else {
                    submit();
                }
                break;
        }
    }

    private void submit() {
        ToastUtils.show(this, "订单提交成功");
        finish();
    }

    @Override
    public void onBackPressed() {
        if (null != pop && pop.isPopShowing()) {
            pop.dissmiss();
        }else{
            super.onBackPressed();
        }

    }
}
