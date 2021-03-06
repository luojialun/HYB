package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.bean.response.GoodsResponse;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TabContentAdapter extends BaseQuickAdapter<GoodsResponse.GoodsBean, BaseViewHolder> {
    public TabContentAdapter(@Nullable List<GoodsResponse.GoodsBean> data) {
        super(R.layout.item_best_sallers_tab, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsResponse.GoodsBean item) {

        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.icon_iv));
        helper.setText(R.id.name_tv, item.getName());
        helper.setText(R.id.money_tv,"￥" + item.getPresentPrice());
        helper.setText(R.id.sales_tv,item.getSales() + "人付款");
        helper.setText(R.id.back_money_tv,"返最高" +  item.getCommission() + "元佣金");
//        helper.setText(R.id.title_tv, item.getName());
//        helper.setText(R.id.current_price_tv, "￥" + item.getPresentPrice());
//        helper.setText(R.id.sellers_tv, "销量：" + item.getSales());
//        SpannableString msp = new SpannableString("￥" + item.getOriginalPrice());
//        msp.setSpan(new StrikethroughSpan(), 0, msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        helper.setText(R.id.old_price_tv, msp);
//        helper.setText(R.id.back_money_tv,"返最高" +  String.format("%.2f", item.getPresentPrice()*0.2) + "元佣金");
    }
}
