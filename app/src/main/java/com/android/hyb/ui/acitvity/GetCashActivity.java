package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.ConvertUtil;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class GetCashActivity extends BaseActivity {

    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.can_cash_tv)
    TextView canCashTv;

    @Override
    public int setViewId() {
        return R.layout.activity_get_cash;
    }

    @Override
    public void initView() {
        canCashTv.setText("可提现金额：" + UserInfo.getAvailableFunds() + "元");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.submit_tv)
    public void onClick() {
        if (TextUtils.isEmpty(passwordEt.getText().toString())){
            ToastUtils.show(this,"提现金额不可为空");
            return;
        }


        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        ServiceFactory.createHYBService(ContentService.class)
                .Withdraw(token, ConvertUtil.convertToFloat(passwordEt.getText().toString(),0))
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {
                    @Override
                    public void onNext(ApplyForBusinessResponse response) {
                        if (response.getData().equals("success")){
                            ToastUtils.show(getActicity(),"提现申请 发起成功");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });


    }
}
