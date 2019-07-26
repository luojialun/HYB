package com.android.hyb.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.OrderActivity;
import com.android.hyb.ui.acitvity.OrderMoreActivity;
import com.android.hyb.ui.adapter.OrdersAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.widget.MyRecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.orderRv)
    MyRecyclerView orderRv;

    public int type;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

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
        page = 0;
        loadMore();
    }

    public void loadMore() {
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
        page++;
        ServiceFactory.createHYBService(ContentService.class).GetOrderList(token, page, 10, orderStatus)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetOrderListResponse>(this.getContext()) {
                    @Override
                    public void onNext(GetOrderListResponse response) {
                        if (null != response) {
                            if (1 == page) {
                                swipeRefreshLayout.setRefreshing(false);
                                adapter.setNewData(response.getData());
                            } else {
                                adapter.addData(response.getData());
                            }

                            if (response.getData().size() < ConstUtils.PAGE_SIZE) {
                                adapter.loadMoreEnd();
                            } else {
                                adapter.loadMoreComplete();
                            }

                        } else {
                            swipeRefreshLayout.setRefreshing(false);
                            adapter.setNewData(null);
                            adapter.setEmptyView(R.layout.layout_empty,orderRv);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }


    private void initRecyclerView() {
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new OrdersAdapter(null);
        orderRv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        },orderRv);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < adapter.getData().size()){
                    GetOrderListResponse.OrderListBean bean = (GetOrderListResponse.OrderListBean)adapter.getData().get(position);
                    if (bean != null){
                        Intent intent = new Intent(OrderFragment.this.getContext(), OrderMoreActivity.class);
                        intent.putExtra(ConstUtils.ID, bean.getId());
                        startActivity(intent);
                    }
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }
}

