package com.android.hyb.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {

    public int type;
    @BindView(R.id.test)
    TextView test;

    private int page;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public int setViewId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
//        allFragment.type = 0;
//        unpayFragment.type = 1;
//        unsendFragment.type = 2;
//        ungetFragment.type = 3;
//        finishFragment.type = 4;
        page = 1;
        int orderStatus = -1;
        switch (type){
            case 0:
                orderStatus = -1;
                break;
            case 1:
                orderStatus = 10;
                break;
            case 2:
                orderStatus = 20;
                break;
            case 3:
                orderStatus = 30;
                break;
            case 4:
                orderStatus = 40;
                break;
        }
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);

        ServiceFactory.createHYBService(ContentService.class).GetOrderList(token,page,10,orderStatus)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetOrderListResponse>(this.getContext()){
                    @Override
                    public void onNext(GetOrderListResponse getOrderListResponse) {

                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }

}

