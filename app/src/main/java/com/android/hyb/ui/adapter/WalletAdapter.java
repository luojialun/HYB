package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;

import com.android.hyb.R;
import com.android.hyb.bean.response.GetPageGroupsResponse;
import com.android.hyb.bean.response.WalletRecordResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class WalletAdapter extends BaseQuickAdapter<WalletRecordResponse.WalletRecordBean, BaseViewHolder> {
    public WalletAdapter(@Nullable List<WalletRecordResponse.WalletRecordBean> data) {
        super(R.layout.item_wallet,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletRecordResponse.WalletRecordBean item) {
        if (item.getType() == 10){
            helper.setText(R.id.title_tv,"会员充值");
        } else if(item.getType() == 20) {
            helper.setText(R.id.title_tv,"商城购物");
        } else if(item.getType() == 30) {
            helper.setText(R.id.title_tv,"提现记录");
        }

        helper.setText(R.id.message_tv,item.getMsg());
        helper.setText(R.id.time_tv,item.getCreatedTimeString());
        helper.setText(R.id.money_tv, "¥" + item.getFunds());

    }
}
