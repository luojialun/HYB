package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
                helper.setVisible(R.id.tv_next,true);
                helper.setText(R.id.tv_next,"取消订单");
                break;
            case 20:
                status = "待发货";
                helper.setVisible(R.id.tv_next,false);
                break;
            case 30:
                status = "待收货";
                helper.setVisible(R.id.tv_next,true);
                helper.setText(R.id.tv_next,"确认收货 ");
                break;
            case 40:
                status = "已完成";
                helper.setVisible(R.id.tv_next,false);
                break;
        }

        helper.setText(R.id.tv_order_status,"订单状态：" + status);
        helper.setText(R.id.tv_goods_name,item.getGoodsName());
        helper.setText(R.id.tv_price,"金额："+ item.getActualPay());
        GlideApp.with(mContext).load(item.getPictureUrl()).into((ImageView) helper.getView(R.id.image_goods));

        TextView tvNext = (TextView) helper.getView(R.id.tv_next);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleListener(item);
            }
        });
    }

    public void handleListener(GetOrderListResponse.OrderListBean item) {
        String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
        OrdersAdapter that = this;

        switch (item.getStatus()){
            case 10:
//              "待付款"，取消订单
                ServiceFactory.createHYBService(ContentService.class).CancelOrder(token,item.getId())
                        .compose(new RemoteTransformer<>())
                        .subscribe(new ToastObserver<ApplyForBusinessResponse>(mContext){
                            @Override
                            public void onNext(ApplyForBusinessResponse applyForBusinessResponse) {
                                if (applyForBusinessResponse.getData().equals("success")) {
                                    ToastUtils.show(mContext,"删除成功");
                                    that.getData().remove(item);
                                    that.notifyDataSetChanged();
                                }
                                else
                                {
                                    ToastUtils.show(mContext,applyForBusinessResponse.message);
                                }
                            }

                            @Override
                            public void onError(ErrorException e) {
                                super.onError(e);
                            }
                        });
                break;
            case 20:
//                        "待发货";
                break;
            case 30:
//              "待收货"，确认收货
                ServiceFactory.createHYBService(ContentService.class).ConfirmOrder(token,item.getId())
                        .compose(new RemoteTransformer<>())
                        .subscribe(new ToastObserver<ApplyForBusinessResponse>(mContext){
                            @Override
                            public void onNext(ApplyForBusinessResponse applyForBusinessResponse) {
                                if (applyForBusinessResponse.getData().equals("success")) {
                                    item.setStatus(40);
                                    that.notifyDataSetChanged();
                                }
                                else
                                {
                                    ToastUtils.show(mContext,applyForBusinessResponse.message);
                                }
                            }

                            @Override
                            public void onError(ErrorException e) {
                                super.onError(e);
                            }
                        });
                break;
            case 40:
//                        "已完成";
                break;
        }
    }
}
