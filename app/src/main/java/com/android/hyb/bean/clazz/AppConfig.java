package com.android.hyb.bean.clazz;

import com.android.hyb.base.BaseApp;

import java.io.File;

public class AppConfig {

    // 默认超时时间
    public static final int TIME_OUT = 20;

    // okhttp缓存空间
    public static final int OK_HTTP_CACHE_SIZE = 400 * 1024 * 1024;

    public static final String DEFAULT_OKHTTP_CACHE_PATH = BaseApp.getContext().getFilesDir() + File.separator;

    public static final String DEFAULT_SAVE_OK_HTTP_PATH = DEFAULT_OKHTTP_CACHE_PATH + "okhttp" + File.separator;

}
