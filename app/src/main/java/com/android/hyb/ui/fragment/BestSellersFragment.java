package com.android.hyb.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.GoodsDetailsActivity;
import com.android.hyb.ui.acitvity.SearchActivity;
import com.android.hyb.ui.adapter.TabAdapter;
import com.android.hyb.ui.adapter.TabContentAdapter;
import com.android.hyb.util.ConstUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 热卖商品
 */
public class BestSellersFragment extends BaseFragment {

    @BindView(R.id.tab_rv)
    RecyclerView tabRv;
    @BindView(R.id.content_rv)
    RecyclerView contentRv;

    private TabAdapter tabAdapter;
    private TabContentAdapter tabContentAdapter;

    private int pageIndex = 1;
    private int categoryId;   //分类标签id
    private int lasSelectPosition = 0;  //上次选中的标签位置

    @Override
    public int setViewId() {
        return R.layout.fragment_best_sellers;
    }

    @Override
    public void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        tabRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        tabAdapter = new TabAdapter(null);
        tabRv.setAdapter(tabAdapter);

        contentRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tabContentAdapter = new TabContentAdapter(null);
        contentRv.setAdapter(tabContentAdapter);

        tabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((GoodsCategoryResponse.GoodsCategoryBean) adapter.getData().get(lasSelectPosition)).setSelected(false);
                ((GoodsCategoryResponse.GoodsCategoryBean) adapter.getData().get(position)).setSelected(true);
                adapter.notifyItemChanged(lasSelectPosition);
                adapter.notifyItemChanged(position);
                lasSelectPosition = position;
                pageIndex = 1;
                categoryId = ((GoodsCategoryResponse.GoodsCategoryBean) adapter.getData().get(position)).getId();
                getGoodsList(categoryId);
            }
        });

        tabContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                getGoodsList(categoryId);
            }
        }, contentRv);

        tabContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent detailsIntent = new Intent(getActivity(), GoodsDetailsActivity.class);
                detailsIntent.putExtra(ConstUtils.ID, ((GoodsResponse.GoodsBean) adapter.getData().get(position)).getId());
                startActivity(detailsIntent);
            }
        });

    }

    @Override
    public void initData() {
        getCategory();
    }

    /**
     * 获取tab标签
     */
    private void getCategory() {
        ServiceFactory.createHYBService(ContentService.class).getGoodsCategoryList()
                .compose(new RemoteTransformer<GoodsCategoryResponse>())
                .subscribe(new ToastObserver<GoodsCategoryResponse>(getActivity()) {
                    @Override
                    public void onNext(GoodsCategoryResponse response) {
                        if (null != response && 0 < response.getData().size()) {
                            response.getData().get(0).setSelected(true);
                            tabAdapter.setNewData(response.getData());
                            getGoodsList(response.getData().get(0).getId());
                        }
                    }
                });

    }

    /**
     * 获取标签内容列表
     *
     * @param categoryId
     */
    private void getGoodsList(int categoryId) {
        showProgress();
        ServiceFactory.createHYBService(ContentService.class).getGoodsList(categoryId, pageIndex, ConstUtils.PAGE_SIZE, ConstUtils.SALES, false)
                .compose(new RemoteTransformer<GoodsResponse>())
                .subscribe(new ToastObserver<GoodsResponse>(getActivity()) {
                    @Override
                    public void onNext(GoodsResponse response) {
                        dissmissProgress();
                        if (null != response && 0 < response.getData().size()) {
                            if (1 == pageIndex) {
                                tabContentAdapter.setNewData(response.getData());
                            } else {
                                tabContentAdapter.addData(response.getData());
                            }

                            if (response.getData().size() < ConstUtils.PAGE_SIZE) {
                                tabContentAdapter.loadMoreEnd();
                            } else {
                                tabContentAdapter.loadMoreComplete();
                            }

                        } else {
                            tabContentAdapter.setNewData(null);
                            tabContentAdapter.setEmptyView(R.layout.layout_empty, contentRv);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        dissmissProgress();
                    }
                });
    }

    @OnClick({R.id.search_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_tv:
                readyGo(SearchActivity.class);
                break;
        }
    }


}
