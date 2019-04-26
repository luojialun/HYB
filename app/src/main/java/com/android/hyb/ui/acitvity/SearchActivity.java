package com.android.hyb.ui.acitvity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.GetListInSearchResponse;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.TabContentAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;

    private String goodsName;
    private int pageIndex = 1;
    private TabContentAdapter adapter;

    @Override
    public int setViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        initRecyclerView();

    }

    private void initRecyclerView() {
        searchRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TabContentAdapter(null);
        searchRv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                getSearchResult();
            }
        }, searchRv);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent detailsIntent = new Intent(SearchActivity.this, GoodsDetailsActivity.class);
                detailsIntent.putExtra(ConstUtils.ID, ((GoodsResponse.GoodsBean) adapter.getData().get(position)).getId());
                startActivity(detailsIntent);
            }
        });

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.back_iv, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.sure_tv:
                goodsName = searchEt.getText().toString().trim();
                if (TextUtils.isEmpty(goodsName)) {
                    ToastUtils.show(this, "请输入搜索内容");
                    return;
                }
                pageIndex = 1;
                showProgress();
                getSearchResult();
                break;
        }
    }

    private void getSearchResult() {

        ServiceFactory.createHYBService(ContentService.class).getListInSearch(goodsName, pageIndex, ConstUtils.PAGE_SIZE, "sales", true)
                .compose(new RemoteTransformer<GetListInSearchResponse>())
                .subscribe(new ToastObserver<GetListInSearchResponse>(this) {
                    @Override
                    public void onNext(GetListInSearchResponse response) {
                        dismissProgress();
                        if (null != response && null != response.getData() && 0 < response.getData().size()) {
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
                            adapter.setNewData(null);
                            adapter.setEmptyView(R.layout.layout_empty);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        dismissProgress();
                        adapter.setNewData(null);
                        adapter.setEmptyView(R.layout.layout_empty);
                    }
                });
    }
}
