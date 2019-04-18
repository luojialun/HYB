package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
import com.android.hyb.base.BaseResponse;

public class GetPlatformInfoResponse extends BaseResponse {

    private PlatformInfoBean data;

    public PlatformInfoBean getData() {
        return data;
    }

    public void setData(PlatformInfoBean data) {
        this.data = data;
    }

    public static class PlatformInfoBean {

        /**
         * Id : 1
         * Name : 引流宝
         * LogoUrl : /business/597fa1e936de43a3961073a1007b09c2.png
         * ShareCodeUrl : /business/02a72a225191445e8f62c3b010cacd63.jpg
         * ExtensionUrl : /business/74f346131d99470f85203a453812b4a9.jpg
         */

        private int Id;
        private String Name;
        private String LogoUrl;
        private String ShareCodeUrl;
        private String ExtensionUrl;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getLogoUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+LogoUrl;
        }

        public void setLogoUrl(String LogoUrl) {
            this.LogoUrl = LogoUrl;
        }

        public String getShareCodeUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+ShareCodeUrl;
        }

        public void setShareCodeUrl(String ShareCodeUrl) {
            this.ShareCodeUrl = ShareCodeUrl;
        }

        public String getExtensionUrl() {
            return BuildConfig.serverUrl+"/Yinliubao/images"+ExtensionUrl;
        }

        public void setExtensionUrl(String ExtensionUrl) {
            this.ExtensionUrl = ExtensionUrl;
        }
    }
}
