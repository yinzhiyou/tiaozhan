package com.example.android.tiaozhan.Entity;

public class ReservePartnerEntity {

    /**
     * code : 2000
     * msg : 活动创建成功
     * data : {"uuid":"9f5d54b0-9eb1-adaa-ca22-9236474e43bf","orderId":"011912181611164247","SportMode":1,"SportId":"1","SportType":"4","siteUid":"da470d6d-68d1-a880-0125-f55d8951d478","StartTime":"2019-12-18 19:30:00","PlayTime":"0.5","SiteMoney":"100","PaySiteMoneyType":1,"Comments":"疯了","PublicStatus":1,"FinishedTime":"2019-12-18 20:00:00","JoinEndTime":"2019-12-18 19:00:00","venueid":"1","reserve":1,"paied":0,"CreateTime":"2019-12-18 16:11:16"}
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
         * uuid : 9f5d54b0-9eb1-adaa-ca22-9236474e43bf
         * orderId : 011912181611164247
         * SportMode : 1
         * SportId : 1
         * SportType : 4
         * siteUid : da470d6d-68d1-a880-0125-f55d8951d478
         * StartTime : 2019-12-18 19:30:00
         * PlayTime : 0.5
         * SiteMoney : 100
         * PaySiteMoneyType : 1
         * Comments : 疯了
         * PublicStatus : 1
         * FinishedTime : 2019-12-18 20:00:00
         * JoinEndTime : 2019-12-18 19:00:00
         * venueid : 1
         * reserve : 1
         * paied : 0
         * CreateTime : 2019-12-18 16:11:16
         */

        private String uuid;
        private String orderId;
        private int SportMode;
        private String SportId;
        private String SportType;
        private String siteUid;
        private String StartTime;
        private String PlayTime;
        private String SiteMoney;
        private int PaySiteMoneyType;
        private String Comments;
        private int PublicStatus;
        private String FinishedTime;
        private String JoinEndTime;
        private String venueid;
        private int reserve;
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

        public int getSportMode() {
            return SportMode;
        }

        public void setSportMode(int SportMode) {
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

        public String getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(String SiteMoney) {
            this.SiteMoney = SiteMoney;
        }

        public int getPaySiteMoneyType() {
            return PaySiteMoneyType;
        }

        public void setPaySiteMoneyType(int PaySiteMoneyType) {
            this.PaySiteMoneyType = PaySiteMoneyType;
        }

        public String getComments() {
            return Comments;
        }

        public void setComments(String Comments) {
            this.Comments = Comments;
        }

        public int getPublicStatus() {
            return PublicStatus;
        }

        public void setPublicStatus(int PublicStatus) {
            this.PublicStatus = PublicStatus;
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

        public String getVenueid() {
            return venueid;
        }

        public void setVenueid(String venueid) {
            this.venueid = venueid;
        }

        public int getReserve() {
            return reserve;
        }

        public void setReserve(int reserve) {
            this.reserve = reserve;
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
