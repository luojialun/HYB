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
import com.android.hyb.bean.response.ApplyForVipResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ImageUtils;
import com.android.hyb.util.ToastUtils;
import com.mylhyl.zxing.scanner.encode.QREncode;

import java.io.File;

import butterknife.BindView;

/**
 * VIP的订单页
 */
public class VIPOrderActivity extends BaseActivity {

    @BindView(R.id.code_iv)
    ImageView codeIv;
    @BindView(R.id.time_tv)
    TextView timeTv;

    private Bitmap bitmap;
    private CountDownTimer countDownTimer;

    @Override
    public int setViewId() {
        return R.layout.activity_viporder;
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
                ToastUtils.show(VIPOrderActivity.this, "已保存到手机相册");
                return false;
            }
        });

    }

    @Override
    public void initData() {
        ServiceFactory.createHYBService(ContentService.class).ApplyForVip(UserInfo.getToken())
                .compose(new RemoteTransformer<ApplyForVipResponse>())
                .subscribe(new ToastObserver<ApplyForVipResponse>(this) {
                    @Override
                    public void onNext(ApplyForVipResponse response) {
                        if (null != response) {
                            Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.wechat);
                            bitmap = new QREncode.Builder(VIPOrderActivity.this)
                                    .setColor(getResources().getColor(R.color.black))//二维码颜色
                                    //.setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                                    .setContents(response.getData().getUrl())//二维码内容
                                    .setLogoBitmap(logoBitmap)//二维码中间logo
                                    .setMargin(0)
                                    .build().encodeAsBitmap();
                            codeIv.setImageBitmap(bitmap);
                            startTimer(response.getData().getMinutes());
                        }
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
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
