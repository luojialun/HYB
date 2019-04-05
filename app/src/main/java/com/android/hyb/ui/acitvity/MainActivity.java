package com.android.hyb.ui.acitvity;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.ui.adapter.MainPageAdapter;
import com.android.hyb.ui.fragment.MainFragment;
import com.android.hyb.ui.fragment.MineFragment;
import com.android.hyb.ui.fragment.ShareCodeFragment;
import com.android.hyb.util.ToastUtils;
import com.android.hyb.widget.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    NoSlideViewPager viewPager;
    @BindView(R.id.home_iv)
    ImageView homeIv;
    @BindView(R.id.sharecode_iv)
    ImageView sharecodeIv;
    @BindView(R.id.mine_iv)
    ImageView mineIv;
    @BindView(R.id.home_tv)
    TextView homeTv;
    @BindView(R.id.sharecode_tv)
    TextView sharecodeTv;
    @BindView(R.id.mine_tv)
    TextView mineTv;

    private long firstTime = 0;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                ToastUtils.show(MainActivity.this, "再按一次退出");
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int setViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        viewPager.setScanScroll(false);
    }

    @Override
    public void initData() {
        fragmentList.add(new MainFragment());
        fragmentList.add(new ShareCodeFragment());
        fragmentList.add(new MineFragment());
        MainPageAdapter adapter = new MainPageAdapter(fragmentList, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @OnClick({R.id.home_ll, R.id.sharecode_ll, R.id.mine_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_ll:
                setTabSelect(0);
                break;
            case R.id.sharecode_ll:
                setTabSelect(1);
                break;
            case R.id.mine_ll:
                setTabSelect(2);
                break;
        }
    }

    public void setTabSelect(int position) {
        viewPager.setCurrentItem(position, false);
        switch (position) {
            case 0:
                homeIv.setImageResource(R.mipmap.h1_1);
                homeTv.setTextColor(getResources().getColor(R.color.color_e26e1a));

                sharecodeIv.setImageResource(R.mipmap.h3_0);
                sharecodeTv.setTextColor(getResources().getColor(R.color.color_9a9a9a));

                mineIv.setImageResource(R.mipmap.h4_0);
                mineTv.setTextColor(getResources().getColor(R.color.color_9a9a9a));
                break;
            case 1:
                homeIv.setImageResource(R.mipmap.h1_0);
                homeTv.setTextColor(getResources().getColor(R.color.color_9a9a9a));

                sharecodeIv.setImageResource(R.mipmap.h3_1);
                sharecodeTv.setTextColor(getResources().getColor(R.color.color_e26e1a));

                mineIv.setImageResource(R.mipmap.h4_0);
                mineTv.setTextColor(getResources().getColor(R.color.color_9a9a9a));
                break;
            case 2:
                homeIv.setImageResource(R.mipmap.h1_0);
                homeTv.setTextColor(getResources().getColor(R.color.color_9a9a9a));

                sharecodeIv.setImageResource(R.mipmap.h3_0);
                sharecodeTv.setTextColor(getResources().getColor(R.color.color_9a9a9a));

                mineIv.setImageResource(R.mipmap.h4_1);
                mineTv.setTextColor(getResources().getColor(R.color.color_e26e1a));
                break;
        }
    }

}
