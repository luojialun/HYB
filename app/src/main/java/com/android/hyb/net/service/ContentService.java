package com.android.hyb.net.service;

import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.BannerResponse;
import com.android.hyb.bean.response.GetApplyForBusinessResponse;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.bean.response.GetPlatformInfoResponse;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.android.hyb.bean.response.GoodsDetailsResponse;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.bean.response.PlaceNewOrderResponse;
import com.android.hyb.bean.response.RegisterResponse;
import com.android.hyb.bean.response.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    /**
     * 获取轮播图列表
     *
     * @return
     */
    @GET("Yinliubao/Slider/GetList")
    Observable<BannerResponse> getBannerList();

    /**
     * 获取商品分类列表
     *
     * @return
     */
    @GET("Yinliubao/GoodsCategory/GetList")
    Observable<GoodsCategoryResponse> getGoodsCategoryList();

    /**
     * 分页获取商品列表
     *
     * @param categoryId
     * @param pageIndex
     * @param pageSize
     * @param orderItem
     * @param isDesc
     * @return
     */
    @GET("Yinliubao/Goods/GetNewList")
    Observable<GoodsResponse> getGoodsList(@Query("categoryId") int categoryId, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize, @Query("orderItem") String orderItem, @Query("isDesc") boolean isDesc);

    /**
     * 获取商品详细信息
     *
     * @param id
     * @return
     */
    @GET("Yinliubao/Goods/GetNew")
    Observable<GoodsDetailsResponse> getGoodsDetails(@Query("id") int id);

    /**
     * 获取用户信息
     */
    @GET("Yinliubao/User/Get")
    Observable<UserResponse> getUserAgent(@Query("token") String token);

    /**
     * 申请成为商户
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/ApplyForBusiness")
    Observable<ApplyForBusinessResponse> applyForBusiness(@Field("token") String token);

    /**
     * 获取申请成为商户审核信息
     */
    @GET("Yinliubao/User/GetApplyForBusiness")
    Observable<GetApplyForBusinessResponse> GetApplyForBusiness(@Query("token") String token);

    /**
     * 用户下单
     */
    @FormUrlEncoded
    @POST("Yinliubao/Order/PlaceNewOrder")
    Observable<PlaceNewOrderResponse> placeNewOrder(@Field("token") String token, @Field("goodsId") int goodsId);

    /**
     * 分页获取订单列表
     */
    @GET("Yinliubao/Order/GetList")
    Observable<GetOrderListResponse> GetOrderList(@Query("token") String token, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize, @Query("orderStatus") int orderStatus);

    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST("Yinliubao/Order/CancelOrder")
    Observable<ApplyForBusinessResponse> CancelOrder(@Field("token") String token, @Field("id") int id);

    /**
     * 确认收货订单
     */
    @FormUrlEncoded
    @POST("Yinliubao/Order/ConfirmOrder")
    Observable<ApplyForBusinessResponse> ConfirmOrder(@Field("token") String token, @Field("id") int id);

    /**
     * 分页获取订单列表
     */
    @GET("Yinliubao/Business/Get")
    Observable<GetPlatformInfoResponse> GetPlatformInifo();

}
