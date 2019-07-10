package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
import com.android.hyb.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

public class GoodsResponse extends BaseResponse {

    private List<GoodsBean> data;

    public List<GoodsBean> getData() {
        return data;
    }

    public void setData(List<GoodsBean> data) {
        this.data = data;
    }

    public static class GoodsBean implements Serializable {


        /**
         * Id : 1
         * CategoryId : 1
         * OpenId :
         * Name : v商神器
         * Sales : 0
         * Url : /goods/1d4c444b6af24ff5867d701e73d8104b.png
         * Url1 :
         * Url2 :
         * Url3 :
         * Url4 :
         * Url5 :
         * Url6 :
         * Url7 :
         * Url8 :
         * Url9 :
         * Url10 :
         * Details :
         * IsPublish : true
         * OriginalPrice : 44
         * PresentPrice : 44
         * AgentPrice : float
         * NoCoupon : false
         * SizeString :
         */

        private int Id;
        private int CategoryId;
        private String OpenId;
        private String Name;
        private int Sales;
        private String Url;
        private String Url1;
        private String Url2;
        private String Url3;
        private String Url4;
        private String Url5;
        private String Url6;
        private String Url7;
        private String Url8;
        private String Url9;
        private String Url10;
        private String Details;
        private boolean IsPublish;
        private float OriginalPrice;
        private float PresentPrice;
        private String AgentPrice;
        private boolean NoCoupon;
        private String SizeString;
        private float Commission;

        public float getCommission() {
            return Commission;
        }

        public void setCommission(float commission) {
            Commission = commission;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(int CategoryId) {
            this.CategoryId = CategoryId;
        }

        public String getOpenId() {
            return OpenId;
        }

        public void setOpenId(String OpenId) {
            this.OpenId = OpenId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getSales() {
            return Sales;
        }

        public void setSales(int Sales) {
            this.Sales = Sales;
        }

        public String getUrl() {
            return BuildConfig.serverUrl + "/Yinliubao/images" + Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getUrl1() {
            return Url1;
        }

        public void setUrl1(String Url1) {
            this.Url1 = Url1;
        }

        public String getUrl2() {
            return Url2;
        }

        public void setUrl2(String Url2) {
            this.Url2 = Url2;
        }

        public String getUrl3() {
            return Url3;
        }

        public void setUrl3(String Url3) {
            this.Url3 = Url3;
        }

        public String getUrl4() {
            return Url4;
        }

        public void setUrl4(String Url4) {
            this.Url4 = Url4;
        }

        public String getUrl5() {
            return Url5;
        }

        public void setUrl5(String Url5) {
            this.Url5 = Url5;
        }

        public String getUrl6() {
            return Url6;
        }

        public void setUrl6(String Url6) {
            this.Url6 = Url6;
        }

        public String getUrl7() {
            return Url7;
        }

        public void setUrl7(String Url7) {
            this.Url7 = Url7;
        }

        public String getUrl8() {
            return Url8;
        }

        public void setUrl8(String Url8) {
            this.Url8 = Url8;
        }

        public String getUrl9() {
            return Url9;
        }

        public void setUrl9(String Url9) {
            this.Url9 = Url9;
        }

        public String getUrl10() {
            return Url10;
        }

        public void setUrl10(String Url10) {
            this.Url10 = Url10;
        }

        public String getDetails() {
            return Details;
        }

        public void setDetails(String Details) {
            this.Details = Details;
        }

        public boolean isIsPublish() {
            return IsPublish;
        }

        public void setIsPublish(boolean IsPublish) {
            this.IsPublish = IsPublish;
        }

        public float getOriginalPrice() {
            return OriginalPrice;
        }

        public void setOriginalPrice(float OriginalPrice) {
            this.OriginalPrice = OriginalPrice;
        }

        public float getPresentPrice() {
            return PresentPrice;
        }

        public void setPresentPrice(float PresentPrice) {
            this.PresentPrice = PresentPrice;
        }

        public String getAgentPrice() {
            return AgentPrice;
        }

        public void setAgentPrice(String AgentPrice) {
            this.AgentPrice = AgentPrice;
        }

        public boolean isNoCoupon() {
            return NoCoupon;
        }

        public void setNoCoupon(boolean NoCoupon) {
            this.NoCoupon = NoCoupon;
        }

        public String getSizeString() {
            return SizeString;
        }

        public void setSizeString(String SizeString) {
            this.SizeString = SizeString;
        }
    }
}
