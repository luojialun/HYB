package com.android.hyb.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppUtils {

    /**
     * 判断 App 是否安装
     * 通过包名找单个应用 来判断是否安装改应用 不需要获取应用列表权限
     *
     * @param pkgName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean hasPackage(Context context, String pkgName) {
        if (null == context || null == pkgName) {
            return false;
        }

        boolean bHas = true;
        try {
            context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_GIDS);
        } catch (PackageManager.NameNotFoundException e) {
            // 抛出找不到的异常，说明该程序已经被卸载
            bHas = false;
        }
        return bHas;
    }

    public static boolean isAliPayInstall(Context context) {
        return hasPackage(context, "com.eg.android.AlipayGphone");
    }

    public static boolean isWechatInstall(Context context) {
        return hasPackage(context, "com.tencent.mm");
    }

}
