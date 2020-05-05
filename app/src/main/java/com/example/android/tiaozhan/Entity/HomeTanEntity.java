package com.example.android.tiaozhan.Entity;

import java.util.List;

public class HomeTanEntity {

    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"PublicStatus":6,"SportMode":"我找陪练","sportName":"羽毛球","sportID":1,"nowLevel":"Lv6","sportTypeName":"单打","week":"星期三","startDays":"2020-03-11","endDays":"2020-03-11","startTimes":"16:00","endTimes":"17:00","nickname":"测试31","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105309934.png","sitename":"东三旗体育中心","uuid":"ec8d887d-7a3f-cedd-6b52-5a87049d5c7a","userid":"5058b7b5-f89a-9eff-f260-2941a2c9f638","status":4,"sex":0,"comment":""},{"PublicStatus":6,"SportMode":"我是陪练","sportName":"羽毛球","sportID":1,"nowLevel":"Lv8","sportTypeName":"单打","week":"星期二","startDays":"2020-03-10","endDays":"2020-03-10","startTimes":"11:00","endTimes":"12:00","nickname":"测试33","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105450760.png","sitename":"东三旗体育中心","uuid":"36b619e1-1b2f-7239-91d1-5e9dad77c8c1","userid":"55366cba-80de-ef04-8a69-fb431eefa726","status":4,"sex":0,"comment":""},{"PublicStatus":4,"SportMode":"竞技模式","sportName":"羽毛球","sportID":1,"nowLevel":"Lv6","sportTypeName":"单打","week":"星期二","startDays":"2020-03-03","endDays":"2020-03-03","startTimes":"13:00","endTimes":"14:00","nickname":"测试31","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105309934.png","sitename":"东三旗体育中心","uuid":"363f36f5-59c4-2ea0-6caa-ac5bd4e59f74","userid":"5058b7b5-f89a-9eff-f260-2941a2c9f638","status":0,"sex":0,"comment":""},{"PublicStatus":9,"SportMode":"竞技模式","sportName":"羽毛球","sportID":1,"nowLevel":"Lv8","sportTypeName":"单打","week":"星期二","startDays":"2020-02-11","endDays":"2020-02-11","startTimes":"15:00","endTimes":"16:00","nickname":"测试33","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105450760.png","sitename":"北京甲乙电子","uuid":"f278c3d7-843b-be94-50f4-71228430980e","userid":"55366cba-80de-ef04-8a69-fb431eefa726","status":3,"sex":0,"comment":"推广员:\"场馆未预留场地\"情况不属实,请确认。"},{"PublicStatus":6,"SportMode":"竞技模式","sportName":"羽毛球","sportID":1,"nowLevel":"Lv2","sportTypeName":"双打","week":"星期二","startDays":"2020-02-11","endDays":"2020-02-11","startTimes":"14:30","endTimes":"15:00","nickname":"甲乙~刘志伟","imgURL":"uploads/HeaderImgs/2020-02-21/20200221131415445.png","sitename":"山西省体育健身馆","uuid":"d5eaeaa2-3c6f-6d4b-efe0-1587ebf910b3","userid":"bdc1bf13-7f0a-e03c-2e17-68ae966d9557","status":4,"sex":0,"comment":""}]
     * other :
     */

    private int code;
    private String msg;
    private String other;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * PublicStatus : 6
         * SportMode : 我找陪练
         * sportName : 羽毛球
         * sportID : 1
         * nowLevel : Lv6
         * sportTypeName : 单打
         * week : 星期三
         * startDays : 2020-03-11
         * endDays : 2020-03-11
         * startTimes : 16:00
         * endTimes : 17:00
         * nickname : 测试31
         * imgURL : uploads/HeaderImgs/2019-09-17/20190917105309934.png
         * sitename : 东三旗体育中心
         * uuid : ec8d887d-7a3f-cedd-6b52-5a87049d5c7a
         * userid : 5058b7b5-f89a-9eff-f260-2941a2c9f638
         * status : 4
         * sex : 0
         * comment :
         */

        private int PublicStatus;
        private String SportMode;
        private String sportName;
        private int sportID;
        private String nowLevel;
        private String sportTypeName;
        private String week;
        private String startDays;
        private String endDays;
        private String startTimes;
        private String endTimes;
        private String nickname;
        private String imgURL;
        private String sitename;
        private String uuid;
        private String userid;
        private String status;
        private int sex;
        private String comment;

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

        public int getSportID() {
            return sportID;
        }

        public void setSportID(int sportID) {
            this.sportID = sportID;
        }

        public String getNowLevel() {
            return nowLevel;
        }

        public void setNowLevel(String nowLevel) {
            this.nowLevel = nowLevel;
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

        public String getEndDays() {
            return endDays;
        }

        public void setEndDays(String endDays) {
            this.endDays = endDays;
        }

        public String getStartTimes() {
            return startTimes;
        }

        public void setStartTimes(String startTimes) {
            this.startTimes = startTimes;
        }

        public String getEndTimes() {
            return endTimes;
        }

        public void setEndTimes(String endTimes) {
            this.endTimes = endTimes;
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

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
