package com.android.hyb.bean.response;

import com.android.hyb.BuildConfig;
import com.android.hyb.base.BaseResponse;

import java.util.List;

public class BusinessSingleGoodBean extends BaseResponse {

    private BusinessGoodsBean data;

    public BusinessGoodsBean getData() {
        return data;
    }

    public void setData(BusinessGoodsBean data) {
        this.data = data;
    }

    public static class BusinessGoodsBean {

        /**
         * Id : 2
         * CategoryId : 1
         * OpenId :
         * Name : 魔力小V
         * Sales : 1235
         * Url : /goods/bb9f5b27f0184365be71d26f655d8718.jpg
         * Details :
         * IsPublish : true
         * OriginalPrice : 2
         * PresentPrice : 2
         * AgentPrice :
         * BrandId :
         * NoCoupon : false
         * SizeString :
         */

        private int Id;
        private int CategoryId;
        private String OpenId;
        private String Name;
        private int Sales;
        private String Url;
        private String Details;
        private boolean IsPublish;
        private int OriginalPrice;
        private int PresentPrice;
        private String AgentPrice;
        private String BrandId;
        private boolean NoCoupon;
        private String SizeString;

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
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
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

        public int getOriginalPrice() {
            return OriginalPrice;
        }

        public void setOriginalPrice(int OriginalPrice) {
            this.OriginalPrice = OriginalPrice;
        }

        public int getPresentPrice() {
            return PresentPrice;
        }

        public void setPresentPrice(int PresentPrice) {
            this.PresentPrice = PresentPrice;
        }

        public String getAgentPrice() {
            return AgentPrice;
        }

        public void setAgentPrice(String AgentPrice) {
            this.AgentPrice = AgentPrice;
        }

        public String getBrandId() {
            return BrandId;
        }

        public void setBrandId(String BrandId) {
            this.BrandId = BrandId;
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
