package com.android.hyb.net.service;

import com.android.hyb.bean.response.BannerResponse;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.bean.response.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContentService {

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/Register")
    Observable<RegisterResponse> register(@Field("mobile") String mobile, @Field("password") String password);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/Login")
    Observable<LoginResponse> login(@Field("mobile") String mobile, @Field("password") String password);

    @GET("Yinliubao/Slider/GetList")
    Observable<BannerResponse> getBannerList();

}
