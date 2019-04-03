package com.android.hyb.base;

import android.app.Application;
import android.content.Context;

import com.android.hyb.util.NotchScreenUtils;

public class BaseApp extends Application {

    private static BaseApp context;
    private static int mStatusBarHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
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
}
