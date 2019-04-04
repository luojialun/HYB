package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;

import com.android.hyb.R;
import com.android.hyb.bean.clazz.GoodsBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {
    public GoodsAdapter(@Nullable List<GoodsBean> data) {
        super(R.layout.item_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        helper.setText(R.id.name_tv, item.getName());
        helper.setImageResource(R.id.icon_iv, item.getImage());
    }
}
