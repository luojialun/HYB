package com.android.hyb.ui.acitvity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.LoginResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.login_switch)
    Switch loginSwitch;

    @Override
    public int setViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        phoneEt.setText(SPUtils.getInstance().getString(ConstUtils.PHONE));
        passwordEt.setText(SPUtils.getInstance().getString(ConstUtils.PASSWORD));
        if (SPUtils.getInstance().getBoolean(ConstUtils.AUTO_LOGIN)) {
            loginSwitch.setChecked(true);
            login();
        } else {
            loginSwitch.setChecked(false);
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.forget_tv, R.id.register_tv, R.id.login_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString())) {
                    ToastUtils.show(LoginActivity.this, "请输入手机号");
                    break;
                }
                if (TextUtils.isEmpty(passwordEt.getText().toString())) {
                    ToastUtils.show(LoginActivity.this, "请输入密码");
                    break;
                }
                login();
                break;
            case R.id.forget_tv:
                readyGo(ForgetActivity.class);
                break;
            case R.id.register_tv:
                readyGo(RegisterActivity.class);
                break;
        }
    }

    private void login() {
        ServiceFactory.createHYBService(ContentService.class).login(phoneEt.getText().toString(), passwordEt.getText().toString())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<LoginResponse>(this) {
                    @Override
                    public void onNext(LoginResponse response) {
                        ToastUtils.show(LoginActivity.this, "response-->" + response.getData());
                        readyGo(MainActivity.class);
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        ToastUtils.show(LoginActivity.this, "error-->" + e.msg);
                    }
                });
    }
}
