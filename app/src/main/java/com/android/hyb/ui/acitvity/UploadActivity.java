package com.android.hyb.ui.acitvity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.hyb.BuildConfig;
import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.BusinessSingleGoodBean;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.android.hyb.widget.pop.ListSingleSelectPop;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tencent.mm.opensdk.utils.Log;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传商品页
 */
public class UploadActivity extends BaseActivity {

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
    @BindView(R.id.select_iv)
    ImageView selectIv;
    @BindView(R.id.goods_category_tv)
    TextView categoryTv;
    @BindView(R.id.add_goods_content_ll)
    ScrollView addGoodsContentLl;

    private int id = 0;
    private int categoryId = -1;  //类别id
    private String filePath;   //上传图片路径
    private String imageURL;

    @Override
    public int setViewId() {
        return R.layout.activity_upload;
    }

    @Override
    public void initView() {
        addGoodsContentLl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dismissKeyboard();
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initParams() {
        id = getIntent().getIntExtra(ConstUtils.ID, 0);
        if (id != 0) {
            uploadTv.setText("更新商品");
            String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
            ServiceFactory.createHYBService(ContentService.class)
                    .getBusinessGoodsById(token, id)
                    .compose(new RemoteTransformer<BusinessSingleGoodBean>())
                    .subscribe(new ToastObserver<BusinessSingleGoodBean>(this) {
                        @Override
                        public void onNext(BusinessSingleGoodBean responseSingleGoodBean) {
                            if (responseSingleGoodBean.getData() != null) {
                                String url = BuildConfig.serverUrl + "/Yinliubao/images" + responseSingleGoodBean.getData().getUrl();
                                Glide.with(getActicity()).load(url).into(selectIv);
                                imageURL = responseSingleGoodBean.getData().getUrl();
                                ServiceFactory.createHYBService(ContentService.class).getGoodsCategoryList()
                                        .compose(new RemoteTransformer<GoodsCategoryResponse>())
                                        .subscribe(new ToastObserver<GoodsCategoryResponse>(UploadActivity.this) {
                                            @Override
                                            public void onNext(GoodsCategoryResponse response) {
                                                if (null != response && 0 < response.getData().size()) {
                                                    for (GoodsCategoryResponse.GoodsCategoryBean bean : response.getData()) {
                                                        if (bean.getId() == responseSingleGoodBean.getData().getCategoryId()) {
                                                            categoryId = bean.getId();
                                                            categoryTv.setText(bean.getName());
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                goodsNameEt.setText(responseSingleGoodBean.getData().getName());
                                goodsDetailEt.setText(responseSingleGoodBean.getData().getDetails());
                                goodsPriceEt.setText(responseSingleGoodBean.getData().getPresentPrice() + "");
                            }
                        }

                        @Override
                        public void onError(ErrorException e) {
                            super.onError(e);

                        }
                    });
            ;
        }
    }

    @OnClick({R.id.select_ll, R.id.upload_tv, R.id.goods_category_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_ll:
                showSelectPop();
                break;
            case R.id.upload_tv:
                if (TextUtils.isEmpty(imageURL)) {
                    ToastUtils.show(UploadActivity.this, "请选择上传的图片");
                } else if (TextUtils.isEmpty(categoryTv.getText())) {
                    ToastUtils.show(UploadActivity.this, "请选择商品类别");
                } else if (TextUtils.isEmpty(goodsNameEt.getText())) {
                    ToastUtils.show(UploadActivity.this, "请输入商品名称");
                } else if (TextUtils.isEmpty(goodsDetailEt.getText())) {
                    ToastUtils.show(UploadActivity.this, "请输入商品详情");
                } else if (TextUtils.isEmpty(goodsPriceEt.getText())) {
                    ToastUtils.show(UploadActivity.this, "请输入商品价格");
                } else {
                    uploadGoods();
                }
                break;
            case R.id.goods_category_tv:
                getCategory();
                break;
        }
    }

    private void showSelectPop() {
        List<String> contentList = new ArrayList<>();
        contentList.add("拍照");
        contentList.add("相册");

        ListSingleSelectPop pop = new ListSingleSelectPop(this, contentList);
        pop.setOnItemClickListener(new ListSingleSelectPop.OnItemClickListener() {
            @Override
            public void setOnItemClick(int position, String typeContent) {
                switch (position) {
                    case 0: //拍照
                        checkTakePicPermission();
                        break;
                    case 1:  //相册
                        checkPickMediaPermission();
                        break;
                }
            }
        });
        pop.showPopupWindow(addGoodsLl);
    }

    /**
     * 检查拍摄照片权限
     */
    private void checkTakePicPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            goToCamera();
        } else {
            AndPermission.with(this).runtime()
                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            goToCamera();
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            ToastUtils.show(UploadActivity.this, "请开启所需要的权限");
                            AndPermission.with(UploadActivity.this)
                                    .runtime()
                                    .setting()
                                    .start(ConstUtils.REQUEST_PERMISSION);
                        }
                    })
                    .start();
        }
    }

    /**
     * 照相机
     */
    public void goToCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 检查选择照片（视频）权限
     */
    private void checkPickMediaPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            goToGallery();
        } else {
            AndPermission.with(this).runtime()
                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            goToGallery();
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            ToastUtils.show(UploadActivity.this, "请开启所需要的权限");
                            AndPermission.with(UploadActivity.this)
                                    .runtime()
                                    .setting()
                                    .start(ConstUtils.REQUEST_PERMISSION);
                        }
                    })
                    .start();
        }
    }

    /*
     * 系统图库
     */
    public void goToGallery() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(false)
                .isGif(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 获取tab标签
     */
    private void getCategory() {
        ServiceFactory.createHYBService(ContentService.class).getGoodsCategoryList()
                .compose(new RemoteTransformer<GoodsCategoryResponse>())
                .subscribe(new ToastObserver<GoodsCategoryResponse>(this) {
                    @Override
                    public void onNext(GoodsCategoryResponse response) {
                        if (null != response && 0 < response.getData().size()) {
                            List<String> contentList = new ArrayList<>();
                            for (GoodsCategoryResponse.GoodsCategoryBean bean : response.getData()) {
                                contentList.add(bean.getName());
                            }
                            ListSingleSelectPop pop = new ListSingleSelectPop(UploadActivity.this, contentList);
                            pop.setOnItemClickListener(new ListSingleSelectPop.OnItemClickListener() {
                                @Override
                                public void setOnItemClick(int position, String typeContent) {
                                    categoryId = response.getData().get(position).getId();
                                    categoryTv.setText(typeContent);
                                }
                            });
                            pop.showPopupWindow(addGoodsLl);
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
                    if (0 < localMediaList.size()) {
                        filePath = localMediaList.get(0).getPath();
                        Glide.with(UploadActivity.this).load(filePath).into(selectIv);
                        uploadImage(filePath);
                    }

                    break;
            }
        }
    }

    /**
     * 图片上传
     *
     * @param filePath 文件路径
     */
    private void uploadImage(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        RequestBody requestBody = builder.build();
        ServiceFactory.createHYBService(ContentService.class).uploadImage(requestBody)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {
                    @Override
                    public void onNext(ApplyForBusinessResponse emptyResponse) {
                        if (emptyResponse.getData() != null) {
                            imageURL = emptyResponse.getData();
                        }
                    }
                });
    }

    /**
     * 商品上传
     */
    private void uploadGoods() {
        String token = UserInfo.getToken();
        ServiceFactory.createHYBService(ContentService.class)
                .UploadGoods(token, id, categoryId, goodsNameEt.getText().toString(), goodsDetailEt.getText().toString(), imageURL, Double.parseDouble(goodsPriceEt.getText().toString()))
                .compose(new RemoteTransformer<ApplyForBusinessResponse>())
                .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {
                    @Override
                    public void onNext(ApplyForBusinessResponse response) {
                        ToastUtils.show(UploadActivity.this, response.getData());
                        Log.e("TAG", response.getData());
                        finish();
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);

                    }
                });
    }

    public void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
