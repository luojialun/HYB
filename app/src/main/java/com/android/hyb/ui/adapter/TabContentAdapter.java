package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.response.GoodsResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TabContentAdapter extends BaseQuickAdapter<GoodsResponse.GoodsBean, BaseViewHolder> {
    public TabContentAdapter(@Nullable List<GoodsResponse.GoodsBean> data) {
        super(R.layout.item_tab_content, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsResponse.GoodsBean item) {
        GlideApp.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.icon_iv));
        helper.setText(R.id.title_tv, item.getName());
        helper.setText(R.id.current_price_tv, "￥" + item.getPresentPrice());
        helper.setText(R.id.sellers_tv, "销量：" + item.getSales());
        SpannableString msp = new SpannableString("￥" + item.getOriginalPrice());
        msp.setSpan(new StrikethroughSpan(), 0, msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.old_price_tv, msp);
    }
}
