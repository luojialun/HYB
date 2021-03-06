package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
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
        private String BannerUrl;

        public void setBannerUrl(String bannerUrl) {
            BannerUrl = bannerUrl;
        }

        public String getBannerUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+BannerUrl;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+Url;
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
