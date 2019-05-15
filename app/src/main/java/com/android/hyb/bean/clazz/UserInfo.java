package com.android.hyb.bean.clazz;

import com.android.hyb.BuildConfig;
import com.android.hyb.util.SPUtils;

public class UserInfo {

    private static final String TOKEN="TOKEN";  //token

    private static final String Id = "Id";  //编号

    private static final String NickName = "NickName";  //昵称

    public static final String AvatarUrl = "AvatarUrl";  //头像链接

    private static final String Role = "Role";  //身份，充值VIP后为”vip”，申请入驻为”business”

    private static final String AccessTime = "AccessTime";  //登录时间

    private static final String LastAccessTime = "LastAccessTime";  //上次登录时间

    private static final String AccessNum = "AccessNum";  //登录次数

    private static final String Mobile = "Mobile";  //手机号

    private static final String AvailableFunds = "AvailableFunds";  //我的余额

    private static final String FrozenFunds = "FrozenFunds";  //冻结资金

    private static final String OpenId = "OpenId";
    private static final String SessionKey = "SessionKey";
    private static final String Gender = "Gender";
    private static final String Remarks = "Remarks";
    private static final String Number = "Number";
    private static final String ParentId = "ParentId";
    private static final String InvitationCode = "InvitationCode";
    private static final String Withdraw = "Withdraw";
    private static final String Earnings = "Earnings";
    private static final String TodayEarnings = "TodayEarnings";

    public static void setId(int id) {
        SPUtils.getInstance().put(Id, id);
    }

    public static int getId() {
        return SPUtils.getInstance().getInt(Id, 0);
    }

    public static void setToken(String token){
        SPUtils.getInstance().put(TOKEN,token);
    }

    public static String getToken(){
        return SPUtils.getInstance().getString(TOKEN);
    }

    public static void setNickName(String nickName) {
        SPUtils.getInstance().put(NickName, nickName);
    }

    public static String getNickName() {
        return SPUtils.getInstance().getString(NickName, "");
    }

    public static void setAvatarUrl(String avatarUrl) {
        SPUtils.getInstance().put(AvatarUrl, avatarUrl);
    }

    public static String getAvatarUrl() {
        return SPUtils.getInstance().getString(AvatarUrl, "");
    }

    public static String getTotalAvatarUrl() {
        return BuildConfig.serverUrl + "/Yinliubao/images" + getAvatarUrl();
    }

    public static void setRole(String role) {
        SPUtils.getInstance().put(Role, role);
    }

    public static String getRole() {
        return SPUtils.getInstance().getString(Role, "");
    }

    public static void setAccessTime(String accessTime) {
        SPUtils.getInstance().put(AccessTime, accessTime);
    }

    public static String getAccessTime() {
        return SPUtils.getInstance().getString(AccessTime, "");
    }

    public static void setLastAccessTime(String lastAccessTime) {
        SPUtils.getInstance().put(LastAccessTime, lastAccessTime);
    }

    public static String getLastAccessTime() {
        return SPUtils.getInstance().getString(LastAccessTime, "");
    }

    public static void setAccessNum(int accessNum) {
        SPUtils.getInstance().put(AccessNum, accessNum);
    }

    public static int getAccessNum() {
        return SPUtils.getInstance().getInt(AccessNum, 0);
    }

    public static void setMobile(String mobile) {
        SPUtils.getInstance().put(Mobile, mobile);
    }

    public static String getMobile() {
        return SPUtils.getInstance().getString(Mobile);
    }

    public static void setAvailableFunds(float availableFunds) {
        SPUtils.getInstance().put(AvailableFunds, availableFunds);
    }

    public static float getAvailableFunds() {
        return SPUtils.getInstance().getFloat(AvailableFunds);
    }

    public static void setFrozenFunds(float frozenFunds) {
        SPUtils.getInstance().put(FrozenFunds, frozenFunds);
    }

    public static float getFrozenFunds() {
        return SPUtils.getInstance().getFloat(FrozenFunds);
    }

    public static void setOpenId(String openId) {
        SPUtils.getInstance().put(OpenId, openId);
    }

    public static String getOpenId() {
        return SPUtils.getInstance().getString(OpenId, "");
    }

    public static void setSessionKey(String sessionKey) {
        SPUtils.getInstance().put(SessionKey, sessionKey);
    }

    public static String getSessionKey() {
        return SPUtils.getInstance().getString(SessionKey, "");
    }

    public static void setRemarks(String remarks) {
        SPUtils.getInstance().put(Remarks, remarks);
    }

    public static String getRemarks() {
        return SPUtils.getInstance().getString(Remarks, "");
    }

    public static void setNumber(String number) {
        SPUtils.getInstance().put(Number, number);
    }

    public static String getNumber() {
        return SPUtils.getInstance().getString(Number, "");
    }

    public static void setInvitationCode(String invitationCode) {
        SPUtils.getInstance().put(InvitationCode, invitationCode);
    }

    public static String getInvitationCode() {
        return SPUtils.getInstance().getString(InvitationCode, "");
    }

    public static void setGender(float gender) {
        SPUtils.getInstance().put(Gender, gender);
    }

    public static float getGender() {
        return SPUtils.getInstance().getFloat(Gender);
    }

    public static void setParentId(float parentId) {
        SPUtils.getInstance().put(ParentId, parentId);
    }

    public static float getParentId() {
        return SPUtils.getInstance().getFloat(ParentId);
    }

    public static void setWithdraw(float withdraw) {
        SPUtils.getInstance().put(Withdraw, withdraw);
    }

    public static float getWithdraw() {
        return SPUtils.getInstance().getFloat(Withdraw);
    }


    public static void setEarnings(float earnings) {
        SPUtils.getInstance().put(Earnings, earnings);
    }

    public static float getEarnings() {
        return SPUtils.getInstance().getFloat(Earnings);
    }


    public static void setTodayEarnings(float todayEarnings) {
        SPUtils.getInstance().put(TodayEarnings, todayEarnings);
    }

    public static float getTodayEarnings() {
        return SPUtils.getInstance().getFloat(TodayEarnings);
    }

}
