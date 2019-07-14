package com.android.hyb.ui.acitvity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.response.GoodsDetailsResponse;
import com.android.hyb.bean.response.GoodsResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.util.ConstUtils;
import com.android.hyb.util.ToastUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class GoodsDetailsActivity extends BaseActivity {

    @BindView(R.id.content_iv)
    ImageView contentIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.sales_tv)
    TextView salesTv;
    @BindView(R.id.desc_tv)
    TextView descTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.hidden_tv)
    TextView hiddenTv;

    private int id;
    private GoodsResponse.GoodsBean goodsBean;

    @Override
    public int setViewId() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void initParams() {
        id = getIntent().getIntExtra(ConstUtils.ID, 0);
    }

    @Override
    public void initView() {
        descTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                copy(descTv.getText().toString());
                return false;
            }
        });
    }

    @Override
    public void initData() {
        getGoodsDetails();
    }

    private void getGoodsDetails() {
        showProgress();
        ServiceFactory.createHYBService(ContentService.class).getGoodsDetails(id)
                .compose(new RemoteTransformer<GoodsDetailsResponse>())
                .subscribe(new ToastObserver<GoodsDetailsResponse>(this) {
                    @Override
                    public void onNext(GoodsDetailsResponse response) {
                        dismissProgress();
                        if (null != response && response.getData() != null) {
                            goodsBean = response.getData();
                            Glide.with(GoodsDetailsActivity.this).load(response.getData().getUrl()).into(contentIv);
                            nameTv.setText(response.getData().getName());
                            salesTv.setText(response.getData().getSales() + "人已付款");
                            descTv.setText(response.getData().getDetails());
                            priceTv.setText("¥" + String.valueOf(response.getData().getPresentPrice()));
                            if (response.getData().getHiddenInfo() != null && !TextUtils.isEmpty(response.getData().getHiddenInfo())){
                                hiddenTv.setText(response.getData().getHiddenInfo());
                                hiddenTv.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                hiddenTv.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        dismissProgress();
                    }
                });
    }

    @OnClick({R.id.desc_tv, R.id.shop_ll, R.id.buy_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.desc_tv:
                ToastUtils.show(this, "长按复制内容");
                break;
            case R.id.shop_ll:
                finish();
                break;
           /* case R.id.share_code_tv:
            case R.id.share_code_ll:
                ShareCodePop shareCodePop = new ShareCodePop(this);
                shareCodePop.showPopupWindow();
                break;*/
            case R.id.buy_tv:
                Intent detailsIntent = new Intent(this, OrderDetailsActivity.class);
                detailsIntent.putExtra(ConstUtils.Bean, goodsBean);
                startActivity(detailsIntent);
                break;
        }
    }

    public void copy(String content) {
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content);
    }
}
