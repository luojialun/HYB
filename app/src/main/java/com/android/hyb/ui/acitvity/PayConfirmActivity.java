package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.event.WechatFeedBackEvent;
import com.android.hyb.bean.response.ApplyForVipResponse;
import com.android.hyb.bean.response.PayBean;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mm.opensdk.utils.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class PayConfirmActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.wechat_iv)
    ImageView wechatIv;
    @BindView(R.id.wechat_rl)
    RelativeLayout wechatRl;
    @BindView(R.id.alipay_iv)
    ImageView alipayIv;
    @BindView(R.id.alipay_rl)
    RelativeLayout alipayRl;
    @BindView(R.id.confirm_tv)
    TextView confirmTv;
    @BindView(R.id.wechat_select_iv)
    ImageView wechatSelectIv;
    @BindView(R.id.alipay_select_iv)
    ImageView alipaySelectIv;
    @BindView(R.id.tips_tv)
    TextView tipsTv;

    private int type = -1;
    private int id;

    @Override
    public int setViewId() {
        return R.layout.activity_pay_confirm;
    }

    @Override
    public void initView() {
        if (type == 0)
        {
            tipsTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {

    }

    public void initParams() {
        type = getIntent().getIntExtra(ConstUtils.TYPE, -1);
        id = getIntent().getIntExtra(ConstUtils.ID, -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.wechat_rl, R.id.alipay_rl, R.id.confirm_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wechat_rl: {
                wechatSelectIv.setImageResource(R.mipmap.pay_select);
                alipaySelectIv.setImageResource(R.mipmap.pay_unselect);
            }
            break;
            case R.id.alipay_rl: {
                ToastUtils.show(this, "暂不支持");
//                wechatSelectIv.setImageResource(R.mipmap.pay_unselect);
//                alipaySelectIv.setImageResource(R.mipmap.pay_select);
            }
            break;
            case R.id.confirm_tv: {
                if (type == 0) {
                    getVipPay();
                } else if (type == 1) {
                    order();
                }
            }
            break;
        }
    }


    public void getVipPay() {

        ServiceFactory.createHYBService(ContentService.class).ApplyForVip(UserInfo.getToken())
                .compose(new RemoteTransformer<ApplyForVipResponse>())
                .subscribe(new ToastObserver<ApplyForVipResponse>(this) {
                    @Override
                    public void onNext(ApplyForVipResponse response) {
                        if (null != response) {
                            if (response.getData().getStatus().equals("success")) {

                                Gson gson = new Gson();
                                PayBean payBean = gson.fromJson(response.getData().getJsApiParam(), PayBean.class);

                                IWXAPI msgApi = WXAPIFactory.createWXAPI(PayConfirmActivity.this, ConstUtils.WECHAT_PAY_APP_ID);

                                PayReq request = new PayReq();
                                request.appId = ConstUtils.WECHAT_PAY_APP_ID;
                                request.partnerId = payBean.getPartnerid();
                                request.prepayId = payBean.getPrepayid();
                                request.packageValue = payBean.getPackageX();
                                request.nonceStr = payBean.getNoncestr();
                                request.timeStamp = payBean.getTimestamp();
                                request.sign = payBean.getSign();
                                msgApi.sendReq(request);
                            } else {
                                ToastUtils.show(getActicity(), response.getData().getMessage());
                            }
                        }
                    }
                });
    }

    public void order() {
        ServiceFactory.createHYBService(ContentService.class).placeNewOrder(SPUtils.getInstance().getString(ConstUtils.TOKEN), id)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<ApplyForVipResponse>(this) {
                    @Override
                    public void onNext(ApplyForVipResponse response) {
                        if (null != response) {
                            if (response.getData().getStatus().equals("success")) {

                                Gson gson = new Gson();
                                PayBean payBean = gson.fromJson(response.getData().getJsApiParam(), PayBean.class);

                                IWXAPI msgApi = WXAPIFactory.createWXAPI(PayConfirmActivity.this, ConstUtils.WECHAT_PAY_APP_ID);

                                PayReq request = new PayReq();
                                request.appId = ConstUtils.WECHAT_PAY_APP_ID;
                                request.partnerId = payBean.getPartnerid();
                                request.prepayId = payBean.getPrepayid();
                                request.packageValue = payBean.getPackageX();
                                request.nonceStr = payBean.getNoncestr();
                                request.timeStamp = payBean.getTimestamp();
                                request.sign = payBean.getSign();
                                msgApi.sendReq(request);
                            } else {
                                ToastUtils.show(getActicity(), response.getData().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }


    @Override
    public boolean bindEventbus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wechatFeedBack(WechatFeedBackEvent event) {
        if (event.getCode() == 1) {
            ToastUtils.show(this, "支付成功");
            finish();
        } else {
            ToastUtils.show(this, "支付失败");

        }
        Log.e("TAG", "errorcode-->" + event.getCode());
    }

}
