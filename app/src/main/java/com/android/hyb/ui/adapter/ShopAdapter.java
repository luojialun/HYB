package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.BannerResponse;
import com.android.hyb.bean.response.BusinessGoodsResponse;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.android.hyb.widget.GlideImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.mm.opensdk.utils.Log;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<BusinessGoodsResponse.BusinessGoodsBean, BaseViewHolder> {

    private ShopAdapter that;

    public ShopAdapter(@Nullable List<BusinessGoodsResponse.BusinessGoodsBean> data) {
        super(R.layout.item_shop, data);
        that = this;
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessGoodsResponse.BusinessGoodsBean item) {
        Glide.with(this.mContext)
                .load(item.getUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into((ImageView) helper.getView(R.id.goods_iv));
        helper.setText(R.id.name_tv,item.getName());
        helper.setText(R.id.detail_tv,item.getDetails());
        helper.setText(R.id.status_tv,item.isIsPublish()?"已上架":"已下架");
        helper.getView(R.id.up_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
                ServiceFactory.createHYBService(ContentService.class)
                        .publish(token,item.getId())
                        .compose(new RemoteTransformer<ApplyForBusinessResponse>())
                        .subscribe(new ToastObserver<ApplyForBusinessResponse>(that.mContext) {
                            @Override
                            public void onNext(ApplyForBusinessResponse response) {
                                ToastUtils.show(that.mContext,"上架成功");
                                item.setIsPublish(true);
                                helper.setText(R.id.status_tv,item.isIsPublish()?"已上架":"已下架");
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                ToastUtils.show(that.mContext,"上架失败");
                            }
                        });
            }
        });

        helper.getView(R.id.down_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
                ServiceFactory.createHYBService(ContentService.class)
                        .UnPublish(token,item.getId())
                        .compose(new RemoteTransformer<ApplyForBusinessResponse>())
                        .subscribe(new ToastObserver<ApplyForBusinessResponse>(that.mContext) {
                            @Override
                            public void onNext(ApplyForBusinessResponse response) {
                                ToastUtils.show(that.mContext,"下架成功");
                                item.setIsPublish(false);
                                helper.setText(R.id.status_tv,item.isIsPublish()?"已上架":"已下架");
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                ToastUtils.show(that.mContext,"下架失败");
                            }
                        });
            }
        });
    }
}
