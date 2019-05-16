package com.android.hyb.bean.response;

import com.android.hyb.base.BaseResponse;

import java.util.List;

public class GetPageGroupsResponse extends BaseResponse {
    private List<MemberBean> data;

    public List<MemberBean> getData() {
        return data;
    }

    public void setData(List<MemberBean> data) {
        this.data = data;
    }

    public static class MemberBean{


        /**
         * Id : 5
         * OpenId : 4
         * GroupId : 1
         * DirectGroups : 1
         * Groups : 10
         * ParentId : 6
         * AccessTime : /Date(1557849600000)/
         * AccessTimeString : 2019-05-15 00:00:00
         * GroupName : null
         * SmallMarkets : 0
         */

        private int Id;
        private String OpenId;
        private int GroupId;
        private int DirectGroups;
        private int Groups;
        private int ParentId;
        private String AccessTime;
        private String AccessTimeString;
        private Object GroupName;
        private int SmallMarkets;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getOpenId() {
            return OpenId;
        }

        public void setOpenId(String OpenId) {
            this.OpenId = OpenId;
        }

        public int getGroupId() {
            return GroupId;
        }

        public void setGroupId(int GroupId) {
            this.GroupId = GroupId;
        }

        public int getDirectGroups() {
            return DirectGroups;
        }

        public void setDirectGroups(int DirectGroups) {
            this.DirectGroups = DirectGroups;
        }

        public int getGroups() {
            return Groups;
        }

        public void setGroups(int Groups) {
            this.Groups = Groups;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getAccessTime() {
            return AccessTime;
        }

        public void setAccessTime(String AccessTime) {
            this.AccessTime = AccessTime;
        }

        public String getAccessTimeString() {
            return AccessTimeString;
        }

        public void setAccessTimeString(String AccessTimeString) {
            this.AccessTimeString = AccessTimeString;
        }

        public Object getGroupName() {
            return GroupName;
        }

        public void setGroupName(Object GroupName) {
            this.GroupName = GroupName;
        }

        public int getSmallMarkets() {
            return SmallMarkets;
        }

        public void setSmallMarkets(int SmallMarkets) {
            this.SmallMarkets = SmallMarkets;
        }
    }
}
