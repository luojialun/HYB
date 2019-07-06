package com.android.hyb.ui.acitvity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.event.WechatFeedBackEvent;
import com.android.hyb.bean.response.ApplyForVipResponse;
import com.android.hyb.bean.response.PayBean;
import com.android.hyb.bean.response.PlaceNewOrderResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.ImageUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.mylhyl.zxing.scanner.encode.QREncode;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mm.opensdk.utils.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;

/**
 * 支付的页面
 */
public class PaymentActivity extends BaseActivity {

    @BindView(R.id.code_iv)
    ImageView codeIv;
    @BindView(R.id.time_tv)
    TextView timeTv;

    private Bitmap bitmap;
    private CountDownTimer countDownTimer;
    private int type = -1;
    private int id;
    private PayBean payBean;

    @Override
    public int setViewId() {
        return R.layout.activity_viporder;
    }

    @Override
    public void initParams() {
        type = getIntent().getIntExtra(ConstUtils.TYPE, -1);
        id = getIntent().getIntExtra(ConstUtils.ID, -1);
    }

    @Override
    public void initView() {
        codeIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String galleryPath = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator;
                String path = galleryPath + "hyb" + System.currentTimeMillis() + ".png";
                ImageUtils.save(bitmap, path, Bitmap.CompressFormat.PNG);
                //保存图片后发送广播通知相册刷新
                Uri uri = Uri.fromFile(new File(path));
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                ToastUtils.show(PaymentActivity.this, "已保存到手机相册");
                return false;
            }
        });

    }

    @Override
    public void initData() {
        switch (type) {
            case 0:
                getVipPay();
                break;
            case 1:
                order();
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
//                                Glide.with(getActicity())
//                                        .load(response.getData().getUrl())
//                                        .into(codeIv);

                                Gson gson = new Gson();
                                payBean = gson.fromJson(response.getData().getJsApiParam(), PayBean.class);

                                IWXAPI msgApi = WXAPIFactory.createWXAPI(PaymentActivity.this, ConstUtils.WECHAT_PAY_APP_ID);

                                PayReq request = new PayReq();
                                request.appId = ConstUtils.WECHAT_PAY_APP_ID;
                                request.partnerId = response.getData().getPartnerId();
                                request.prepayId = response.getData().getPrepayId();
                                request.packageValue = payBean.getPackageX();
                                request.nonceStr = payBean.getNonceStr();
                                request.timeStamp = payBean.getTimeStamp();
                                request.sign = payBean.getPaySign();
                                Boolean success = msgApi.sendReq(request);
                                Log.e("ssss", success + "");

                                startTimer(response.getData().getMinutes());
                            } else {
                                ToastUtils.show(getActicity(), response.getData().getMessage());
                                finish();
                            }
                        }
                    }
                });
    }

    public void order() {
        ServiceFactory.createHYBService(ContentService.class).placeNewOrder(SPUtils.getInstance().getString(ConstUtils.TOKEN), id)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<PlaceNewOrderResponse>(this) {
                    @Override
                    public void onNext(PlaceNewOrderResponse response) {
                        if (response.status.equals("success")) {
                            Glide.with(getActicity())
                                    .load(response.getData().getUrl())
                                    .into(codeIv);
                            startTimer(response.getData().getMinutes());
                        } else {
                            ToastUtils.show(PaymentActivity.this, response.message);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }

    public void startTimer(int time) {
        countDownTimer = new CountDownTimer(60 * 1000 * time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) ((millisUntilFinished / 1000) % 60);
                int minite = (int) ((millisUntilFinished / 1000) / 60);
                if (null != timeTv) {
                    timeTv.setText((minite < 10 ? ("0" + minite) : minite) + ":" + (second < 10 ? ("0" + second) : second));
                }
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        countDownTimer.start();
    }

    @Override
    public boolean bindEventbus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wechatFeedBack(WechatFeedBackEvent event) {
        Log.e("TAG", "errorcode-->" + event.getCode());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
