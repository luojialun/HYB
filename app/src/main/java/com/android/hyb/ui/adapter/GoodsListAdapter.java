package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.response.GoodsResponse.GoodsBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsListAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    public GoodsListAdapter(@Nullable List<GoodsBean> data) {
        super(R.layout.item_goods_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        GlideApp.with(mContext)
                .load(item.getUrl())
                .into((ImageView) helper.getView(R.id.content_iv));

        helper.addOnClickListener(R.id.item_cardview);
        helper.addOnClickListener(R.id.buy_tv);
    }
}
