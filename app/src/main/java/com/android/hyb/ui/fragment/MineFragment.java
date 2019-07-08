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
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.response.GetCountsResponse;
import com.android.hyb.bean.response.GetGroupResponse;
import com.android.hyb.bean.response.UserResponse;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.acitvity.CashDetailActivity;
import com.android.hyb.ui.acitvity.EmployActivity;
import com.android.hyb.ui.acitvity.GetCashActivity;
import com.android.hyb.ui.acitvity.LoginActivity;
import com.android.hyb.ui.acitvity.MineTeamActivity;
import com.android.hyb.ui.acitvity.OrderActivity;
import com.android.hyb.ui.acitvity.SettingActivity;
import com.android.hyb.ui.acitvity.UpdateShopActivity;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.util.ToastUtils;
import com.bumptech.glide.Glide;

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
//    @BindView(R.id.ll_merchant)
//    RelativeLayout llMerchant;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
//    @BindView(R.id.image_merchant)
//    ImageView imageMerchant;
    @BindView(R.id.unpay_tv)
    TextView unpayTv;
    @BindView(R.id.unsend_tv)
    TextView unsendTv;
    @BindView(R.id.unget_tv)
    TextView ungetTv;
    @BindView(R.id.finish_tv)
    TextView finishTv;
    @BindView(R.id.tv_invate)
    TextView tvInvate;
    @BindView(R.id.tv_vip)
    TextView tvVip;


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
        ServiceFactory.createHYBService(ContentService.class)
                .getUserAgent(UserInfo.getToken())
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
                        UserInfo.setParentOpenId(response.getData().getParentOpenId());
                        UserInfo.setAlipayUrl(response.getData().getAlipayUrl());
                        UserInfo.setWeChatUrl(response.getData().getWeChatUrl());

                        updateHeaderView();
                    }
                });

        ServiceFactory.createHYBService(ContentService.class)
                .getGroup(UserInfo.getToken())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetGroupResponse>(this.getContext()) {
                    @Override
                    public void onNext(GetGroupResponse response) {
                        if (response.getData().getGroupName() != null) {
                            tvVip.setText(response.getData().getGroupName());
                            tvVip.setVisibility(View.VISIBLE);
                        } else {
                            tvVip.setVisibility(View.GONE);
                        }
                    }
                });

        ServiceFactory.createHYBService(ContentService.class)
                .getCounts(UserInfo.getToken())
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetCountsResponse>(this.getContext()) {
                    @Override
                    public void onNext(GetCountsResponse getCountsResponse) {
                        if (getCountsResponse.getData().size() >= 4) {
                            unpayTv.setText(getCountsResponse.getData().get(0) + "");
                            unsendTv.setText(getCountsResponse.getData().get(1) + "");
                            ungetTv.setText(getCountsResponse.getData().get(2) + "");
                            finishTv.setText(getCountsResponse.getData().get(3) + "");
                        }
                    }
                });


    }

    private void updateHeaderView() {
        imageHeader.setImageResource(R.mipmap.header_default);

        tvMobile.setText(UserInfo.getOpenId());

        tvMoneyNumber.setText(UserInfo.getAvailableFunds() + "");
        tvCashNumber.setText(UserInfo.getWithdraw() + "");
        tvIncomeNumber.setText(UserInfo.getTodayEarnings() + "");
        tvTotalNumber.setText(UserInfo.getEarnings() + "");

        if (TextUtils.isEmpty(UserInfo.getInvitationCode())) {
            tvInvate.setVisibility(View.GONE);
        } else {
            tvInvate.setVisibility(View.VISIBLE);
            tvInvate.setText("邀请码：" + UserInfo.getInvitationCode());
        }
    }

    @OnClick({R.id.tv_all_order, R.id.image_unpay, R.id.image_unsend, R.id.image_unget, R.id.image_finish, R.id.ll_team, R.id.tv_logout, R.id.ll_share, R.id.ll_detail, R.id.setting_iv, R.id.ll_get_cash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all_order: {
                Intent intent = new Intent(this.getContext(), OrderActivity.class);
                intent.putExtra(ConstUtils.ID, 0);
                startActivity(intent);
            }
            break;
            case R.id.image_unpay: {
                Intent intent = new Intent(this.getContext(), OrderActivity.class);
                intent.putExtra(ConstUtils.ID, 1);
                startActivity(intent);
            }
            break;
            case R.id.image_unsend: {
                Intent intent = new Intent(this.getContext(), OrderActivity.class);
                intent.putExtra(ConstUtils.ID, 2);
                startActivity(intent);
            }
            break;
            case R.id.image_unget: {
                Intent intent = new Intent(this.getContext(), OrderActivity.class);
                intent.putExtra(ConstUtils.ID, 3);
                startActivity(intent);
            }
            break;
            case R.id.image_finish: {
                Intent intent = new Intent(this.getContext(), OrderActivity.class);
                intent.putExtra(ConstUtils.ID, 4);
                startActivity(intent);
            }
            break;
            case R.id.ll_team:
                readyGo(MineTeamActivity.class);
                break;
//            case R.id.ll_merchant:
//                readyGo(UpdateShopActivity.class);
//                break;
            case R.id.tv_logout:
                logout();
                break;
            case R.id.ll_share:
                readyGo(EmployActivity.class);
                break;
            case R.id.ll_detail:
                readyGo(CashDetailActivity.class);
                break;
            case R.id.setting_iv:
                readyGo(SettingActivity.class);
                break;
            case R.id.ll_get_cash: {
                if (UserInfo.getWeChatUrl().length() == 0 && UserInfo.getAlipayUrl().length() == 0) {
                    ToastUtils.show(getActivity(), "请先上传 提现二维码");
                    break;
                }
                readyGo(GetCashActivity.class);
            }
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
