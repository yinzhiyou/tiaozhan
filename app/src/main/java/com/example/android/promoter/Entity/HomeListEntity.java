package com.example.android.promoter.Entity;

import java.util.List;

public class HomeListEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"activeLst":[{"uuid":"d4d1f57c-8e41-8c03-e30a-a7f3c8f70600","SportMode":1,"SportId":1,"SportName":"羽毛球","SportType":4,"SportTypeName":"单打","startDays":"2019-11-19","startTimes":"17:00","endDays":"2019-11-19","endTimes":"17:30","PaySiteMoneyType":1,"MoneyPerhour":0,"Tips":0,"PublicStatus":1,"address":"山西省体育健身馆","nickname":"甲乙~刘志伟","sex":0,"imgURL":"uploads/HeaderImgs/2019-06-14/20190614134509968.png","range":"0.02km","nowLevel":"Lv2","lockNumber":0,"week":"星期二"}]}
     * other :
     */

    private int code;
    private String msg;
    private DataBean data;
    private String other;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * nowPage : 1
         * activeLst : [{"uuid":"d4d1f57c-8e41-8c03-e30a-a7f3c8f70600","SportMode":1,"SportId":1,"SportName":"羽毛球","SportType":4,"SportTypeName":"单打","startDays":"2019-11-19","startTimes":"17:00","endDays":"2019-11-19","endTimes":"17:30","PaySiteMoneyType":1,"MoneyPerhour":0,"Tips":0,"PublicStatus":1,"address":"山西省体育健身馆","nickname":"甲乙~刘志伟","sex":0,"imgURL":"uploads/HeaderImgs/2019-06-14/20190614134509968.png","range":"0.02km","nowLevel":"Lv2","lockNumber":0,"week":"星期二"}]
         */

        private int nowPage;
        private List<ActiveLstBean> activeLst;

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public List<ActiveLstBean> getActiveLst() {
            return activeLst;
        }

        public void setActiveLst(List<ActiveLstBean> activeLst) {
            this.activeLst = activeLst;
        }

        public static class ActiveLstBean {
            /**
             * uuid : d4d1f57c-8e41-8c03-e30a-a7f3c8f70600
             * SportMode : 1
             * SportId : 1
             * SportName : 羽毛球
             * SportType : 4
             * SportTypeName : 单打
             * startDays : 2019-11-19
             * startTimes : 17:00
             * endDays : 2019-11-19
             * endTimes : 17:30
             * PaySiteMoneyType : 1
             * MoneyPerhour : 0
             * Tips : 0
             * PublicStatus : 1
             * address : 山西省体育健身馆
             * nickname : 甲乙~刘志伟
             * sex : 0
             * imgURL : uploads/HeaderImgs/2019-06-14/20190614134509968.png
             * range : 0.02km
             * nowLevel : Lv2
             * lockNumber : 0
             * week : 星期二
             */

            private String uuid;
            private int SportMode;
            private int SportId;
            private String SportName;
            private int SportType;
            private String SportTypeName;
            private String startDays;
            private String startTimes;
            private String endDays;
            private String endTimes;
            private int PaySiteMoneyType;
            private int MoneyPerhour;
            private int Tips;
            private int PublicStatus;
            private String address;
            private String nickname;
            private int sex;
            private String imgURL;
            private String range;
            private String nowLevel;
            private int lockNumber;
            private String week;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public int getSportMode() {
                return SportMode;
            }

            public void setSportMode(int SportMode) {
                this.SportMode = SportMode;
            }

            public int getSportId() {
                return SportId;
            }

            public void setSportId(int SportId) {
                this.SportId = SportId;
            }

            public String getSportName() {
                return SportName;
            }

            public void setSportName(String SportName) {
                this.SportName = SportName;
            }

            public int getSportType() {
                return SportType;
            }

            public void setSportType(int SportType) {
                this.SportType = SportType;
            }

            public String getSportTypeName() {
                return SportTypeName;
            }

            public void setSportTypeName(String SportTypeName) {
                this.SportTypeName = SportTypeName;
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

            public String getEndDays() {
                return endDays;
            }

            public void setEndDays(String endDays) {
                this.endDays = endDays;
            }

            public String getEndTimes() {
                return endTimes;
            }

            public void setEndTimes(String endTimes) {
                this.endTimes = endTimes;
            }

            public int getPaySiteMoneyType() {
                return PaySiteMoneyType;
            }

            public void setPaySiteMoneyType(int PaySiteMoneyType) {
                this.PaySiteMoneyType = PaySiteMoneyType;
            }

            public int getMoneyPerhour() {
                return MoneyPerhour;
            }

            public void setMoneyPerhour(int MoneyPerhour) {
                this.MoneyPerhour = MoneyPerhour;
            }

            public int getTips() {
                return Tips;
            }

            public void setTips(int Tips) {
                this.Tips = Tips;
            }

            public int getPublicStatus() {
                return PublicStatus;
            }

            public void setPublicStatus(int PublicStatus) {
                this.PublicStatus = PublicStatus;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public String getNowLevel() {
                return nowLevel;
            }

            public void setNowLevel(String nowLevel) {
                this.nowLevel = nowLevel;
            }

            public int getLockNumber() {
                return lockNumber;
            }

            public void setLockNumber(int lockNumber) {
                this.lockNumber = lockNumber;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }
        }
    }
}
