package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

public class LoginResponse extends BaseResponse {

    private LoginResponse.LoginBean data;
    public LoginResponse.LoginBean getData() {
        return data;
    }
    public void setData(LoginResponse.LoginBean data) {
        this.data = data;
    }

    public static class LoginBean{
        /**
         * Status : success
         * Message :
         * Token : cd29b76ba4734e919fe6bc1510cd374d
         */

        private String Status;
        private String Message;
        private String Token;



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

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }
    }
}
