package com.android.hyb.net.factory;

import android.util.Log;

import com.android.hyb.BuildConfig;
import com.android.hyb.bean.clazz.AppConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static final String serverUrl = BuildConfig.serverUrl;

    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .cache(new Cache(new File(AppConfig.DEFAULT_SAVE_OK_HTTP_PATH), AppConfig.OK_HTTP_CACHE_SIZE))
            .readTimeout(AppConfig.TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(AppConfig.TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(BuildConfig.DEBUG ? new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.e("hyb-->", "" + "收到响应:" + message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY) :
                            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            )
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    return response;
                }
            });


    public static <T> T createHYBService(Class<T> serviceClazz) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(serverUrl)
                .build();
        return mRetrofit.create(serviceClazz);
    }


}
