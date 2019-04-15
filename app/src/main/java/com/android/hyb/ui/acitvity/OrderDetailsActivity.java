package com.android.hyb.ui.acitvity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.PayResult;
import com.android.hyb.bean.event.WechatFeedBackEvent;
import com.android.hyb.util.AppUtils;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.ToastUtils;
import com.android.hyb.widget.pop.ListSingleSelectPop;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.root_ll)
    LinearLayout rootLl;
    @BindView(R.id.pay_method_tv)
    TextView payMethodTv;
    @BindView(R.id.pay_password_view)
    View payPasswordView;

    private ListSingleSelectPop pop;
    private int payType;

    private static final int SDK_PAY_FLAG = 1;
    public static final int ALI = 0;
    public static final int WEHCAT = 1;

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
                contentList.add("支付宝支付");
                contentList.add("微信支付");
                pop = new ListSingleSelectPop(this, contentList);
                pop.setOnItemClickListener(new ListSingleSelectPop.OnItemClickListener() {
                    @Override
                    public void setOnItemClick(int position, String typeContent) {
                        payType = position;
                        payMethodTv.setText(typeContent);
                    }
                });
                pop.showPopupWindow(rootLl);
                break;
            case R.id.submit_tv:
                submit();
                break;
        }
    }

    private void submit() {
        switch (payType) {
            case ALI:
                if (AppUtils.isAliPayInstall(this)) {
                    aliPay();
                } else {
                    ToastUtils.show(this, "请先安装支付宝");
                }
                break;
            case WEHCAT:
                if (AppUtils.isWechatInstall(this)) {
                    wechatPay();
                } else {
                    ToastUtils.show(this, "请先安装微信");
                }
                break;
        }
    }

    private void aliPay() {
        final String orderInfo = "";   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailsActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 支付宝支付相关回调信息
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);

            switch (payResult.getResultStatus()) {
                case "9000":
                    aliPayCheck();
                    break;
                case "6001":
                    ToastUtils.show(OrderDetailsActivity.this, "支付取消");
                    break;
                case "4000":
                    ToastUtils.show(OrderDetailsActivity.this, "支付失败");
                    break;
            }
        }
    };

    /**
     * 支付宝支付校验
     */
    private void aliPayCheck() {

    }

    private void wechatPay() {
        IWXAPI api = WXAPIFactory.createWXAPI(this, ConstUtils.WECHAT_PAY_APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(ConstUtils.WECHAT_PAY_APP_ID);

        WXTextObject textObj = new WXTextObject();
        textObj.text = "text";

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = "description";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());  //transaction字段用与唯一标示一个请求
        req.message = msg;
        //req.scene = mTargetScene;

        //调用api接口，发送数据到微信
        api.sendReq(req);

    }

    @Override
    public boolean bindEventbus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wechatFeedBack(WechatFeedBackEvent event) {
        switch (event.getCode()) {
            case BaseResp.ErrCode.ERR_OK:
                wechatPayCheck();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.show(this, "支付取消了");
                break;
        }
    }

    /**
     * 微信支付校验
     */
    private void wechatPayCheck() {

    }

    @Override
    public void onBackPressed() {
        if (null != pop && pop.isPopShowing()) {
            pop.dissmiss();
        } else {
            super.onBackPressed();
        }

    }
}
