package com.android.hyb.ui.acitvity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.ui.adapter.MainPageAdapter;
import com.android.hyb.ui.fragment.OrderFragment;
import com.android.hyb.widget.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    NoSlideViewPager viewpager;
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
    @BindView(R.id.tv_tab4)
    TextView tvTab4;
    @BindView(R.id.image_tab4)
    ImageView imageTab4;
    @BindView(R.id.tv_tab5)
    TextView tvTab5;
    @BindView(R.id.image_tab5)
    ImageView imageTab5;

    private OrderFragment allFragment,unpayFragment,unsendFragment,ungetFragment,finishFragment;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int setViewId() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {

        tvTab1.setTextColor(Color.parseColor("#86e2d2"));
        imageTab1.setVisibility(View.VISIBLE);

        allFragment = new  OrderFragment();
        unpayFragment = new  OrderFragment();
        unsendFragment = new  OrderFragment();
        ungetFragment = new  OrderFragment();
        finishFragment = new  OrderFragment();

        allFragment.type = 0;
        unpayFragment.type = 1;
        unsendFragment.type = 2;
        ungetFragment.type = 3;
        finishFragment.type = 4;

        fragmentList.add(allFragment);
        fragmentList.add(unpayFragment);
        fragmentList.add(unsendFragment);
        fragmentList.add(ungetFragment);
        fragmentList.add(finishFragment);

        MainPageAdapter adapter = new MainPageAdapter(fragmentList, getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_tab1, R.id.tv_tab2, R.id.tv_tab3, R.id.tv_tab4, R.id.tv_tab5})
    public void onViewClicked(View view) {
        tvTab1.setTextColor(Color.parseColor("#272727"));
        imageTab1.setVisibility(View.GONE);

        tvTab2.setTextColor(Color.parseColor("#272727"));
        imageTab2.setVisibility(View.GONE);

        tvTab3.setTextColor(Color.parseColor("#272727"));
        imageTab3.setVisibility(View.GONE);

        tvTab4.setTextColor(Color.parseColor("#272727"));
        imageTab4.setVisibility(View.GONE);

        tvTab5.setTextColor(Color.parseColor("#272727"));
        imageTab5.setVisibility(View.GONE);


        switch (view.getId()) {
            case R.id.tv_tab1:
                tvTab1.setTextColor(Color.parseColor("#86e2d2"));
                imageTab1.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(0, true);
                break;
            case R.id.tv_tab2:
                tvTab2.setTextColor(Color.parseColor("#86e2d2"));
                imageTab2.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(1, true);
                break;
            case R.id.tv_tab3:
                tvTab3.setTextColor(Color.parseColor("#86e2d2"));
                imageTab3.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(2, true);
                break;
            case R.id.tv_tab4:
                tvTab4.setTextColor(Color.parseColor("#86e2d2"));
                imageTab4.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(3, true);
                break;
            case R.id.tv_tab5:
                tvTab5.setTextColor(Color.parseColor("#86e2d2"));
                imageTab5.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(4, true);
                break;
        }
    }
}
