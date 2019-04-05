package com.android.hyb.ui.acitvity;

import android.view.View;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;

import butterknife.OnClick;

/**
 * 忘记密码
 */
public class ForgetActivity extends BaseActivity {

    @Override
    public int setViewId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.reset_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_tv:
                finish();
                break;
        }
    }
}
