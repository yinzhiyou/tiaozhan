package com.example.android.promoter.Entity;

import java.util.List;

public class HomeTanEntity {

    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"PublicStatus":8,"SportMode":"娱乐模式","sportName":"羽毛球","sportTypeName":"单打","week":"星期三","startDays":"2019-07-31","startTimes":"18:00","nickname":"王大大","imgURL":"uploads/HeaderImgs/2019-08-06/20190806151800396.png","sitename":"2北京甲乙电子商务","uuid":"ac5d427c-b6b1-acc3-d9a3-3b8b02bd591b","status":1}]
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
         * PublicStatus : 8
         * SportMode : 娱乐模式
         * sportName : 羽毛球
         * sportTypeName : 单打
         * week : 星期三
         * startDays : 2019-07-31
         * startTimes : 18:00
         * nickname : 王大大
         * imgURL : uploads/HeaderImgs/2019-08-06/20190806151800396.png
         * sitename : 2北京甲乙电子商务
         * uuid : ac5d427c-b6b1-acc3-d9a3-3b8b02bd591b
         * status : 1
         */

        private int PublicStatus;
        private String SportMode;
        private String sportName;
        private String sportTypeName;
        private String week;
        private String startDays;
        private String startTimes;
        private String nickname;
        private String imgURL;
        private String sitename;
        private String uuid;

        private int  sex;
        private String endTimes;
        private String  nowLevel;
        private int status;

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getEndTimes() {
            return endTimes;
        }

        public void setEndTimes(String endTimes) {
            this.endTimes = endTimes;
        }

        public String getNowLevel() {
            return nowLevel;
        }

        public void setNowLevel(String nowLevel) {
            this.nowLevel = nowLevel;
        }

        public int getPublicStatus() {
            return PublicStatus;
        }

        public void setPublicStatus(int PublicStatus) {
            this.PublicStatus = PublicStatus;
        }

        public String getSportMode() {
            return SportMode;
        }

        public void setSportMode(String SportMode) {
            this.SportMode = SportMode;
        }

        public String getSportName() {
            return sportName;
        }

        public void setSportName(String sportName) {
            this.sportName = sportName;
        }

        public String getSportTypeName() {
            return sportTypeName;
        }

        public void setSportTypeName(String sportTypeName) {
            this.sportTypeName = sportTypeName;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getStartDays() {
            return startDays;
        }

        public void setStartDays(String startDays) {
            this.startDays = startDays;
        }

        public String getStartTimes() {
            return startTimes;
        }

        public void setStartTimes(String startTimes) {
            this.startTimes = startTimes;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getSitename() {
            return sitename;
        }

        public void setSitename(String sitename) {
            this.sitename = sitename;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
