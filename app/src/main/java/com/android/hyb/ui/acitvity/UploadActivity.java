package com.android.hyb.ui.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.TakePhotoPopwindow;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tencent.mm.opensdk.utils.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UploadActivity extends BaseActivity {

    @BindView(R.id.goods_category_et)
    EditText goodsCategoryEt;
    @BindView(R.id.goods_name_et)
    EditText goodsNameEt;
    @BindView(R.id.goods_detail_et)
    EditText goodsDetailEt;
    @BindView(R.id.goods_price_et)
    EditText goodsPriceEt;
    @BindView(R.id.upload_tv)
    TextView uploadTv;
    @BindView(R.id.add_goods_ll)
    LinearLayout addGoodsLl;

    private int id = 0;

    @Override
    public int setViewId() {
        return R.layout.activity_upload;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initParams() {
        super.initParams();
        id = getIntent().getIntExtra(ConstUtils.ID, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    images = PictureSelector.obtainMultipleResult(data);

                    break;
            }
        }
    }

    @OnClick({R.id.select_iv, R.id.upload_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_iv: {
                TakePhotoPopwindow takePhotoPopwindow = new TakePhotoPopwindow(this);
                takePhotoPopwindow.showBottomPop(addGoodsLl);
                takePhotoPopwindow.setCallBackListener(new TakePhotoPopwindow.OnTakePhotoCallBackListener() {
                    @Override
                    public void OnClickTakePhotoButton() {
                        //拍照
                        PictureSelector.create(UploadActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }

                    @Override
                    public void OnClickOpenPhotoButton() {
                        //相册
                        PictureSelector.create(UploadActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(1)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }
                });
            }
            break;
            case R.id.upload_tv: {
                String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
                ServiceFactory.createHYBService(ContentService.class)
                        .UploadGoods(token, id, 1, goodsNameEt.getText().toString(), goodsDetailEt.getText().toString(), "111", 12.2)
                        .compose(new RemoteTransformer<ApplyForBusinessResponse>())
                        .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {
                            @Override
                            public void onNext(ApplyForBusinessResponse response) {
                                Log.e("eeeee", response.getData());
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);

                            }
                        });
            }
            break;
        }
    }
}
