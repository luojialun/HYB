package com.android.hyb.ui.acitvity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.ui.adapter.GoodsListAdapter;
import com.android.hyb.util.ConstUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.goods_list_rv)
    RecyclerView goodsListRv;

    private String title;

    @Override
    public int setViewId() {
        return R.layout.activity_goods_list;
    }

    @Override
    public void initParams() {
        title = getIntent().getStringExtra(ConstUtils.TITLE);
    }

    @Override
    public void initView() {
        titleTv.setText(title);
        initRecyclerView();
    }

    private void initRecyclerView() {
        goodsListRv.setLayoutManager(new GridLayoutManager(this, 2));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("i-->" + i);
        }
        GoodsListAdapter adapter = new GoodsListAdapter(list);
        goodsListRv.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_cardview:
                        readyGo(GoodsDetailsActivity.class);
                        break;
                    case R.id.buy_tv:
                        readyGo(OrderDetailsActivity.class);
                        break;
                }
            }
        });

    }

    @Override
    public void initData() {

    }
}
