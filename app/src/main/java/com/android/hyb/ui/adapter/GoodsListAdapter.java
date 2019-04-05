package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public GoodsListAdapter(@Nullable List<String> data) {
        super(R.layout.item_goods_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideApp.with(mContext)
                .load("http://img5q.duitang.com/uploads/item/201503/18/20150318215612_USNEu.jpeg")
                .into((ImageView) helper.getView(R.id.content_iv));

        helper.addOnClickListener(R.id.item_cardview);
        helper.addOnClickListener(R.id.buy_tv);
    }
}
