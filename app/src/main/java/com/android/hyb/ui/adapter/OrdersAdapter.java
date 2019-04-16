package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class OrdersAdapter extends BaseQuickAdapter<GetOrderListResponse.OrderListBean, BaseViewHolder> {

    public OrdersAdapter(@Nullable List<GetOrderListResponse.OrderListBean> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderListResponse.OrderListBean item) {
        helper.setText(R.id.tv_order_number,"订单号：" + item.getOrderNumber());

        String status = "";
        switch (item.getStatus()){
            case 10:
                status = "待付款";
                break;
            case 20:
                status = "待发货";
                break;
            case 30:
                status = "待收货";
                break;
            case 40:
                status = "已完成";
                break;
        }

        helper.setText(R.id.tv_order_status,"订单状态：" + status);
        helper.setText(R.id.tv_goods_name,item.getGoodsName());
        helper.setText(R.id.tv_price,"金额："+ item.getActualPay());
        GlideApp.with(mContext).load(item.getPictureUrl()).into((ImageView) helper.getView(R.id.image_goods));
    }
}
