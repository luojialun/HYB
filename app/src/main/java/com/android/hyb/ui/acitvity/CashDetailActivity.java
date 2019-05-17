package com.android.hyb.ui.acitvity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.WalletRecordResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.MainPageAdapter;
import com.android.hyb.ui.fragment.OrderFragment;
import com.android.hyb.ui.fragment.WalletFragment;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.SPUtils;
import com.android.hyb.widget.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CashDetailActivity extends BaseActivity {

    @BindView(R.id.tv_tab1)
    TextView tvTab1;
    @BindView(R.id.image_tab1)
    ImageView imageTab1;
    @BindView(R.id.tv_tab2)
    TextView tvTab2;
    @BindView(R.id.image_tab2)
    ImageView imageTab2;
    @BindView(R.id.tv_tab3)
    TextView tvTab3;
    @BindView(R.id.image_tab3)
    ImageView imageTab3;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.viewpager)
    NoSlideViewPager viewpager;

    private WalletFragment vipFragment, shopFragment, cashFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int setViewId() {
        return R.layout.activity_cash_detail;
    }

    @Override
    public void initView() {
        tvTab1.setTextColor(Color.parseColor("#86e2d2"));
        imageTab1.setVisibility(View.VISIBLE);

        vipFragment = new WalletFragment();
        shopFragment = new WalletFragment();
        cashFragment = new WalletFragment();

        vipFragment.setType(10);
        shopFragment.setType(20);
        cashFragment.setType(30);

        fragmentList.add(vipFragment);
        fragmentList.add(shopFragment);
        fragmentList.add(cashFragment);

        MainPageAdapter adapter = new MainPageAdapter(fragmentList, getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(fragmentList.size());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.tv_tab1, R.id.tv_tab2, R.id.tv_tab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tab1:
                selectTab(0);
                break;
            case R.id.tv_tab2:
                selectTab(1);
                break;
            case R.id.tv_tab3:
                selectTab(2);
                break;
        }
    }

    public void selectTab(int num) {
        tvTab1.setTextColor(Color.parseColor("#272727"));
        imageTab1.setVisibility(View.GONE);

        tvTab2.setTextColor(Color.parseColor("#272727"));
        imageTab2.setVisibility(View.GONE);

        tvTab3.setTextColor(Color.parseColor("#272727"));
        imageTab3.setVisibility(View.GONE);

        switch (num) {
            case 0:
                tvTab1.setTextColor(Color.parseColor("#86e2d2"));
                imageTab1.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(0, true);
                break;
            case 1:
                tvTab2.setTextColor(Color.parseColor("#86e2d2"));
                imageTab2.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(1, true);
                break;
            case 2:
                tvTab3.setTextColor(Color.parseColor("#86e2d2"));
                imageTab3.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(2, true);
                break;
        }
    }
}
