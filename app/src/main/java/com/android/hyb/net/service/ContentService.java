package com.android.hyb.net.service;

import com.android.hyb.bean.response.ApplyForBusinessResponse;
import com.android.hyb.bean.response.ApplyForVipResponse;
import com.android.hyb.bean.response.BannerResponse;
import com.android.hyb.bean.response.BusinessGoodsResponse;
import com.android.hyb.bean.response.BusinessSingleGoodBean;
import com.android.hyb.bean.response.EmptyResponse;
import com.android.hyb.bean.response.GetApplyForBusinessResponse;
import com.android.hyb.bean.response.GetCountsResponse;
import com.android.hyb.bean.response.GetListInSearchResponse;
import com.android.hyb.bean.response.GetOrderListResponse;
import com.android.hyb.bean.response.GetPageGroupsResponse;
import com.android.hyb.bean.response.GetPlatformInfoResponse;
import com.android.hyb.bean.response.GoodsCategoryResponse;
import com.android.hyb.bean.response.GoodsDetailsResponse;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.bean.response.NewHotSellingGoodsResponse;
import com.android.hyb.bean.response.PlaceNewOrderResponse;
import com.android.hyb.bean.response.RegisterResponse;
import com.android.hyb.bean.response.UserResponse;
import com.android.hyb.bean.response.WalletRecordResponse;
import com.android.hyb.bean.response.GetGroupResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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
    Observable<RegisterResponse> register(@Field("mobile") String mobile, @Field("password") String password, @Field("code") String code, @Field("invitationCode") String invitationCode);

    /**
     * 获取短信验证码
     *
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/Sms/Send")
    Observable<RegisterResponse> sendSMS(@Field("mobile") String mobile);

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
    Observable<ApplyForVipResponse> placeNewOrder(@Field("token") String token, @Field("goodsId") int goodsId);

    /**
     * 用户继续支付
     */
    /**
     * 分页获取订单列表
     */
    @GET("Yinliubao/Order/PayOrder")
    Observable<ApplyForVipResponse> PayOrder(@Query("token") String token, @Query("id") int orderId);

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

    /**
     * 获取热卖商品列表
     *
     * @return
     */
    @GET("Yinliubao/Goods/GetNewHotSellingGoods")
    Observable<NewHotSellingGoodsResponse> getNewHotSellingGoods();

    /**
     * 根据搜索名称分页获取商品列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/Goods/GetListInSearch")
    Observable<GetListInSearchResponse> getListInSearch(@Field("goodsName") String goodsName, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize, @Field("orderItem") String orderItem, @Field("isDesc") boolean isDesc);

    /**
     * 用户申请成为VIP
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/VipOrder/ApplyForVip")
    Observable<ApplyForVipResponse> ApplyForVip(@Field("token") String token);

    /**
     * 我的团队
     *
     * @param token
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("Yinliubao/User/GetPageGroups")
    Observable<GetPageGroupsResponse> getPageGroups(@Query("token") String token, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);


    /**
     * 分润明细
     *
     * @param token
     * @param pageIndex
     * @param pageSize
     * @param type 10 会员 20商场 30提现
     * @return
     */
    @GET("Yinliubao/User/GetPageWalletRecord")
    Observable<WalletRecordResponse> getPageWalletRecord(@Query("token") String token, @Query("type") int type, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    /**
     * 商品
     *
     * @param token
     * @param pageIndex
     * @param pageSize
     *
     * @return
     */
    @GET("Yinliubao/Goods/GetBusinessGoodsList")
    Observable<BusinessGoodsResponse> getBusinessGoodsList(@Query("token") String token, @Query("categoryId") int categoryId, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize, @Query("orderItem") String orderItem, @Query("isDesc") Boolean isDesc);

    /**
     * 商品
     *
     * @param token
     *
     * @return
     */
    @GET("Yinliubao/Goods/GetBusinessGoods")
    Observable<BusinessSingleGoodBean> getBusinessGoodsById(@Query("token") String token,@Query("id") int id );


    /**
     * GetGroup
     *
     * @param token
     * @return
     */
    @GET("Yinliubao/User/GetGroup")
    Observable<GetGroupResponse> getGroup(@Query("token") String token);

    /**
     * Publish
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/Goods/Publish")
    Observable<ApplyForBusinessResponse> publish(@Field("token") String token,@Field("id") int id);

    /**
     * UnPublish
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/Goods/UnPublish")
    Observable<ApplyForBusinessResponse> UnPublish(@Field("token") String token,@Field("id") int id);

    @POST("Yinliubao/image/Upload")
    Observable<ApplyForBusinessResponse> uploadImage(@Body() RequestBody file);

    /**
     * Yinliubao/Goods/Upload
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/Goods/Upload")
    Observable<ApplyForBusinessResponse> UploadGoods(@Field("token") String token,@Field("id") int id,@Field("categoryId") int categorId,@Field("name") String name,@Field("details") String detail,@Field("url") String url,@Field("price") double price);


    /**
     * GetGroup
     *
     * @param token
     * @return
     */
    @GET("Yinliubao/Order/GetCounts")
    Observable<GetCountsResponse> getCounts(@Query("token") String token);

    /**
     * UploadAlipayUrl
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/UploadAlipayUrl")
    Observable<ApplyForBusinessResponse> UploadAlipayUrl(@Field("token") String token,@Field("url") String url);


    /**
     * Withdraw
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Yinliubao/User/Withdraw")
    Observable<ApplyForBusinessResponse> Withdraw(@Field("token") String token,@Field("funds") float funds);

    /**
     * AppVersion/Get
     *
     * @param token
     * @return
     */
    @GET("Yinliubao/AppVersion/Get")
    Observable<String> AppVersionGet();


//
}
