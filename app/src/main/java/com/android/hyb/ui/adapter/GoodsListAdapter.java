package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.bean.response.GoodsResponse.GoodsBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsListAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    public GoodsListAdapter(@Nullable List<GoodsBean> data) {
        super(R.layout.item_goods_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .into((ImageView) helper.getView(R.id.content_iv));

        helper.setText(R.id.name_tv, item.getName());
        helper.setText(R.id.price_tv, "￥" + item.getPresentPrice());
        helper.setText(R.id.sales_tv, item.getSales() + "人付款");

        helper.addOnClickListener(R.id.item_cardview);
        helper.addOnClickListener(R.id.buy_tv);
    }
}
