package com.android.hyb.ui.fragment;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.clazz.GoodsBean;
import com.android.hyb.ui.acitvity.CommonH5Activity;
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

        goodsList.add(new GoodsBean(R.mipmap.android, "安卓营销软件"));
        goodsList.add(new GoodsBean(R.mipmap.apple, "苹果营销软件"));
        goodsList.add(new GoodsBean(R.mipmap.computer, "电脑营销软件"));
        goodsList.add(new GoodsBean(R.mipmap.cloud, "云端营销产品"));
        goodsList.add(new GoodsBean(R.mipmap.gift, "VIP会员福利"));
        goodsList.add(new GoodsBean(R.mipmap.hongbao, "云端红包系列"));
        goodsList.add(new GoodsBean(R.mipmap.xiangce, "营销做图软件"));
        goodsList.add(new GoodsBean(R.mipmap.cloud_chat, "云微讯全系列"));
        goodsList.add(new GoodsBean(R.mipmap.hotfans, "云端爆粉产品"));
        goodsList.add(new GoodsBean(R.mipmap.delete, "云端清理产品"));
        goodsList.add(new GoodsBean(R.mipmap.qq, "QQ营销软件"));
        goodsList.add(new GoodsBean(R.mipmap.other, "其他营销软件"));

        GoodsAdapter goodsAdapter = new GoodsAdapter(goodsList);
        goodsRv.setAdapter(goodsAdapter);

        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra(ConstUtils.TITLE, goodsList.get(position).getName());
                intent.putExtra(ConstUtils.POSITION, position);
                startActivity(intent);
            }
        });

        appRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<GoodsBean> appList = new ArrayList<>();

        appList.add(new GoodsBean(R.mipmap.jiangshi, ConstUtils.CLEAR_FANS));
        appList.add(new GoodsBean(R.mipmap.video, ConstUtils.VIDEO_SHARE));
        appList.add(new GoodsBean(R.mipmap.resource_share, ConstUtils.RESOURCE_SHARE));
        appList.add(new GoodsBean(R.mipmap.agent, ConstUtils.AGENT));
        appList.add(new GoodsBean(R.mipmap.online_service, ConstUtils.ONLINE_SERVICE));
        appList.add(new GoodsBean(R.mipmap.watermark, ConstUtils.WATERMARK));


        GoodsAdapter appAdapter = new GoodsAdapter(appList);
        appRv.setAdapter(appAdapter);

        appAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (appList.get(position).getName()) {
                    case ConstUtils.CLEAR_FANS:
                        readyGo(CommonH5Activity.class);
                        break;
                    case ConstUtils.VIDEO_SHARE:
                        readyGo(CommonH5Activity.class);
                        break;
                    case ConstUtils.RESOURCE_SHARE:
                    case ConstUtils.AGENT:
                        readyGo(CommonH5Activity.class);
                        break;
                    case ConstUtils.ONLINE_SERVICE:
                        readyGo(CommonH5Activity.class);
                        break;
                    case ConstUtils.WATERMARK:
                        readyGo(CommonH5Activity.class);
                        break;
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

}
