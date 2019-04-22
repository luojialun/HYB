package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;

import com.android.hyb.R;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TabAdapter extends BaseQuickAdapter<GoodsCategoryResponse.GoodsCategoryBean, BaseViewHolder> {

    public TabAdapter(@Nullable List<GoodsCategoryResponse.GoodsCategoryBean> data) {
        super(R.layout.item_best_sellers, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsCategoryResponse.GoodsCategoryBean item) {
        helper.setText(R.id.name_tv, item.getName());
        if (item.isSelected()) {
            helper.setTextColor(R.id.name_tv, mContext.getResources().getColor(R.color.color_f1121b));
            helper.setBackgroundColor(R.id.name_tv, mContext.getResources().getColor(R.color.color_ecebf1));
        } else {
            helper.setTextColor(R.id.name_tv, mContext.getResources().getColor(R.color.color_4f4f4f));
            helper.setBackgroundColor(R.id.name_tv, mContext.getResources().getColor(R.color.white));
        }
    }
}
