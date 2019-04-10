package com.android.hyb.net.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ErrorException handleException(Throwable e) {
        ErrorException ex;
        if (e instanceof HttpException) {// HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ErrorException(e, NetError.HTTP_ERROR);
            ex.code = httpException.code();
            switch (httpException.code()) {
                case BAD_GATEWAY:
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.msg = NetError.MSG_HTTP_ERROR;
                    break;
            }
            return ex;
        } else if (e instanceof BussinessError) {// 服务器返回的错误
            BussinessError bussinessError = (BussinessError) e;
            ex = new ErrorException(bussinessError, NetError.SERVICE_ERROR);
            if (bussinessError.state == 1) {
                ex.code = Integer.valueOf(bussinessError.state + String.valueOf(bussinessError.code));
            } else {
                ex.code = bussinessError.code;
            }
            ex.msg = bussinessError.msg;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {// 解析错误
            ex = new ErrorException(e, NetError.PARSE_ERROR);
            ex.code = NetError.PARSE_ERROR;
            ex.msg = NetError.MSG_PARSE_ERROR;
            return ex;
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof UnknownHostException) {// 网络链接错误
            ex = new ErrorException(e, NetError.NETWORD_ERROR);
            ex.code = NetError.NETWORD_ERROR;
            ex.msg = NetError.MSG_NETWORD_ERROR;
            return ex;
        } else {// 未知错误
            ex = new ErrorException(e, NetError.UNKNOWN);
            ex.code = NetError.UNKNOWN;
            ex.msg = NetError.MSG_UNKNOWN;
            return ex;
        }
    }

}
