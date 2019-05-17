package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

import java.util.List;

public class WalletRecordResponse extends BaseResponse {
    private List<WalletRecordBean> data;
    public List<WalletRecordBean> getData() {
        return data;
    }
    public void setData(List<WalletRecordBean> data) {
        this.data = data;
    }

    public static class WalletRecordBean {

        /**
         * Id : 5
         * OpenId :
         * Type : 10
         * Funds : 9
         * PreFunds : 13
         * Msg : 你的团队新增了一名成员，获得了9.00元。
         * CreatedTime : /Date(1557912137830)/
         * OrderNumber : 201905151721040793
         * CreatedTimeString : 2019-05-15 17:22:17
         */

        private int Id;
        private String OpenId;
        private int Type;
        private int Funds;
        private int PreFunds;
        private String Msg;
        private String CreatedTime;
        private String OrderNumber;
        private String CreatedTimeString;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getOpenId() {
            return OpenId;
        }

        public void setOpenId(String OpenId) {
            this.OpenId = OpenId;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getFunds() {
            return Funds;
        }

        public void setFunds(int Funds) {
            this.Funds = Funds;
        }

        public int getPreFunds() {
            return PreFunds;
        }

        public void setPreFunds(int PreFunds) {
            this.PreFunds = PreFunds;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public String getOrderNumber() {
            return OrderNumber;
        }

        public void setOrderNumber(String OrderNumber) {
            this.OrderNumber = OrderNumber;
        }

        public String getCreatedTimeString() {
            return CreatedTimeString;
        }

        public void setCreatedTimeString(String CreatedTimeString) {
            this.CreatedTimeString = CreatedTimeString;
        }
    }
}
