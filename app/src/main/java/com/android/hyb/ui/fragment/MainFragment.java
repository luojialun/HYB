package com.android.hyb.ui.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.clazz.GoodsBean;
import com.android.hyb.ui.adapter.GoodsAdapter;
import com.android.hyb.widget.GlideImageLoader;
import com.android.hyb.widget.MainTipPop;
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
    @BindView(R.id.app_rv)
    RecyclerView appRv;

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
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        List<String> images = new ArrayList<>();
        images.add("http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg");
        images.add("http://pic41.nipic.com/20140429/12728082_192158998000_2.jpg");
        banner.setImages(images);
        banner.start();
    }

    private void initRecyclerView() {
        goodsRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<GoodsBean> goodsList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            goodsList.add(new GoodsBean(R.mipmap.ic_launcher_round, "安卓营销软件"));
        }
        GoodsAdapter goodsAdapter = new GoodsAdapter(goodsList);
        goodsRv.setAdapter(goodsAdapter);

        appRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<GoodsBean> appList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            appList.add(new GoodsBean(R.mipmap.ic_launcher_round, "清理僵尸粉"));
        }

        GoodsAdapter appAdapter = new GoodsAdapter(appList);
        appRv.setAdapter(appAdapter);

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

}
