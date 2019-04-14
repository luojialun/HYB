package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.GetApplyForBusinessResponse;
import com.android.hyb.bean.response.UserResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MerchantActivity extends BaseActivity {

    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_apply)
    TextView tvApply;

    @Override
    public int setViewId() {
        return R.layout.activity_merchant;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        ServiceFactory.createHYBService(ContentService.class).GetApplyForBusiness(token).compose(new RemoteTransformer<>()).subscribe(new ToastObserver<GetApplyForBusinessResponse>(this) {

            @Override
            public void onNext(GetApplyForBusinessResponse getApplyForBusinessResponse) {
                GetApplyForBusinessResponse.GetApplyForBusinessBean bean = getApplyForBusinessResponse.getData();
                if (bean != null) {
                    if (bean.getApplyTimeString() != null){
                        tvStatus.setText("申请状态："+bean.getApplyTimeString());
                    }
                    if (bean.getMessage() != null){
                        tvMessage.setText("信息："+bean.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        });
    }

    @OnClick({R.id.tv_apply})
    public void onApplyClicked(){
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        ServiceFactory.createHYBService(ContentService.class).applyForBusiness(token)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {

                    @Override
                    public void onNext(ApplyForBusinessResponse applyForBusinessResponse) {
                        ToastUtils.show(MerchantActivity.this,"申请提交成功");
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        ToastUtils.show(MerchantActivity.this,"申请提交失败");
                    }
                });
    }
}
