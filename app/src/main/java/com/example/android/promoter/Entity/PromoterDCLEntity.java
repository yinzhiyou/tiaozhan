package com.example.android.promoter.Entity;

import java.util.List;

public class PromoterDCLEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"sitename":"2北京甲乙电子商务","publicUUid":"90b4c3ff-fd04-21df-9d21-5aef3b6006d6","playerUUID":"67004120-aa15-3ebb-d159-bda39816fe8a","nickname":"羽球菜鸟","sex":0,"imgURL":"uploads/HeaderImgs/2019-06-30/20190630101508136.png","SportMode":"娱乐模式","SportId":"羽毛球","SportType":"双打","SiteMoney":30,"RoyaltyMoney":0,"PublicStatus":7,"vip":"Lv6","year":"2019-06-28","week":"星期五","time":"23:00-23:30"},{"sitename":"2北京甲乙电子商务","publicUUid":"1a88f6ac-79e9-f5f8-6d21-ad47268eb8af","playerUUID":"bcb5e880-96b4-3872-d577-5ae560e3e20f","nickname":"hello kittiy","sex":0,"imgURL":"uploads/HeaderImgs/2019-05-14/20190514143048459.png","SportMode":"竞技模式","SportId":"乒乓球","SportType":"双打","SiteMoney":4,"RoyaltyMoney":0,"PublicStatus":6,"vip":"Lv3","year":"2019-04-28","week":"星期五","time":"18:30-19:00"},{"sitename":"1北京甲乙电子商务","publicUUid":"89c0ce5c-f8af-1aa3-e6a7-07b7bf5d01f4","playerUUID":"c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba","nickname":"一颗酸柠檬","sex":0,"imgURL":"uploads/HeaderImgs/2019-07-02/20190702165639719.png","SportMode":"竞技模式","SportId":"网球","SportType":"单打","SiteMoney":1,"RoyaltyMoney":0,"PublicStatus":6,"vip":"Lv1","year":"2019-04-28","week":"星期五","time":"11:00-11:30"},{"sitename":"1北京甲乙电子商务","publicUUid":"f0030c30-a53d-bcf7-ce1e-0b5f09e69b57","playerUUID":"c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba","nickname":"一颗酸柠檬","sex":0,"imgURL":"uploads/HeaderImgs/2019-07-02/20190702165639719.png","SportMode":"竞技模式","SportId":"台球","SportType":"中式黑八","SiteMoney":1,"RoyaltyMoney":0,"PublicStatus":6,"vip":"Lv1","year":"2019-04-17","week":"星期五","time":"11:30-12:00"}]
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sitename : 2北京甲乙电子商务
         * publicUUid : 90b4c3ff-fd04-21df-9d21-5aef3b6006d6
         * playerUUID : 67004120-aa15-3ebb-d159-bda39816fe8a
         * nickname : 羽球菜鸟
         * sex : 0
         * imgURL : uploads/HeaderImgs/2019-06-30/20190630101508136.png
         * SportMode : 娱乐模式
         * SportId : 羽毛球
         * SportType : 双打
         * SiteMoney : 30
         * RoyaltyMoney : 0
         * PublicStatus : 7
         * vip : Lv6
         * year : 2019-06-28
         * week : 星期五
         * time : 23:00-23:30
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
        private double SiteMoney;
        private int RoyaltyMoney;
        private int PublicStatus;
        private String vip;
        private String year;
        private String week;
        private String time;

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

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

        public double getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(double SiteMoney) {
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
    }
}
