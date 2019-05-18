package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
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
        private String Url;
        private String OrderTimeString;
        private int Minutes;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public String getUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public String getOrderTimeString() {
            return OrderTimeString;
        }

        public void setOrderTimeString(String orderTimeString) {
            OrderTimeString = orderTimeString;
        }

        public int getMinutes() {
            return Minutes;
        }

        public void setMinutes(int minutes) {
            Minutes = minutes;
        }
    }
}
