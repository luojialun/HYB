package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

public class PlaceNewOrderResponse extends BaseResponse {
    private PlaceNewOrderBean data;

    public PlaceNewOrderBean getData() {
        return data;
    }

    public void setData(PlaceNewOrderBean data) {
        this.data = data;
    }

    public static class PlaceNewOrderBean {


        /**
         * Status : success
         * Message :
         * OrderId : 1
         */

        private String Status;
        private String Message;
        private int OrderId;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
        }
    }
}
