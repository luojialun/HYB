package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

import java.util.List;

public class BannerResponse extends BaseResponse {

    private List<BannerBean> data;

    public List<BannerBean> getData() {
        return data;
    }

    public void setData(List<BannerBean> data) {
        this.data = data;
    }

    public static class BannerBean {
        private int Id;
        private String Url;
        private int SortNum;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public int getSortNum() {
            return SortNum;
        }

        public void setSortNum(int sortNum) {
            SortNum = sortNum;
        }
    }

}
