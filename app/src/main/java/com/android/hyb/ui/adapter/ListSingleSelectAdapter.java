package com.android.hyb.ui.adapter;

import android.support.annotation.Nullable;

import com.android.hyb.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * created by luojialun on 2018/9/29
 */
public class ListSingleSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ListSingleSelectAdapter(@Nullable List<String> data) {
        super(R.layout.item_list_single_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.content_tv, item);
    }
}
