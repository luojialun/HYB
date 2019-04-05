package com.android.hyb.ui.acitvity;

import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;

import butterknife.OnClick;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {

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

                break;
            case R.id.register_tv:
                finish();
                break;
        }
    }
}
