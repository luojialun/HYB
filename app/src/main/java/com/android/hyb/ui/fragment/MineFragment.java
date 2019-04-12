package com.android.hyb.ui.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.ui.acitvity.MineTeamActivity;
import com.android.hyb.ui.acitvity.OrderActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心fragment
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.image_setting)
    ImageView imageSetting;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_recommand)
    TextView tvRecommand;
    @BindView(R.id.tv_money_number)
    TextView tvMoneyNumber;
    @BindView(R.id.tv_money_text)
    TextView tvMoneyText;
    @BindView(R.id.tv_cash_number)
    TextView tvCashNumber;
    @BindView(R.id.tv_cash_text)
    TextView tvCashText;
    @BindView(R.id.tv_income_number)
    TextView tvIncomeNumber;
    @BindView(R.id.tv_income_text)
    TextView tvIncomeText;
    @BindView(R.id.tv_total_number)
    TextView tvTotalNumber;
    @BindView(R.id.tv_total_text)
    TextView tvTotalText;
    @BindView(R.id.ll_header)
    RelativeLayout llHeader;
    @BindView(R.id.image_header)
    ImageView imageHeader;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.image_unpay)
    ImageView imageUnpay;
    @BindView(R.id.image_unsend)
    ImageView imageUnsend;
    @BindView(R.id.image_unget)
    ImageView imageUnget;
    @BindView(R.id.image_finish)
    ImageView imageFinish;
    @BindView(R.id.ll_order)
    RelativeLayout llOrder;
    @BindView(R.id.image_team)
    ImageView imageTeam;
    @BindView(R.id.ll_team)
    RelativeLayout llTeam;
    @BindView(R.id.image_get_cash)
    ImageView imageGetCash;
    @BindView(R.id.ll_get_cash)
    RelativeLayout llGetCash;
    @BindView(R.id.image_detail)
    ImageView imageDetail;
    @BindView(R.id.ll_detail)
    RelativeLayout llDetail;
    @BindView(R.id.image_share)
    ImageView imageShare;
    @BindView(R.id.ll_share)
    RelativeLayout llShare;
    @BindView(R.id.ll_other)
    RelativeLayout llOther;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public int setViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        GlideApp.with(this)
                .load(UserInfo.getTotalAvatarUrl())
                .placeholder(R.mipmap.header_default)
                .into(imageHeader);


        String replaceStr = UserInfo.getMobile().substring(3, 7);
        String showMobile = UserInfo.getMobile().replace(replaceStr, "****");
        tvMobile.setText(showMobile);
        tvMoneyNumber.setText(UserInfo.getAvailableFunds() + "");


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.image_unpay, R.id.image_unsend, R.id.image_unget, R.id.image_finish, R.id.ll_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_unpay:
                readyGo(OrderActivity.class);
                break;
            case R.id.image_unsend:
                readyGo(OrderActivity.class);
                break;
            case R.id.image_unget:
                readyGo(OrderActivity.class);
                break;
            case R.id.image_finish:
                readyGo(OrderActivity.class);
                break;
            case R.id.ll_team:
                readyGo(MineTeamActivity.class);
                break;
        }
    }

}
