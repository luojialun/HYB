package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
import com.android.hyb.base.BaseResponse;

import java.util.List;

public class GetOrderListResponse extends BaseResponse {
    private List<OrderListBean> data;
    public List<OrderListBean> getData() {
        return data;
    }
    public void setData(List<OrderListBean> data) {
        this.data = data;
    }

    public static class OrderListBean {

        /**
         * Id : 1
         * OrderNumber : 201904151639206483
         * Status : 10
         * TotalFee : 44
         * ActualPay : 44
         * GoodsId : 1
         * GoodsName : v商神器
         * PictureUrl : /goods/1d4c444b6af24ff5867d701e73d8104b.png
         * ActivationCode :
         * OrderTimeString : 2019-04-15 16:39:20
         * PayTimeString :
         * DeliveryTimeString :
         * FinishTimeString :
         * ValidityEndTimeString : 2019-04-29 16:39:20
         */

        private int Id;
        private String OrderNumber;
        private int Status;
        private double TotalFee;
        private double ActualPay;
        private int GoodsId;
        private String GoodsName;
        private String PictureUrl;
        private String ActivationCode;
        private String OrderTimeString;
        private String PayTimeString;
        private String DeliveryTimeString;
        private String FinishTimeString;
        private String ValidityEndTimeString;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getOrderNumber() {
            return OrderNumber;
        }

        public void setOrderNumber(String OrderNumber) {
            this.OrderNumber = OrderNumber;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public double getTotalFee() {
            return TotalFee;
        }

        public void setTotalFee(double TotalFee) {
            this.TotalFee = TotalFee;
        }

        public double getActualPay() {
            return ActualPay;
        }

        public void setActualPay(double ActualPay) {
            this.ActualPay = ActualPay;
        }

        public int getGoodsId() {
            return GoodsId;
        }

        public void setGoodsId(int GoodsId) {
            this.GoodsId = GoodsId;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String GoodsName) {
            this.GoodsName = GoodsName;
        }

        public String getPictureUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+PictureUrl;
        }

        public void setPictureUrl(String PictureUrl) {
            this.PictureUrl = PictureUrl;
        }

        public String getActivationCode() {
            return ActivationCode;
        }

        public void setActivationCode(String ActivationCode) {
            this.ActivationCode = ActivationCode;
        }

        public String getOrderTimeString() {
            return OrderTimeString;
        }

        public void setOrderTimeString(String OrderTimeString) {
            this.OrderTimeString = OrderTimeString;
        }

        public String getPayTimeString() {
            return PayTimeString;
        }

        public void setPayTimeString(String PayTimeString) {
            this.PayTimeString = PayTimeString;
        }

        public String getDeliveryTimeString() {
            return DeliveryTimeString;
        }

        public void setDeliveryTimeString(String DeliveryTimeString) {
            this.DeliveryTimeString = DeliveryTimeString;
        }

        public String getFinishTimeString() {
            return FinishTimeString;
        }

        public void setFinishTimeString(String FinishTimeString) {
            this.FinishTimeString = FinishTimeString;
        }

        public String getValidityEndTimeString() {
            return ValidityEndTimeString;
        }

        public void setValidityEndTimeString(String ValidityEndTimeString) {
            this.ValidityEndTimeString = ValidityEndTimeString;
        }
    }
}
