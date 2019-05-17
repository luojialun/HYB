package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.bean.response.GoodsResponse;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsResponse.GoodsBean, BaseViewHolder> {
    public GoodsAdapter(@Nullable List<GoodsResponse.GoodsBean> data) {
        super(R.layout.item_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsResponse.GoodsBean item) {
        helper.setText(R.id.name_tv, item.getName());
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.icon_iv));

    }
}
