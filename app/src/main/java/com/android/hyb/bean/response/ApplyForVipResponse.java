package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
import com.android.hyb.base.BaseResponse;

public class ApplyForVipResponse extends BaseResponse {

    private ApplyForVipBean data;
    public ApplyForVipBean getData() {
        return data;
    }
    public void setData(ApplyForVipBean data) {
        this.data = data;
    }


    public static class ApplyForVipBean{

        /**
         * Status : success
         * Message :
         * Url : /vip/3e97e1640bab4e8483e654e2a84de8b8.png
         * OrderTimeString : 2019-05-13 01:51:46
         * Minutes : 10
         */

        private String Status;
        private String Message;
        private String Url;
        private String OrderTimeString;
        private int Minutes;

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

        public String getUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getOrderTimeString() {
            return OrderTimeString;
        }

        public void setOrderTimeString(String OrderTimeString) {
            this.OrderTimeString = OrderTimeString;
        }

        public int getMinutes() {
            return Minutes;
        }

        public void setMinutes(int Minutes) {
            this.Minutes = Minutes;
        }
    }

}
