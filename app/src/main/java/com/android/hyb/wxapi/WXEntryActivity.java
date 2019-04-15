package com.android.hyb.wxapi;

import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.event.WechatFeedBackEvent;
import com.android.hyb.util.ConstUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public int setViewId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        api = WXAPIFactory.createWXAPI(this, ConstUtils.WECHAT_PAY_APP_ID, true);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            EventBus.getDefault().post(new WechatFeedBackEvent(resp.errCode));
        }
        finish();

    }
}