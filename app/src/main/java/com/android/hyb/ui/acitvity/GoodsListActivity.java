package com.android.hyb.ui.acitvity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.bean.response.GoodsResponse.GoodsBean;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.GoodsListAdapter;
import com.android.hyb.util.ConstUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;

/**
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.goods_list_rv)
    RecyclerView goodsListRv;

    private String title;
    private int categoryId;
    private GoodsListAdapter adapter;
    private int pageIndex = 1;

    @Override
    public int setViewId() {
        return R.layout.activity_goods_list;
    }

    @Override
    public void initParams() {
        title = getIntent().getStringExtra(ConstUtils.TITLE);
        categoryId = getIntent().getIntExtra(ConstUtils.ID, 0);
    }

    @Override
    public void initView() {
        titleTv.setText(title);
        initRecyclerView();
    }

    private void initRecyclerView() {
        goodsListRv.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new GoodsListAdapter(null);
        goodsListRv.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_cardview:
                        Intent detailsIntent = new Intent(GoodsListActivity.this, GoodsDetailsActivity.class);
                        detailsIntent.putExtra(ConstUtils.ID, ((GoodsBean) adapter.getData().get(position)).getId());
                        startActivity(detailsIntent);
                        break;
                    case R.id.buy_tv:
                        Intent orderIntent = new Intent(GoodsListActivity.this, OrderDetailsActivity.class);

                        startActivity(orderIntent);
                        break;
                }
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                getGoodsList();
            }
        }, goodsListRv);

    }


    @Override
    public void initData() {
        getGoodsList();
    }

    private void getGoodsList() {
        showProgress();
        ServiceFactory.createHYBService(ContentService.class).getGoodsList(categoryId, pageIndex, ConstUtils.PAGE_SIZE, ConstUtils.SALES, false)
                .compose(new RemoteTransformer<GoodsResponse>())
                .subscribe(new ToastObserver<GoodsResponse>(this) {
                    @Override
                    public void onNext(GoodsResponse response) {
                        dismissProgress();
                        if (null != response && 0 < response.getData().size()) {
                            if (1 == pageIndex) {
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
                            adapter.setEmptyView(R.layout.layout_empty);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        dismissProgress();
                    }
                });
    }
}
