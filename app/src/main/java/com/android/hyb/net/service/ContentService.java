package com.android.hyb.net.service;

import com.android.hyb.base.BaseResponse;
import com.android.hyb.bean.response.WanResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ContentService {

    @GET("article/listproject/0/json")
    Observable<BaseResponse<WanResponse>> getlistproject();


}
