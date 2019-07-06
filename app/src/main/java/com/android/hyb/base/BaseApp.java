package com.android.hyb.base;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.android.hyb.bean.clazz.AppConfig;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.NotchScreenUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class BaseApp extends Application {

    private static BaseApp context;
    private static int mStatusBarHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        regToWx();
    }

    public static Context getContext() {
        return context;
    }

    public static int getStatusBarHeight() {
        if (mStatusBarHeight == 0) {
            mStatusBarHeight = NotchScreenUtils.getStatusBarHeight();
        }
        return mStatusBarHeight;
    }

    private IWXAPI api;

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, ConstUtils.WECHAT_PAY_APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(ConstUtils.WECHAT_PAY_APP_ID);
    }
}
