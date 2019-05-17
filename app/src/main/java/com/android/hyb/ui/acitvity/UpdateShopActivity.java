package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.BusinessGoodsResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.ShopAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.widget.MyRecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;

public class UpdateShopActivity extends BaseActivity {

    @BindView(R.id.shopRv)
    MyRecyclerView shopRv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int pageIndex = 1;
    private ShopAdapter adapter = new ShopAdapter(null);

    @Override
    public int setViewId() {
        return R.layout.activity_update_shop;
    }

    @Override
    public void initData() {
        reloadNewData();
    }

    @Override
    public void initView() {
        shopRv.setLayoutManager(new LinearLayoutManager(this));
        shopRv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                reloadMoreData();
            }
        },shopRv);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void reloadNewData() {
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        pageIndex = 1;
        ServiceFactory.createHYBService(ContentService.class)
                .getBusinessGoodsList(token, 0, pageIndex, 10, "sales", false)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<BusinessGoodsResponse>(this) {
                    @Override
                    public void onNext(BusinessGoodsResponse response) {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setNewData(response.getData());
                    }

                    @Override
                    public void onError(ErrorException e) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void reloadMoreData() {
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        pageIndex++;
        ServiceFactory.createHYBService(ContentService.class)
                .getBusinessGoodsList(token, 0, pageIndex, 10, "sales", false)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<BusinessGoodsResponse>(this) {
                    @Override
                    public void onNext(BusinessGoodsResponse response) {
                        if (response.getData().size() > 0)
                        {
                            adapter.addData(response.getData());
                            adapter.loadMoreComplete();
                        }
                        else
                        {
                            adapter.loadMoreEnd();
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        adapter.loadMoreComplete();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
