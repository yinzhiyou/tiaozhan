package com.example.android.tiaozhan.Entity;

public class QianbaoZhifuEntity  {


    /**
     * code : 2000
     * msg : 活动创建成功
     * data : {"uuid":"1aa55720-3272-9eb2-4c6b-cd06fc88f2dc","orderId":"011901224754","SportMode":"1","SportId":"1","SportType":"4","siteUid":"00052e666d12f06cafee7ad6","StartTime":"2019-01-22 19:00","PlayTime":"0.5","TeamMateSex":"2","OpponentsSex":"2","TeamMateLevelMin":"1","TeamMateLevelMax":"10","OpponentsLevelMin":"1","OpponentsLevelMax":"10","SiteMoney":"0.2","PaySiteMoneyType":"1","MoneyPerhour":0,"Tips":"1","Comments":null,"PublicStatus":1,"GroupChartUUID":0,"FinishedTime":"2019-01-22 19:30:00","JoinEndTime":"2019-01-22 18:30:00","paied":0,"CreateTime":"2019-01-22 14:49:17"}
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
         * uuid : 1aa55720-3272-9eb2-4c6b-cd06fc88f2dc
         * orderId : 011901224754
         * SportMode : 1
         * SportId : 1
         * SportType : 4
         * siteUid : 00052e666d12f06cafee7ad6
         * StartTime : 2019-01-22 19:00
         * PlayTime : 0.5
         * TeamMateSex : 2
         * OpponentsSex : 2
         * TeamMateLevelMin : 1
         * TeamMateLevelMax : 10
         * OpponentsLevelMin : 1
         * OpponentsLevelMax : 10
         * SiteMoney : 0.2
         * PaySiteMoneyType : 1
         * MoneyPerhour : 0
         * Tips : 1
         * Comments : null
         * PublicStatus : 1
         * GroupChartUUID : 0
         * FinishedTime : 2019-01-22 19:30:00
         * JoinEndTime : 2019-01-22 18:30:00
         * paied : 0
         * CreateTime : 2019-01-22 14:49:17
         */

        private String uuid;
        private String orderId;
        private String SportMode;
        private String SportId;
        private String SportType;
        private String siteUid;
        private String StartTime;
        private String PlayTime;
        private String TeamMateSex;
        private String OpponentsSex;
        private String TeamMateLevelMin;
        private String TeamMateLevelMax;
        private String OpponentsLevelMin;
        private String OpponentsLevelMax;
        private String SiteMoney;
        private String PaySiteMoneyType;
        private String MoneyPerhour;
        private String Tips;
        private Object Comments;
        private int PublicStatus;
        private String GroupChartUUID;
        private String FinishedTime;
        private String JoinEndTime;
        private int paied;
        private String CreateTime;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getSiteUid() {
            return siteUid;
        }

        public void setSiteUid(String siteUid) {
            this.siteUid = siteUid;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getPlayTime() {
            return PlayTime;
        }

        public void setPlayTime(String PlayTime) {
            this.PlayTime = PlayTime;
        }

        public String getTeamMateSex() {
            return TeamMateSex;
        }

        public void setTeamMateSex(String TeamMateSex) {
            this.TeamMateSex = TeamMateSex;
        }

        public String getOpponentsSex() {
            return OpponentsSex;
        }

        public void setOpponentsSex(String OpponentsSex) {
            this.OpponentsSex = OpponentsSex;
        }

        public String getTeamMateLevelMin() {
            return TeamMateLevelMin;
        }

        public void setTeamMateLevelMin(String TeamMateLevelMin) {
            this.TeamMateLevelMin = TeamMateLevelMin;
        }

        public String getTeamMateLevelMax() {
            return TeamMateLevelMax;
        }

        public void setTeamMateLevelMax(String TeamMateLevelMax) {
            this.TeamMateLevelMax = TeamMateLevelMax;
        }

        public String getOpponentsLevelMin() {
            return OpponentsLevelMin;
        }

        public void setOpponentsLevelMin(String OpponentsLevelMin) {
            this.OpponentsLevelMin = OpponentsLevelMin;
        }

        public String getOpponentsLevelMax() {
            return OpponentsLevelMax;
        }

        public void setOpponentsLevelMax(String OpponentsLevelMax) {
            this.OpponentsLevelMax = OpponentsLevelMax;
        }

        public String getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(String SiteMoney) {
            this.SiteMoney = SiteMoney;
        }

        public String getPaySiteMoneyType() {
            return PaySiteMoneyType;
        }

        public void setPaySiteMoneyType(String PaySiteMoneyType) {
            this.PaySiteMoneyType = PaySiteMoneyType;
        }

        public String getMoneyPerhour() {
            return MoneyPerhour;
        }

        public void setMoneyPerhour(String MoneyPerhour) {
            this.MoneyPerhour = MoneyPerhour;
        }

        public String getTips() {
            return Tips;
        }

        public void setTips(String Tips) {
            this.Tips = Tips;
        }

        public Object getComments() {
            return Comments;
        }

        public void setComments(Object Comments) {
            this.Comments = Comments;
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

        public String getJoinEndTime() {
            return JoinEndTime;
        }

        public void setJoinEndTime(String JoinEndTime) {
            this.JoinEndTime = JoinEndTime;
        }

        public int getPaied() {
            return paied;
        }

        public void setPaied(int paied) {
            this.paied = paied;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
