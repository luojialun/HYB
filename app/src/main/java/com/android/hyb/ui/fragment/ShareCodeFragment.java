package com.android.hyb.ui.fragment;


import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.response.GetPlatformInfoResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;

import butterknife.BindView;

/**
 * 分享码fragment
 */
public class ShareCodeFragment extends BaseFragment {

    @BindView(R.id.shareCode_image)
    ImageView shareCodeImage;

    @Override
    public int setViewId() {
        return R.layout.fragment_share_code;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ServiceFactory.createHYBService(ContentService.class).GetPlatformInifo()
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetPlatformInfoResponse>(this.getContext()) {
                    @Override
                    public void onNext(GetPlatformInfoResponse response) {
                        GlideApp.with(ShareCodeFragment.this).load(response.getData().getExtensionUrl()).into(shareCodeImage);
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }
}
