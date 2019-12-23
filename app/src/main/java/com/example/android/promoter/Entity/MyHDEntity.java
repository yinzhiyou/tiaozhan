package com.example.android.promoter.Entity;

import java.util.List;

public class MyHDEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":2,"publicLst":[{"uuid":"02d69a6c-5ac0-8937-1062-513e7b5039bf","SportMode":1,"SportId":1,"SportType":4,"StartTime":"2019-01-04 22:00:00","PlayTime":0.5,"siteUid":"00052e666d12f06cafee7ad6","PaySiteMoneyType":1,"MoneyPerhour":0,"Tips":0,"PublicStatus":6,"GroupChartUUID":"0","FinishedTime":"2019-01-04 22:30:00","sportName":"羽毛球","sportTypeName":"单打","needPlayerNumber":2,"week":"星期五","startDays":"2019-01-04","startTimes":"22:00","endDays":"2019-01-04","endTimes":"22:30","sitesName":"1北京甲乙电子商务(华远11层店)","sitesAddress":"国贸","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png","sex":0,"IsPublisher":1,"techcoins":7.5,"userLevel":"Lv1","singUpCount":2,"lackCount":0,"isSignIn":0,"isConfirmResult":0,"isComment":0},{"uuid":"ffbd7245-0382-95fd-7ffb-57515faa7a2f","SportMode":1,"SportId":1,"SportType":4,"StartTime":"2019-01-04 14:00:00","PlayTime":1,"siteUid":"00052e666d12f06cafee7ad6","PaySiteMoneyType":1,"MoneyPerhour":0,"Tips":0,"PublicStatus":7,"GroupChartUUID":"0","FinishedTime":"2019-01-04 15:00:00","sportName":"羽毛球","sportTypeName":"单打","needPlayerNumber":2,"week":"星期五","startDays":"2019-01-04","startTimes":"14:00","endDays":"2019-01-04","endTimes":"15:00","sitesName":"1北京甲乙电子商务(华远11层店)","sitesAddress":"国贸","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png","sex":0,"IsPublisher":1,"techcoins":7.5,"userLevel":"Lv1","singUpCount":1,"lackCount":1,"isSignIn":0,"isConfirmResult":0,"isComment":0}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public static class DataBean {
        /**
         * nowPage : 1
         * total : 2
         * publicLst : [{"uuid":"02d69a6c-5ac0-8937-1062-513e7b5039bf","SportMode":1,"SportId":1,"SportType":4,"StartTime":"2019-01-04 22:00:00","PlayTime":0.5,"siteUid":"00052e666d12f06cafee7ad6","PaySiteMoneyType":1,"MoneyPerhour":0,"Tips":0,"PublicStatus":6,"GroupChartUUID":"0","FinishedTime":"2019-01-04 22:30:00","sportName":"羽毛球","sportTypeName":"单打","needPlayerNumber":2,"week":"星期五","startDays":"2019-01-04","startTimes":"22:00","endDays":"2019-01-04","endTimes":"22:30","sitesName":"1北京甲乙电子商务(华远11层店)","sitesAddress":"国贸","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png","sex":0,"IsPublisher":1,"techcoins":7.5,"userLevel":"Lv1","singUpCount":2,"lackCount":0,"isSignIn":0,"isConfirmResult":0,"isComment":0},{"uuid":"ffbd7245-0382-95fd-7ffb-57515faa7a2f","SportMode":1,"SportId":1,"SportType":4,"StartTime":"2019-01-04 14:00:00","PlayTime":1,"siteUid":"00052e666d12f06cafee7ad6","PaySiteMoneyType":1,"MoneyPerhour":0,"Tips":0,"PublicStatus":7,"GroupChartUUID":"0","FinishedTime":"2019-01-04 15:00:00","sportName":"羽毛球","sportTypeName":"单打","needPlayerNumber":2,"week":"星期五","startDays":"2019-01-04","startTimes":"14:00","endDays":"2019-01-04","endTimes":"15:00","sitesName":"1北京甲乙电子商务(华远11层店)","sitesAddress":"国贸","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png","sex":0,"IsPublisher":1,"techcoins":7.5,"userLevel":"Lv1","singUpCount":1,"lackCount":1,"isSignIn":0,"isConfirmResult":0,"isComment":0}]
         */

        private int nowPage;
        private int total;
        private List<PublicLstBean> publicLst;

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<PublicLstBean> getPublicLst() {
            return publicLst;
        }

        public void setPublicLst(List<PublicLstBean> publicLst) {
            this.publicLst = publicLst;
        }

        public static class PublicLstBean {
            /**
             * uuid : 02d69a6c-5ac0-8937-1062-513e7b5039bf
             * SportMode : 1
             * SportId : 1
             * SportType : 4
             * StartTime : 2019-01-04 22:00:00
             * PlayTime : 0.5
             * siteUid : 00052e666d12f06cafee7ad6
             * PaySiteMoneyType : 1
             * MoneyPerhour : 0
             * Tips : 0
             * PublicStatus : 6
             * GroupChartUUID : 0
             * FinishedTime : 2019-01-04 22:30:00
             * sportName : 羽毛球
             * sportTypeName : 单打
             * needPlayerNumber : 2
             * week : 星期五
             * startDays : 2019-01-04
             * startTimes : 22:00
             * endDays : 2019-01-04
             * endTimes : 22:30
             * sitesName : 1北京甲乙电子商务(华远11层店)
             * sitesAddress : 国贸
             * nickname : 王二二
             * imgURL : uploads/HeaderImgs/2019-01-03/20190103180853517.png
             * sex : 0
             * IsPublisher : 1
             * techcoins : 7.5
             * userLevel : Lv1
             * singUpCount : 2
             * lackCount : 0
             * isSignIn : 0
             * isConfirmResult : 0
             * isComment : 0
             */

            private String uuid;
            private int SportMode;
            private int SportId;
            private int SportType;
            private String StartTime;
            private double PlayTime;
            private String siteUid;
            private int PaySiteMoneyType;
            private double MoneyPerhour;
            private double Tips;
            private int PublicStatus;
            private String GroupChartUUID;
            private String FinishedTime;
            private String sportName;
            private String sportTypeName;
            private int needPlayerNumber;
            private String week;
            private String startDays;
            private String startTimes;
            private String endDays;
            private String endTimes;
            private String sitesName;
            private String sitesAddress;
            private String nickname;
            private String imgURL;
            private int sex;
            private int IsPublisher;
            private double techcoins;
            private String userLevel;
            private int singUpCount;
            private int lackCount;
            private int isSignIn;
            private int isConfirmResult;
            private int isComment;
            private int isConfirmOver;

            private int isQuit;
            private int isQuitInGame;

            private int Complaint;


            private int Comment;

            private String reserve;

            public String getReserve() {
                return reserve;
            }

            public void setReserve(String reserve) {
                this.reserve = reserve;
            }

            public int getComment() {
                return Comment;
            }

            public void setComment(int comment) {
                Comment = comment;
            }

            public int getComplaint() {
                return Complaint;
            }

            public void setComplaint(int complaint) {
                Complaint = complaint;
            }

            public int getIsQuit() {
                return isQuit;
            }

            public void setIsQuit(int isQuit) {
                this.isQuit = isQuit;
            }

            public int getIsQuitInGame() {
                return isQuitInGame;
            }

            public void setIsQuitInGame(int isQuitInGame) {
                this.isQuitInGame = isQuitInGame;
            }


            public int getIsConfirmOver() {
                return isConfirmOver;
            }

            public void setIsConfirmOver(int isConfirmOver) {
                this.isConfirmOver = isConfirmOver;
            }


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

            public int getSportType() {
                return SportType;
            }

            public void setSportType(int SportType) {
                this.SportType = SportType;
            }

            public String getStartTime() {
                return StartTime;
            }

            public void setStartTime(String StartTime) {
                this.StartTime = StartTime;
            }

            public double getPlayTime() {
                return PlayTime;
            }

            public void setPlayTime(double PlayTime) {
                this.PlayTime = PlayTime;
            }

            public String getSiteUid() {
                return siteUid;
            }

            public void setSiteUid(String siteUid) {
                this.siteUid = siteUid;
            }

            public int getPaySiteMoneyType() {
                return PaySiteMoneyType;
            }

            public void setPaySiteMoneyType(int PaySiteMoneyType) {
                this.PaySiteMoneyType = PaySiteMoneyType;
            }

            public double getMoneyPerhour() {
                return MoneyPerhour;
            }

            public void setMoneyPerhour(double MoneyPerhour) {
                this.MoneyPerhour = MoneyPerhour;
            }

            public double getTips() {
                return Tips;
            }

            public void setTips(double Tips) {
                this.Tips = Tips;
            }

            public int getPublicStatus() {
                return PublicStatus;
            }

            public void setPublicStatus(int PublicStatus) {
                this.PublicStatus = PublicStatus;
            }

            public String getGroupChartUUID() {
                return GroupChartUUID;
            }

            public void setGroupChartUUID(String GroupChartUUID) {
                this.GroupChartUUID = GroupChartUUID;
            }

            public String getFinishedTime() {
                return FinishedTime;
            }

            public void setFinishedTime(String FinishedTime) {
                this.FinishedTime = FinishedTime;
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

            public int getNeedPlayerNumber() {
                return needPlayerNumber;
            }

            public void setNeedPlayerNumber(int needPlayerNumber) {
                this.needPlayerNumber = needPlayerNumber;
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

            public String getSitesName() {
                return sitesName;
            }

            public void setSitesName(String sitesName) {
                this.sitesName = sitesName;
            }

            public String getSitesAddress() {
                return sitesAddress;
            }

            public void setSitesAddress(String sitesAddress) {
                this.sitesAddress = sitesAddress;
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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getIsPublisher() {
                return IsPublisher;
            }

            public void setIsPublisher(int IsPublisher) {
                this.IsPublisher = IsPublisher;
            }

            public double getTechcoins() {
                return techcoins;
            }

            public void setTechcoins(double techcoins) {
                this.techcoins = techcoins;
            }

            public String getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(String userLevel) {
                this.userLevel = userLevel;
            }

            public int getSingUpCount() {
                return singUpCount;
            }

            public void setSingUpCount(int singUpCount) {
                this.singUpCount = singUpCount;
            }

            public int getLackCount() {
                return lackCount;
            }

            public void setLackCount(int lackCount) {
                this.lackCount = lackCount;
            }

            public int getIsSignIn() {
                return isSignIn;
            }

            public void setIsSignIn(int isSignIn) {
                this.isSignIn = isSignIn;
            }

            public int getIsConfirmResult() {
                return isConfirmResult;
            }

            public void setIsConfirmResult(int isConfirmResult) {
                this.isConfirmResult = isConfirmResult;
            }

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }
        }
    }
}
