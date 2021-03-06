package com.android.hyb.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.BusinessGoodsResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.UploadActivity;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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
                .into((ImageView) helper.getView(R.id.goods_iv));
        helper.setText(R.id.name_tv,item.getName());
        helper.setText(R.id.price_tv,"价格：" + item.getPresentPrice() + "¥");
        helper.setText(R.id.sale_tv,"销量：" + item.getSales() + "份");
        helper.setText(R.id.status_tv,item.isIsPublish()?"已上架":"已下架");
        if (item.isIsPublish()){
            TextView textView = helper.getView(R.id.up_or_down_tv);
            textView.setText("下架");
            textView.setBackgroundResource(R.drawable.rectangle_red_5radius);
        }
        else
        {
            TextView textView = helper.getView(R.id.up_or_down_tv);
            textView.setText("上架");
            textView.setBackgroundResource(R.drawable.rectangle_green_5radius);
        }

        helper.getView(R.id.up_or_down_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);

                if (item.isIsPublish()){
                    ServiceFactory.createHYBService(ContentService.class)
                            .UnPublish(token,item.getId())
                            .compose(new RemoteTransformer<ApplyForBusinessResponse>())
                            .subscribe(new ToastObserver<ApplyForBusinessResponse>(that.mContext) {
                                @Override
                                public void onNext(ApplyForBusinessResponse response) {
                                    ToastUtils.show(that.mContext,"下架成功");
                                    item.setIsPublish(false);
                                    helper.setText(R.id.status_tv,item.isIsPublish()?"已上架":"已下架");
                                    TextView textView = helper.getView(R.id.up_or_down_tv);
                                    textView.setText("上架");
                                    textView.setBackgroundResource(R.drawable.rectangle_green_5radius);
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                    ToastUtils.show(that.mContext,"下架失败");
                                }
                            });
                }
                else
                {
                    ServiceFactory.createHYBService(ContentService.class)
                            .publish(token,item.getId())
                            .compose(new RemoteTransformer<ApplyForBusinessResponse>())
                            .subscribe(new ToastObserver<ApplyForBusinessResponse>(that.mContext) {
                                @Override
                                public void onNext(ApplyForBusinessResponse response) {
                                    ToastUtils.show(that.mContext,"上架成功");
                                    item.setIsPublish(true);
                                    helper.setText(R.id.status_tv,item.isIsPublish()?"已上架":"已下架");
                                    TextView textView = helper.getView(R.id.up_or_down_tv);
                                    textView.setText("下架");
                                    textView.setBackgroundResource(R.drawable.rectangle_red_5radius);
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                    ToastUtils.show(that.mContext,"上架失败");
                                }
                            });
                }
            }
        });


        helper.getView(R.id.update_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(that.mContext, UploadActivity.class);
                intent.putExtra(ConstUtils.ID, item.getId());
                that.mContext.startActivity(intent);
            }
        });
    }
}
