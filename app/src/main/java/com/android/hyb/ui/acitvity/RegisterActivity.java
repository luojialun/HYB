package com.android.hyb.ui.acitvity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.RegisterResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.code_tv)
    TextView codeTv;
    @BindView(R.id.invitation_et)
    EditText invitationEt;
    @BindView(R.id.register_tv)
    TextView registerTv;

    /**
     * 倒数计时器
     */
    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        /**
         * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            codeTv.setText(String.valueOf(millisUntilFinished / 1000) + "s");
        }

        /**
         * 倒计时完成时被调用
         */
        @Override
        public void onFinish() {
            isTimerRun = false;
            codeTv.setText("获取验证码");
        }
    };
    private Boolean isTimerRun = false;

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
                register();
                break;
        }
    }

    private void sendCode() {
        if (TextUtils.isEmpty(phoneEt.getText().toString())) {
            ToastUtils.show(RegisterActivity.this, "手机号为空");
            return;
        }

        if (isTimerRun){
            return;
        }

        ServiceFactory.createHYBService(ContentService.class).sendSMS(phoneEt.getText().toString())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<RegisterResponse>(this) {
                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        if (registerResponse.getData().equals("success")){
                            ToastUtils.show(getActicity(),"验证码发送成功");
                            starCount();
                        }
                        else
                        {
                            ToastUtils.show(getActicity(),"验证码发送失败");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
        });
    }

    private void starCount(){
        isTimerRun = true;
        timer.start();
    }

    private void register() {

        if (TextUtils.isEmpty(phoneEt.getText().toString())) {
            ToastUtils.show(RegisterActivity.this, "手机号为空");
            return;
        }
        if (TextUtils.isEmpty(passwordEt.getText().toString())) {
            ToastUtils.show(RegisterActivity.this, "密码为空");
            return;
        }
        if (TextUtils.isEmpty(confirmEt.getText().toString())) {
            ToastUtils.show(RegisterActivity.this, "确认密码为空");
            return;
        }
        if (TextUtils.isEmpty(codeEt.getText().toString())) {
            ToastUtils.show(RegisterActivity.this, "验证码为空");
            return;
        }
        if (!confirmEt.getText().toString().equals(passwordEt.getText().toString())) {
            ToastUtils.show(RegisterActivity.this, "两次密码不一致");
            return;
        }
        if (TextUtils.isEmpty(invitationEt.getText().toString())){
            ToastUtils.show(RegisterActivity.this, "邀请码为空");
            return;
        }


        ServiceFactory.createHYBService(ContentService.class).register(phoneEt.getText().toString(), passwordEt.getText().toString(),codeEt.getText().toString(),invitationEt.getText().toString())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<RegisterResponse>(this) {
                    @Override
                    public void onNext(RegisterResponse response) {
                        if (response.getData().equals("success")){
                            ToastUtils.show(RegisterActivity.this, "注册成功");
                            finish();
                        } else {
                            ToastUtils.show(RegisterActivity.this, response.getData());
                        }

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
