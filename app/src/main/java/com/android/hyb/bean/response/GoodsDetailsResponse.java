package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

public class GoodsDetailsResponse extends BaseResponse {

    public GoodsResponse.GoodsBean data;

    public GoodsResponse.GoodsBean getData() {
        return data;
    }

    public void setData(GoodsResponse.GoodsBean data) {
        this.data = data;
    }
}
