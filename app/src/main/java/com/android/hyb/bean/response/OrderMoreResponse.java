package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

import java.util.List;

public class OrderMoreResponse extends BaseResponse {
    private List<OrderMoreDataBean> data;
    public List<OrderMoreDataBean> getData() {
        return data;
    }
    public void setData(List<OrderMoreDataBean> data) {
        this.data = data;
    }

    public static class OrderMoreDataBean {

        /**
         * Id : 38
         * OrderNumber : 201907170950097496
         * OpenId : null
         * BusinessOpenId :
         * OrderTime : /Date(1563328209927)/
         * PayTime :
         * DeliveryTime :
         * FinishTime :
         * Status : 10
         * PreStatus :
         * TotalFee : 0.01
         * ActualPay : 0.01
         * GoodsId : 87
         * GoodsName : 加菲猫2.6重生版
         * PictureUrl : /goods/51f9da9602a64f10963ed92db3725359.jpg
         * IsNew : true
         * ActivationCode :
         * ValidityEndTime : /Date(1563331809927)/
         * Url :
         * Minutes : 60
         * PrepayId : wx2617343218040539d05f8cbf1747270200
         * JsApiParam : {"appid":"wx678f048066c038e4","noncestr":"72529c83f74545d5baf6565f9d981837","package":"Sign=WXPay","partnerid":"1541871121","prepayid":"wx2617343218040539d05f8cbf1747270200","sign":"800B5E9EA4AB7485A0C21962BCDD4682","timestamp":"1564133672"}
         * OrderTimeString : 2019-07-17 09:50:09
         * PayTimeString :
         * DeliveryTimeString :
         * FinishTimeString :
         * ValidityEndTimeString : 2019-07-17 10:50:09
         */

        private int Id;
        private String OrderNumber;
        private Object OpenId;
        private String BusinessOpenId;
        private String OrderTime;
        private String PayTime;
        private String DeliveryTime;
        private String FinishTime;
        private int Status;
        private String PreStatus;
        private double TotalFee;
        private double ActualPay;
        private int GoodsId;
        private String GoodsName;
        private String PictureUrl;
        private boolean IsNew;
        private String ActivationCode;
        private String ValidityEndTime;
        private String Url;
        private int Minutes;
        private String PrepayId;
        private String JsApiParam;
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

        public Object getOpenId() {
            return OpenId;
        }

        public void setOpenId(Object OpenId) {
            this.OpenId = OpenId;
        }

        public String getBusinessOpenId() {
            return BusinessOpenId;
        }

        public void setBusinessOpenId(String BusinessOpenId) {
            this.BusinessOpenId = BusinessOpenId;
        }

        public String getOrderTime() {
            return OrderTime;
        }

        public void setOrderTime(String OrderTime) {
            this.OrderTime = OrderTime;
        }

        public String getPayTime() {
            return PayTime;
        }

        public void setPayTime(String PayTime) {
            this.PayTime = PayTime;
        }

        public String getDeliveryTime() {
            return DeliveryTime;
        }

        public void setDeliveryTime(String DeliveryTime) {
            this.DeliveryTime = DeliveryTime;
        }

        public String getFinishTime() {
            return FinishTime;
        }

        public void setFinishTime(String FinishTime) {
            this.FinishTime = FinishTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getPreStatus() {
            return PreStatus;
        }

        public void setPreStatus(String PreStatus) {
            this.PreStatus = PreStatus;
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
            return PictureUrl;
        }

        public void setPictureUrl(String PictureUrl) {
            this.PictureUrl = PictureUrl;
        }

        public boolean isIsNew() {
            return IsNew;
        }

        public void setIsNew(boolean IsNew) {
            this.IsNew = IsNew;
        }

        public String getActivationCode() {
            return ActivationCode;
        }

        public void setActivationCode(String ActivationCode) {
            this.ActivationCode = ActivationCode;
        }

        public String getValidityEndTime() {
            return ValidityEndTime;
        }

        public void setValidityEndTime(String ValidityEndTime) {
            this.ValidityEndTime = ValidityEndTime;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public int getMinutes() {
            return Minutes;
        }

        public void setMinutes(int Minutes) {
            this.Minutes = Minutes;
        }

        public String getPrepayId() {
            return PrepayId;
        }

        public void setPrepayId(String PrepayId) {
            this.PrepayId = PrepayId;
        }

        public String getJsApiParam() {
            return JsApiParam;
        }

        public void setJsApiParam(String JsApiParam) {
            this.JsApiParam = JsApiParam;
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
