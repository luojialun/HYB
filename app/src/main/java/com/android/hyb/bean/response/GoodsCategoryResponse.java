package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
import com.android.hyb.base.BaseResponse;

import java.util.List;

public class GoodsCategoryResponse extends BaseResponse {

    private List<GoodsCategoryBean> data;

    public List<GoodsCategoryBean> getData() {
        return data;
    }

    public void setData(List<GoodsCategoryBean> data) {
        this.data = data;
    }

    public static class GoodsCategoryBean {

        private int Id;
        private String Url;
        private String Name;
       // private boolean IsPublish;


        public GoodsCategoryBean(int id, String url, String name) {
            Id = id;
            Url = url;
            Name = name;
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

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

//        public boolean isPublish() {
//            return IsPublish;
//        }
//
//        public void setPublish(boolean publish) {
//            IsPublish = publish;
//        }
    }
}
