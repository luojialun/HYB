package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsCategoryResponse.GoodsCategoryBean, BaseViewHolder> {
    public GoodsAdapter(@Nullable List<GoodsCategoryResponse.GoodsCategoryBean> data) {
        super(R.layout.item_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsCategoryResponse.GoodsCategoryBean item) {
        helper.setText(R.id.name_tv, item.getName());
        GlideApp.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.icon_iv));

    }
}
