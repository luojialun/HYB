package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.android.hyb.R;
import com.android.hyb.bean.response.GetPageGroupsResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TeamAdapter extends BaseQuickAdapter<GetPageGroupsResponse.MemberBean, BaseViewHolder> {
    public TeamAdapter(@Nullable List<GetPageGroupsResponse.MemberBean> data) {
        super(R.layout.item_team,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetPageGroupsResponse.MemberBean item) {
        helper.setText(R.id.sale_tv,"直营销售"+item.getDirectGroups()+"张");
        helper.setText(R.id.phone_tv,item.getOpenId());
        helper.setText(R.id.member_tv,"渠道销售"+item.getGroups()+"张");
        helper.setText(R.id.date_tv,item.getAccessTimeString());
    }
}
