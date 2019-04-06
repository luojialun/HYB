package com.android.hyb.base;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class BaseResponse<T extends BaseResponse.ErrorResponse> {

    public static final int CODE_SUCCESS = 0;

    @SerializedName("data")
    public T data;

    public static class ErrorResponse {
        public String erroeMsg;
        public Integer errorCode = CODE_SUCCESS;
    }


}
