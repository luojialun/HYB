package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

public class WanResponse extends BaseResponse.ErrorResponse {

    private int curPage;


    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
