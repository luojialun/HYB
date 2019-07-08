package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.ApplyForVipResponse;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.bean.response.PayBean;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.PayConfirmActivity;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
                helper.setVisible(R.id.tv_pay,true);
                helper.setText(R.id.tv_next,"取消订单");
                break;
            case 20:
                status = "待发货";
                helper.setVisible(R.id.tv_pay,false);
                helper.setVisible(R.id.tv_next,false);
                break;
            case 30:
                status = "待收货";
                helper.setVisible(R.id.tv_pay,false);
                helper.setVisible(R.id.tv_next,true);
                helper.setText(R.id.tv_next,"确认收货 ");
                break;
            case 40:
                status = "已完成";
                helper.setVisible(R.id.tv_pay,false);
                helper.setVisible(R.id.tv_next,false);
                break;
        }

        helper.setText(R.id.tv_order_status,"订单状态：" + status);
        helper.setText(R.id.tv_goods_name,item.getGoodsName());
        helper.setText(R.id.tv_price,"金额：¥"+ item.getActualPay());
        Glide.with(mContext).load(item.getPictureUrl()).into((ImageView) helper.getView(R.id.image_goods));

        TextView tvNext = (TextView) helper.getView(R.id.tv_next);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleListener(item);
            }
        });

        TextView payTv = (TextView)helper.getView(R.id.tv_pay);
        payTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SPUtils.getInstance().getString(ConstUtils.TOKEN);
                ServiceFactory.createHYBService(ContentService.class).PayOrder(SPUtils.getInstance().getString(ConstUtils.TOKEN), item.getId())
                        .compose(new RemoteTransformer<>())
                        .subscribe(new ToastObserver<ApplyForVipResponse>(OrdersAdapter.this.mContext) {
                            @Override
                            public void onNext(ApplyForVipResponse response) {
                                if (null != response) {
                                    if (response.getData().getStatus().equals("success")) {

                                        Gson gson = new Gson();
                                        PayBean payBean = gson.fromJson(response.getData().getJsApiParam(), PayBean.class);

                                        IWXAPI msgApi = WXAPIFactory.createWXAPI(OrdersAdapter.this.mContext, ConstUtils.WECHAT_PAY_APP_ID);

                                        PayReq request = new PayReq();
                                        request.appId = ConstUtils.WECHAT_PAY_APP_ID;
                                        request.partnerId = payBean.getPartnerid();
                                        request.prepayId = payBean.getPrepayid();
                                        request.packageValue = payBean.getPackageX();
                                        request.nonceStr = payBean.getNoncestr();
                                        request.timeStamp = payBean.getTimestamp();
                                        request.sign = payBean.getSign();
                                        msgApi.sendReq(request);
                                    } else {
                                        ToastUtils.show(OrdersAdapter.this.mContext, response.getData().getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onError(ErrorException e) {
                                super.onError(e);
                            }
                        });
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
