package com.android.hyb.net.service;

import com.android.hyb.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ContentService {

    /**
     * 注册
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/Register")
    Observable<BaseResponse<String>> register(@Field("mobile") String mobile, @Field("password") String password);

    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/Login")
    Observable<BaseResponse<String>> login(@Field("mobile") String mobile, @Field("password") String password);


}
