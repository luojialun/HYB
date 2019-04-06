package com.android.hyb.net.exception;

public class MMError {

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 0x1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 0x1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 0x1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 0x1003;

    /**
     * 服务器业务错误
     */
    public static final int SERVICE_ERROR = 0x1004;

    /**
     * 服务器业务错误
     */
    public static final int APP_ERROR = 0x1005;

    public static final String MSG_UNKNOWN = "未知错误";

    public static final String MSG_PARSE_ERROR = "数据解析错误";

    public static final String MSG_NETWORD_ERROR = "网络连接失败,请检查网络";

    public static final String MSG_HTTP_ERROR = "网络请求异常";

    public static final String MSG_SERVICE_ERROR = "服务器异常";

    public static final String MSG_APP_ERROR = "App异常";

}
