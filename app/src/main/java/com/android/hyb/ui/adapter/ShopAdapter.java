package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.bean.response.BusinessGoodsResponse;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<BusinessGoodsResponse.BusinessGoodsBean, BaseViewHolder> {
    public ShopAdapter(@Nullable List<BusinessGoodsResponse.BusinessGoodsBean> data) {
        super(R.layout.item_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessGoodsResponse.BusinessGoodsBean item) {
        Glide.with(this.mContext)
                .load(item.getUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into((ImageView) helper.getView(R.id.goods_iv));
        helper.setText(R.id.name_tv,item.getName());
        helper.setText(R.id.detail_tv,item.getDetails());
        helper.getView(R.id.up_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        helper.getView(R.id.down_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
