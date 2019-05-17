package com.android.hyb.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseFragment;
import com.android.hyb.base.GlideApp;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.response.UserResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.CashDetailActivity;
import com.android.hyb.ui.acitvity.EmployActivity;
import com.android.hyb.ui.acitvity.LoginActivity;
import com.android.hyb.ui.acitvity.MerchantActivity;
import com.android.hyb.ui.acitvity.MineTeamActivity;
import com.android.hyb.ui.acitvity.OrderActivity;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心fragment
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_mobile)
    TextView tvMobile;
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
    @BindView(R.id.ll_merchant)
    RelativeLayout llMerchant;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.invitationCode_tv)
    TextView invitationCodeTv;
    @BindView(R.id.image_merchant)
    ImageView imageMerchant;
    @BindView(R.id.vip_tv)
    TextView vipTv;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public int setViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        updateHeaderView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserAgent();
    }

    @Override
    public void initData() {
    }

    public void getUserAgent() {
        ServiceFactory.createHYBService(ContentService.class).getUserAgent(UserInfo.getToken())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<UserResponse>(this.getContext()) {
                    @Override
                    public void onNext(UserResponse response) {
                        UserInfo.setId(response.getData().getId());
                        UserInfo.setNickName(response.getData().getNickName());
                        UserInfo.setAvatarUrl(response.getData().getAvatarUrl());
                        UserInfo.setRole(response.getData().getRole());
                        UserInfo.setAccessNum(response.getData().getAccessNum());
                        UserInfo.setLastAccessTime(response.getData().getLastAccessTime());
                        UserInfo.setAccessNum(response.getData().getAccessNum());
                        UserInfo.setMobile(response.getData().getMobile());
                        UserInfo.setAvailableFunds(response.getData().getAvailableFunds());
                        UserInfo.setFrozenFunds(response.getData().getFrozenFunds());
                        UserInfo.setOpenId(response.getData().getOpenId());
                        UserInfo.setSessionKey(response.getData().getSessionKey());
                        UserInfo.setGender(response.getData().getGender());
                        UserInfo.setRemarks(response.getData().getRemarks());
                        UserInfo.setNumber(response.getData().getNumber());
                        UserInfo.setParentId(response.getData().getParentId());
                        UserInfo.setInvitationCode(response.getData().getInvitationCode());
                        UserInfo.setWithdraw(response.getData().getWithdraw());
                        UserInfo.setEarnings(response.getData().getEarnings());
                        UserInfo.setTodayEarnings(response.getData().getTodayEarnings());

                        updateHeaderView();
                    }
                });
    }

    private void updateHeaderView() {
        GlideApp.with(this)
                .load(UserInfo.getTotalAvatarUrl())
                .placeholder(R.mipmap.header_default)
                .into(imageHeader);


        if (UserInfo.getMobile().length() > 7) {
            String replaceStr = UserInfo.getMobile().substring(3, 7);
            String showMobile = UserInfo.getMobile().replace(replaceStr, "****");
            tvMobile.setText(showMobile);
        } else {
            String replaceStr = UserInfo.getMobile();
            String showMobile = UserInfo.getMobile().replace(replaceStr, "****");
            tvMobile.setText(showMobile);
        }

        tvMoneyNumber.setText(UserInfo.getAvailableFunds() + "");
        tvCashNumber.setText(UserInfo.getWithdraw() + "");
        tvIncomeNumber.setText(UserInfo.getEarnings() + "");
        tvTotalNumber.setText(UserInfo.getTodayEarnings() + "");

        if (TextUtils.isEmpty(UserInfo.getInvitationCode())) {
            invitationCodeTv.setVisibility(View.GONE);
        } else {
            invitationCodeTv.setVisibility(View.VISIBLE);
            invitationCodeTv.setText(UserInfo.getInvitationCode());
        }

        if (TextUtils.isEmpty(UserInfo.getRole())) {
            vipTv.setVisibility(View.GONE);
        } else {
            vipTv.setVisibility(View.VISIBLE);
            vipTv.setText(UserInfo.getRole());
        }
    }

    @OnClick({R.id.image_unpay, R.id.image_unsend, R.id.image_unget, R.id.image_finish, R.id.ll_team, R.id.ll_merchant, R.id.tv_logout, R.id.ll_share})
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
            case R.id.ll_merchant:
                readyGo(MerchantActivity.class);
                break;
            case R.id.tv_logout:
                logout();
                break;
            case R.id.ll_share:
                readyGo(EmployActivity.class);
                break;
            case R.id.ll_detail:
                readyGo(CashDetailActivity.class);
                break;
        }
    }

    public void logout() {
        SPUtils.getInstance().remove(ConstUtils.PHONE);
        SPUtils.getInstance().remove(ConstUtils.PASSWORD);
        SPUtils.getInstance().remove(ConstUtils.AUTO_LOGIN);
        SPUtils.getInstance().remove(ConstUtils.TOKEN);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

}
