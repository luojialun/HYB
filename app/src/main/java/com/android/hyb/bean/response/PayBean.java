package com.android.hyb.bean.response;

import com.google.gson.annotations.SerializedName;

public class PayBean {

    /**
     * appId : wx678f048066c038e4
     * nonceStr : fef8e2f88f7345c1beac27ba970f1d78
     * package : prepay_id=wx03104103441355e6210e35fb1753718200
     * paySign : 2FE534FFCE469857D50E414DAFB604D0
     * signType : MD5
     * timeStamp : 1562121664
     */

    private String appId;
    private String nonceStr;
    @SerializedName("package")
    private String packageX;
    private String paySign;
    private String signType;
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
