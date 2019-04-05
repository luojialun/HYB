package com.android.hyb.ui.acitvity;

import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;

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
                SPUtils.getInstance().put(ConstUtils.PHONE, phoneEt.getText().toString());
                SPUtils.getInstance().put(ConstUtils.PASSWORD, passwordEt.getText().toString());
                SPUtils.getInstance().put(ConstUtils.SWITCH_STATE, loginSwitch.isChecked());
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
        readyGoThenKill(MainActivity.class);
    }
}
