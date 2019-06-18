package com.android.hyb.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.hyb.BuildConfig;
import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.response.BannerResponse;
import com.android.hyb.bean.response.GetPlatformInfoResponse;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.bean.response.NewHotSellingGoodsResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.GoodsDetailsActivity;
import com.android.hyb.ui.acitvity.PaymentActivity;
import com.android.hyb.ui.adapter.GoodsAdapter;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.widget.GlideImageLoader;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 首页fragment
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.goodsRv)
    RecyclerView goodsRv;
    @BindView(R.id.promotion_iv)
    ImageView promotionIv;
    @BindView(R.id.banner_background_iv)
    ImageView bannerBackgroundIv;

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

                            banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int i, float v, int i1) {

                                }

                                @Override
                                public void onPageSelected(int i) {
                                    switch (i){
                                        case 0:
                                            bannerBackgroundIv.setBackgroundColor(Color.RED);
                                            break;
                                        case 1:
                                            bannerBackgroundIv.setBackgroundColor(Color.GREEN);
                                            break;
                                        case 2:
                                            bannerBackgroundIv.setBackgroundColor(Color.GRAY);
                                            break;
                                    }
                                }

                                @Override
                                public void onPageScrollStateChanged(int i) {

                                }
                            });
                        }
                    }
                });
    }

    private void initRecyclerView() {
        ServiceFactory.createHYBService(ContentService.class).getNewHotSellingGoods()
                .compose(new RemoteTransformer<NewHotSellingGoodsResponse>())
                .subscribe(new ToastObserver<NewHotSellingGoodsResponse>(getActivity()) {
                    @Override
                    public void onNext(NewHotSellingGoodsResponse response) {
                        if (null != response && 0 < response.getData().size()) {
                            goodsRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            List<GoodsResponse.GoodsBean> goodsList = new ArrayList<>();

                            for (GoodsResponse.GoodsBean goodsCategoryBean : response.getData()) {
                                goodsList.add(goodsCategoryBean);
                            }
                            GoodsAdapter goodsAdapter = new GoodsAdapter(goodsList);
                            goodsRv.setAdapter(goodsAdapter);

                            goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                                    intent.putExtra(ConstUtils.ID, goodsList.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });


    }

    private void checkUpdate() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BuildConfig.serverUrl + "/Yinliubao/AppVersion/Get")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    final String version = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!version.equals(BuildConfig.VERSION_NAME)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("检测有新版本");
                                builder.setMessage("您是否需要更新？");
                                builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String url = BuildConfig.serverUrl + "/Yinliubao/app/zhongfa" + version + ".apk";
                                        Uri uri = Uri.parse(url);
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }
                                });
                                //    设置一个NegativeButton
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                    Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                                    }
                                });

                                builder.show();
                            }
                        }
                    });


                }
            }
        });
    }

    @Override
    public void initData() {
        checkUpdate();
//        getPromotionUrl();
    }

    private void getPromotionUrl() {
        ServiceFactory.createHYBService(ContentService.class).GetPlatformInifo()
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetPlatformInfoResponse>(this.getContext()) {
                    @Override
                    public void onNext(GetPlatformInfoResponse response) {
                        Glide.with(MainFragment.this).load(response.getData().getExtensionUrl()).into(promotionIv);
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                    }
                });
    }

    @OnClick({R.id.promotion_iv})
    public void OnClick(View view) {
        switch (view.getId()) {
//            case R.id.tip_ll:
//                MainTipPop mainTipPop = new MainTipPop(getActivity());
//                mainTipPop.showPopupWindow();
//                break;
            case R.id.promotion_iv:
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                intent.putExtra(ConstUtils.TYPE, 0);
                startActivity(intent);
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
