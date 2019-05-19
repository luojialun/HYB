package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

public class UserResponse extends BaseResponse {

    public UserBean data;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }

    public static class UserBean {

        /**
         * Id : 4
         * OpenId :
         * SessionKey :
         * Token :
         * NickName : 18815288294
         * Gender : 0
         * Remarks :
         * AvatarUrl :
         * Role :
         * AccessTime : /Date(1555054149527)/
         * AccessNum : 2
         * LastAccessTime : /Date(1555054156490)/
         * Mobile : 18815288294
         * Number : 1904120001
         * ParentId : 0
         * InvitationCode : HaHb3
         * AvailableFunds : 0
         * Withdraw : 0
         * Earnings : 0
         * TodayEarnings : 0
         * FrozenFunds : 0
         */

        private int Id;
        private String OpenId;
        private String SessionKey;
        private String Token;
        private String NickName;
        private int Gender;
        private String Remarks;
        private String AvatarUrl;
        private String Role;
        private String AccessTime;
        private int AccessNum;
        private String LastAccessTime;
        private String Mobile;
        private String Number;
        private int ParentId;
        private String InvitationCode;
        private float AvailableFunds;
        private float Withdraw;
        private float Earnings;
        private float TodayEarnings;
        private float FrozenFunds;
        private String ParentOpenId;
        private String AlipayUrl;
        private String WeChatUrl;

        public String getAlipayUrl() {
            return AlipayUrl;
        }

        public void setAlipayUrl(String alipayUrl) {
            AlipayUrl = alipayUrl;
        }

        public String getWeChatUrl() {
            return WeChatUrl;
        }

        public void setWeChatUrl(String weChatUrl) {
            WeChatUrl = weChatUrl;
        }

        public String getParentOpenId() {
            return ParentOpenId;
        }

        public void setParentOpenId(String parentOpenId) {
            ParentOpenId = parentOpenId;
        }

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

        public String getSessionKey() {
            return SessionKey;
        }

        public void setSessionKey(String SessionKey) {
            this.SessionKey = SessionKey;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public int getGender() {
            return Gender;
        }

        public void setGender(int Gender) {
            this.Gender = Gender;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
        }

        public String getRole() {
            return Role;
        }

        public void setRole(String Role) {
            this.Role = Role;
        }

        public String getAccessTime() {
            return AccessTime;
        }

        public void setAccessTime(String AccessTime) {
            this.AccessTime = AccessTime;
        }

        public int getAccessNum() {
            return AccessNum;
        }

        public void setAccessNum(int AccessNum) {
            this.AccessNum = AccessNum;
        }

        public String getLastAccessTime() {
            return LastAccessTime;
        }

        public void setLastAccessTime(String LastAccessTime) {
            this.LastAccessTime = LastAccessTime;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getNumber() {
            return Number;
        }

        public void setNumber(String Number) {
            this.Number = Number;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getInvitationCode() {
            return InvitationCode;
        }

        public void setInvitationCode(String InvitationCode) {
            this.InvitationCode = InvitationCode;
        }

        public float getAvailableFunds() {
            return AvailableFunds;
        }

        public void setAvailableFunds(int AvailableFunds) {
            this.AvailableFunds = AvailableFunds;
        }

        public float getWithdraw() {
            return Withdraw;
        }

        public void setWithdraw(int Withdraw) {
            this.Withdraw = Withdraw;
        }

        public float getEarnings() {
            return Earnings;
        }

        public void setEarnings(int Earnings) {
            this.Earnings = Earnings;
        }

        public float getTodayEarnings() {
            return TodayEarnings;
        }

        public void setTodayEarnings(int TodayEarnings) {
            this.TodayEarnings = TodayEarnings;
        }

        public float getFrozenFunds() {
            return FrozenFunds;
        }

        public void setFrozenFunds(int FrozenFunds) {
            this.FrozenFunds = FrozenFunds;
        }
    }
}
