package com.android.hyb.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;

import butterknife.BindView;

/**
 * 热卖商品
 */
public class BestSellersFragment extends BaseFragment {

    @BindView(R.id.tab_rv)
    RecyclerView tabRv;
    @BindView(R.id.content_rv)
    RecyclerView contentRv;

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



        contentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initData() {

    }
}
