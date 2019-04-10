package com.android.hyb.base;

import android.support.annotation.Keep;

@Keep
public class BaseResponse<T> {

    public static final String STATUS_SUCCESS = "success";

    public String status;
    public String message;
    public T data;

}
