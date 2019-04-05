package com.android.hyb.ui.acitvity;

import android.os.Handler;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.util.ConstUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {

    private static final long TIME = 2000;
    private long time;

    @Override
    public int setViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        time = System.currentTimeMillis();
        checkPermission();

    }

    private void checkPermission() {
        AndPermission.with(this)
                .runtime().permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        goNext();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(SplashActivity.this, data)) {
                            // 用Dialog展示没有某权限，询问用户是否去设置中授权。
                            AndPermission.with(SplashActivity.this)
                                    .runtime()
                                    .setting()
                                    .start(ConstUtils.REQUEST_PERMISSION);
                        }
                    }
                }).start();
    }

    @Override
    public void initData() {

    }

    public void goNext() {
        long remain = TIME - (System.currentTimeMillis() - time);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readyGoThenKill(LoginActivity.class);
            }
        }, remain > 0 ? remain : 0);
    }
}
