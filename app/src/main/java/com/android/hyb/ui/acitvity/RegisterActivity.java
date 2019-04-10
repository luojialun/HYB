package com.android.hyb.ui.acitvity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.confirm_et)
    EditText confirmEt;
    @BindView(R.id.code_et)
    EditText codeEt;

    @Override
    public int setViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.code_tv, R.id.register_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.code_tv:
                sendCode();
                break;
            case R.id.register_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "手机号为空");
                    break;
                }
                if (TextUtils.isEmpty(passwordEt.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "密码为空");
                    break;
                }
              /*  if (TextUtils.isEmpty(confirmEt.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "确认密码为空");
                    break;
                }
                if (TextUtils.isEmpty(codeEt.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "验证码为空");
                    break;
                }
                if (confirmEt.getText().toString().equals(passwordEt.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "两次密码不一致");
                    break;
                }*/
                register();
                break;
        }
    }

    private void sendCode() {

    }

    private void register() {
        ServiceFactory.createHYBService(ContentService.class).register(phoneEt.getText().toString(), passwordEt.getText().toString())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<String>(this) {
                    @Override
                    public void onNext(String response) {
                        ToastUtils.show(RegisterActivity.this, response);
                        finish();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }
}
