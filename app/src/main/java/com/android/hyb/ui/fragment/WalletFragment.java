package com.android.hyb.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.WalletRecordResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.OrdersAdapter;
import com.android.hyb.ui.adapter.WalletAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.widget.MyRecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends BaseFragment {

    @BindView(R.id.walletRv)
    MyRecyclerView walletRv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int page = 1;
    private WalletAdapter adapter = new  WalletAdapter(null);

    private int type = 10;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public int setViewId() {
        return R.layout.fragment_wallet;
    }

    @Override
    public void initView() {
        walletRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        walletRv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                reloadMoreData();
            }
        },walletRv);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    @Override
    public void initData() {
        reloadNewData();
    }

    private void reloadNewData(){
        page = 1;
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        ServiceFactory.createHYBService(ContentService.class).getPageWalletRecord(token, type, 1, 10)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<WalletRecordResponse>(this.getContext()) {
                    @Override
                    public void onNext(WalletRecordResponse response) {
                        swipeRefreshLayout.setRefreshing(false);
                        if (response.status.equals("success")){
                            adapter.setNewData(response.getData());
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void reloadMoreData(){
        page ++;
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        ServiceFactory.createHYBService(ContentService.class).getPageWalletRecord(token, type, 1, 10)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<WalletRecordResponse>(this.getContext()) {
                    @Override
                    public void onNext(WalletRecordResponse response) {
                        if (response.status.equals("success")){
                            if (response.getData().size() > 0){
                                adapter.setNewData(response.getData());
                                adapter.loadMoreEnd();
                            } else {
                                adapter.loadMoreComplete();
                            }

                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        adapter.loadMoreEnd();
                    }
                });
    }
}
