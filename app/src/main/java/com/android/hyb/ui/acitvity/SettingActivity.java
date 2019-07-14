package com.android.hyb.ui.acitvity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.hyb.BuildConfig;
import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
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
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.nikename_tv)
    TextView nikenameTv;
    @BindView(R.id.mobile_tv)
    TextView mobileTv;
//    @BindView(R.id.pay_iv)
//    ImageView payIv;
    @BindView(R.id.setting_ll)
    LinearLayout settingLl;

//    private String filePath;   //上传图片路径
//    private String imageURL;

    @Override
    public int setViewId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        mobileTv.setText(UserInfo.getMobile());
        nikenameTv.setText(UserInfo.getOpenId());
//        if (UserInfo.getWeChatUrl().length() != 0) {
//            Glide.with(this)
//                    .load(BuildConfig.serverUrl + "/Yinliubao/images" +  UserInfo.getWeChatUrl())
//                    .into(payIv);
//        }
//
//        if (UserInfo.getAlipayUrl().length() != 0) {
//            Glide.with(this)
//                    .load(BuildConfig.serverUrl + "/Yinliubao/images" +  UserInfo.getAlipayUrl())
//                    .into(payIv);
//        }

    }

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @OnClick(R.id.code_ll)
//    public void onClick() {
//        List<String> contentList = new ArrayList<>();
//        contentList.add("拍照");
//        contentList.add("相册");
//
//        ListSingleSelectPop pop = new ListSingleSelectPop(this, contentList);
//        pop.setOnItemClickListener(new ListSingleSelectPop.OnItemClickListener() {
//            @Override
//            public void setOnItemClick(int position, String typeContent) {
//                switch (position) {
//                    case 0: //拍照
//                        checkTakePicPermission();
//                        break;
//                    case 1:  //相册
//                        checkPickMediaPermission();
//                        break;
//                }
//            }
//        });
//        pop.showPopupWindow(settingLl);
//    }
//
//    /**
//     * 检查拍摄照片权限
//     */
//    private void checkTakePicPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            goToCamera();
//        } else {
//            AndPermission.with(this).runtime()
//                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    .onGranted(new Action<List<String>>() {
//                        @Override
//                        public void onAction(List<String> data) {
//                            goToCamera();
//                        }
//                    })
//                    .onDenied(new Action<List<String>>() {
//                        @Override
//                        public void onAction(List<String> data) {
//                            ToastUtils.show(SettingActivity.this, "请开启所需要的权限");
//                            AndPermission.with(SettingActivity.this)
//                                    .runtime()
//                                    .setting()
//                                    .start(ConstUtils.REQUEST_PERMISSION);
//                        }
//                    })
//                    .start();
//        }
//    }
//
//    /**
//     * 照相机
//     */
//    public void goToCamera() {
//        PictureSelector.create(this)
//                .openCamera(PictureMimeType.ofImage())
//                .forResult(PictureConfig.CHOOSE_REQUEST);
//    }
//
//    /**
//     * 检查选择照片（视频）权限
//     */
//    private void checkPickMediaPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            goToGallery();
//        } else {
//            AndPermission.with(this).runtime()
//                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    .onGranted(new Action<List<String>>() {
//                        @Override
//                        public void onAction(List<String> data) {
//                            goToGallery();
//                        }
//                    })
//                    .onDenied(new Action<List<String>>() {
//                        @Override
//                        public void onAction(List<String> data) {
//                            ToastUtils.show(SettingActivity.this, "请开启所需要的权限");
//                            AndPermission.with(SettingActivity.this)
//                                    .runtime()
//                                    .setting()
//                                    .start(ConstUtils.REQUEST_PERMISSION);
//                        }
//                    })
//                    .start();
//        }
//    }
//
//    /*
//     * 系统图库
//     */
//    public void goToGallery() {
//        PictureSelector.create(this)
//                .openGallery(PictureMimeType.ofImage())
//                .maxSelectNum(1)
//                .isCamera(false)
//                .isGif(false)
//                .forResult(PictureConfig.CHOOSE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片选择结果回调
//                    // 例如 LocalMedia 里面返回三种path
//                    // 1.media.getPath(); 为原图path
//                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
//                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
//                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
//                    if (0 < localMediaList.size()) {
//                        filePath = localMediaList.get(0).getPath();
//                        Glide.with(SettingActivity.this).load(filePath).into(payIv);
//                        uploadImage(filePath);
//                    }
//
//                    break;
//            }
//        }
//    }
//
//    /**
//     * 图片上传
//     *
//     * @param filePath 文件路径
//     */
//    private void uploadImage(String filePath) {
//        File file = new File(filePath);
//        if (!file.exists()) {
//            return;
//        }
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
//        RequestBody requestBody = builder.build();
//        ServiceFactory.createHYBService(ContentService.class).uploadImage(requestBody)
//                .compose(new RemoteTransformer<>())
//                .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {
//                    @Override
//                    public void onNext(ApplyForBusinessResponse emptyResponse) {
//                        if (emptyResponse.getData() != null){
//                            imageURL = emptyResponse.getData();
//                            uploadGoods();
//                        }
//                    }
//                });
//    }
//
//    private void uploadGoods() {
//        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
//        ServiceFactory.createHYBService(ContentService.class)
//                .UploadAlipayUrl(token,imageURL)
//                .compose(new RemoteTransformer<>())
//                .subscribe(new ToastObserver<ApplyForBusinessResponse>(this) {
//                    @Override
//                    public void onNext(ApplyForBusinessResponse response) {
//                        if (response.getData().equals("success")){
//                            ToastUtils.show(getActicity(),"上传成功");
//                            UserInfo.setAlipayUrl(imageURL);
//                        }
//                    }
//                });
//
//    }
}
