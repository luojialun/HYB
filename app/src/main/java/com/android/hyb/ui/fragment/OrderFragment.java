package com.android.hyb.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.OrdersAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.widget.MyRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.orderRv)
    MyRecyclerView orderRv;

    public int type;
    private int page;
    private List<GetOrderListResponse.OrderListBean> orderList;
    private OrdersAdapter adapter;

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
        initRecyclerView();
    }

    @Override
    public void initData() {

        int orderStatus = -1;
        switch (type) {
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
        page = 1;
        ServiceFactory.createHYBService(ContentService.class).GetOrderList(token, page, 10, orderStatus)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetOrderListResponse>(this.getContext()) {
                    @Override
                    public void onNext(GetOrderListResponse getOrderListResponse) {
                        if (getOrderListResponse.status.equals("success")) {
                            orderList = getOrderListResponse.getData();
                            adapter.setNewData(orderList);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }


    private void initRecyclerView() {
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new OrdersAdapter(null);
        orderRv.setAdapter(adapter);
    }


}

