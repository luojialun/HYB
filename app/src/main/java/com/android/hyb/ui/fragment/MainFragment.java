package com.android.hyb.ui.fragment;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.BannerResponse;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.GoodsListActivity;
import com.android.hyb.ui.adapter.GoodsAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.widget.GlideImageLoader;
import com.android.hyb.widget.pop.MainTipPop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页fragment
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.goodsRv)
    RecyclerView goodsRv;


    @Override
    public int setViewId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {
        initBanner();
        initRecyclerView();

    }

    private void initBanner() {
        ServiceFactory.createHYBService(ContentService.class).getBannerList()
                .compose(new RemoteTransformer<BannerResponse>())
                .subscribe(new ToastObserver<BannerResponse>(getActivity()) {
                    @Override
                    public void onNext(BannerResponse response) {
                        if (null != response && 0 < response.getData().size()) {
                            banner.isAutoPlay(true);
                            //设置轮播时间
                            banner.setDelayTime(3000);
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                            List<String> images = new ArrayList<>();
                            for (BannerResponse.BannerBean bannerBean : response.getData()) {
                                images.add(bannerBean.getUrl());
                            }
                            banner.setImages(images);
                            banner.start();
                        }
                    }
                });
    }

    private void initRecyclerView() {
        ServiceFactory.createHYBService(ContentService.class).getGoodsCategoryList()
                .compose(new RemoteTransformer<GoodsCategoryResponse>())
                .subscribe(new ToastObserver<GoodsCategoryResponse>(getActivity()) {
                    @Override
                    public void onNext(GoodsCategoryResponse response) {
                        if (null != response && 0 < response.getData().size()) {
                            goodsRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                            List<GoodsCategoryResponse.GoodsCategoryBean> goodsList = new ArrayList<>();

                            for (GoodsCategoryResponse.GoodsCategoryBean goodsCategoryBean : response.getData()) {
                                goodsList.add(goodsCategoryBean);
                            }
                            GoodsAdapter goodsAdapter = new GoodsAdapter(goodsList);
                            goodsRv.setAdapter(goodsAdapter);

                            goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                                    intent.putExtra(ConstUtils.TITLE, goodsList.get(position).getName());
                                    intent.putExtra(ConstUtils.ID, goodsList.get(position).getId());
                                    intent.putExtra(ConstUtils.POSITION, position);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tip_ll})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tip_ll:
                MainTipPop mainTipPop = new MainTipPop(getActivity());
                mainTipPop.showPopupWindow();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        if (null != banner) {
            banner.startAutoPlay();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        if (null != banner) {
            banner.stopAutoPlay();
        }
    }

}
