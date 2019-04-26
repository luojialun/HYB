package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

import java.util.List;

public class NewHotSellingGoodsResponse extends BaseResponse {
    List<GoodsResponse.GoodsBean> data;

    public List<GoodsResponse.GoodsBean> getData() {
        return data;
    }

    public void setData(List<GoodsResponse.GoodsBean> data) {
        this.data = data;
    }
}
