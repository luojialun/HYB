package com.android.hyb.ui.acitvity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.ToastUtils;
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
        AndPermission.with(this).runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE,Permission.CAMERA,Permission.READ_PHONE_STATE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        goNext();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.show(SplashActivity.this, "请开启所需要的权限");
                        AndPermission.with(SplashActivity.this)
                                .runtime()
                                .setting()
                                .start(ConstUtils.REQUEST_PERMISSION);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstUtils.REQUEST_PERMISSION:
                if (AndPermission.hasPermissions(this, Permission.Group.STORAGE)) {
                    goNext();
                } else {
                    onBackPressed();
                }
                break;
        }
    }
}
