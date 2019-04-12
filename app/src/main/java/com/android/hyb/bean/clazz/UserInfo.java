package com.android.hyb.bean.clazz;

import com.android.hyb.BuildConfig;
import com.android.hyb.util.SPUtils;

public class UserInfo {

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

    public static void setId(int id) {
        SPUtils.getInstance().put(Id, id);
    }

    public static int getId() {
        return SPUtils.getInstance().getInt(Id, 0);
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

}
