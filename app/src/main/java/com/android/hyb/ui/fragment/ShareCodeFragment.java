package com.android.hyb.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.bean.clazz.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分享码fragment
 */
public class ShareCodeFragment extends BaseFragment {
    @BindView(R.id.code_tv)
    TextView codeTv;

//    @BindView(R.id.shareCode_image)
//    ImageView shareCodeImage;

    @Override
    public int setViewId() {
        return R.layout.fragment_share_code;
    }

    @Override
    public void initView() {
        if (UserInfo.getInvitationCode() != null){
            codeTv.setText(UserInfo.getInvitationCode());
        }
    }

    @Override
    public void initData() {

    }

}
