package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

public class GetApplyForBusinessResponse extends BaseResponse {

    private GetApplyForBusinessBean data;
    public GetApplyForBusinessBean getData() {
        return data;
    }
    public void setData(GetApplyForBusinessBean data) {
        this.data = data;
    }

    public static class GetApplyForBusinessBean{

        /**
         * Id : 1
         * OpenId :
         * Status : 0
         * CreatedTime : /Date(1555140953587)/
         * ApplyTime :
         * Message :
         * CreatedTimeString : 2019-04-13 15:35:53
         * ApplyTimeString :
         * StatusString : 待审核
         */

        private int Id;
        private String OpenId;
        private int Status;
        private String CreatedTime;
        private String ApplyTime;
        private String Message;
        private String CreatedTimeString;
        private String ApplyTimeString;
        private String StatusString;

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

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public String getApplyTime() {
            return ApplyTime;
        }

        public void setApplyTime(String ApplyTime) {
            this.ApplyTime = ApplyTime;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public String getCreatedTimeString() {
            return CreatedTimeString;
        }

        public void setCreatedTimeString(String CreatedTimeString) {
            this.CreatedTimeString = CreatedTimeString;
        }

        public String getApplyTimeString() {
            return ApplyTimeString;
        }

        public void setApplyTimeString(String ApplyTimeString) {
            this.ApplyTimeString = ApplyTimeString;
        }

        public String getStatusString() {
            return StatusString;
        }

        public void setStatusString(String StatusString) {
            this.StatusString = StatusString;
        }
    }
}
