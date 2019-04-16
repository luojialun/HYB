package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.android.hyb.R;
import java.util.List;

public class OrdersAdapter extends BaseQuickAdapter<GetOrderListResponse.OrderListBean, BaseViewHolder>{

    public OrdersAdapter(@Nullable List<GetOrderListResponse.OrderListBean> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderListResponse.OrderListBean item) {

    }
}
