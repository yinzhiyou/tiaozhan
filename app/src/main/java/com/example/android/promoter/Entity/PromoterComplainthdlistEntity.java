package com.example.android.promoter.Entity;

import java.util.List;

public class PromoterComplainthdlistEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"sitename":"甲乙飞飞飞","publicUUid":"6f3f807f-26b0-95c6-25d0-1876f2ed6f70","playerUUID":"8adec8a6-0cd1-909b-6910-c08eea7a25a4","nickname":"测试41","sex":0,"imgURL":"uploads/HeaderImgs/2019-09-17/20190917104402483.png","SportMode":"娱乐模式","SportId":"羽毛球","SportType":"单打","SiteMoney":"50.00","RoyaltyMoney":2,"PublicStatus":3,"vip":"Lv10","year":"2019-12-19","week":"星期五","time":"10:30-11:00","status":0,"reserve":1}]
     * other : {"status0":0,"status1":0,"status2":0}
     */

    private int code;
    private String msg;
    private OtherBean other;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OtherBean {
        /**
         * status0 : 0
         * status1 : 0
         * status2 : 0
         */

        private int status0;
        private int status1;
        private int status2;

        public int getStatus0() {
            return status0;
        }

        public void setStatus0(int status0) {
            this.status0 = status0;
        }

        public int getStatus1() {
            return status1;
        }

        public void setStatus1(int status1) {
            this.status1 = status1;
        }

        public int getStatus2() {
            return status2;
        }

        public void setStatus2(int status2) {
            this.status2 = status2;
        }
    }

    public static class DataBean {
        /**
         * sitename : 甲乙飞飞飞
         * publicUUid : 6f3f807f-26b0-95c6-25d0-1876f2ed6f70
         * playerUUID : 8adec8a6-0cd1-909b-6910-c08eea7a25a4
         * nickname : 测试41
         * sex : 0
         * imgURL : uploads/HeaderImgs/2019-09-17/20190917104402483.png
         * SportMode : 娱乐模式
         * SportId : 羽毛球
         * SportType : 单打
         * SiteMoney : 50.00
         * RoyaltyMoney : 2
         * PublicStatus : 3
         * vip : Lv10
         * year : 2019-12-19
         * week : 星期五
         * time : 10:30-11:00
         * status : 0
         * reserve : 1
         */

        private String sitename;
        private String publicUUid;
        private String playerUUID;
        private String nickname;
        private int sex;
        private String imgURL;
        private String SportMode;
        private String SportId;
        private String SportType;
        private String SiteMoney;
        private int RoyaltyMoney;
        private int PublicStatus;
        private String vip;
        private String year;
        private String week;
        private String time;
        private int status;
        private int reserve;

        public String getSitename() {
            return sitename;
        }

        public void setSitename(String sitename) {
            this.sitename = sitename;
        }

        public String getPublicUUid() {
            return publicUUid;
        }

        public void setPublicUUid(String publicUUid) {
            this.publicUUid = publicUUid;
        }

        public String getPlayerUUID() {
            return playerUUID;
        }

        public void setPlayerUUID(String playerUUID) {
            this.playerUUID = playerUUID;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getSportMode() {
            return SportMode;
        }

        public void setSportMode(String SportMode) {
            this.SportMode = SportMode;
        }

        public String getSportId() {
            return SportId;
        }

        public void setSportId(String SportId) {
            this.SportId = SportId;
        }

        public String getSportType() {
            return SportType;
        }

        public void setSportType(String SportType) {
            this.SportType = SportType;
        }

        public String getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(String SiteMoney) {
            this.SiteMoney = SiteMoney;
        }

        public int getRoyaltyMoney() {
            return RoyaltyMoney;
        }

        public void setRoyaltyMoney(int RoyaltyMoney) {
            this.RoyaltyMoney = RoyaltyMoney;
        }

        public int getPublicStatus() {
            return PublicStatus;
        }

        public void setPublicStatus(int PublicStatus) {
            this.PublicStatus = PublicStatus;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getReserve() {
            return reserve;
        }

        public void setReserve(int reserve) {
            this.reserve = reserve;
        }
    }
}
