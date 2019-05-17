package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.widget.ImageView;
import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.GetPlatformInfoResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.fragment.ShareCodeFragment;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployActivity extends BaseActivity {

    @BindView(R.id.share_image)
    ImageView shareImage;

    @Override
    public int setViewId() {
        return R.layout.activity_employ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ServiceFactory.createHYBService(ContentService.class).GetPlatformInifo()
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetPlatformInfoResponse>(this) {
                    @Override
                    public void onNext(GetPlatformInfoResponse response) {
                        Glide.with(EmployActivity.this).load(response.getData().getExtensionUrl()).into(shareImage);
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }
}
