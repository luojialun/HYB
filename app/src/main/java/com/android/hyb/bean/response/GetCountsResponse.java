package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

import java.util.List;

public class GetCountsResponse extends BaseResponse {

    private List<Integer> data;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
