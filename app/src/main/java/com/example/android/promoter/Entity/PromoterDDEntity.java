package com.example.android.promoter.Entity;

import java.util.List;

public class PromoterDDEntity {


    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"orderId":"011906277941","Sport":"羽毛球双打","SiteName":"2北京甲乙电子商务","SiteMoney":0,"CreateTime":"2019-06-27 19:00:00","FinishedTime":"2019-06-27 19:30:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906276378","Sport":"羽毛球双打","SiteName":"2北京甲乙电子商务","SiteMoney":0,"CreateTime":"2019-06-27 18:00:00","FinishedTime":"2019-06-27 18:30:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906273301","Sport":"羽毛球双打","SiteName":"2北京甲乙电子商务","SiteMoney":0,"CreateTime":"2019-06-27 16:30:00","FinishedTime":"2019-06-27 17:00:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906265559","Sport":"篮球3v3","SiteName":"2北京甲乙电子商务","SiteMoney":6,"CreateTime":"2019-06-26 23:00:00","FinishedTime":"2019-06-26 23:30:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906263220","Sport":"乒乓球双打","SiteName":"1北京甲乙电子商务","SiteMoney":1,"CreateTime":"2019-06-26 19:00:00","FinishedTime":"2019-06-26 19:30:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906269132","Sport":"乒乓球单打","SiteName":"1北京甲乙电子商务","SiteMoney":1,"CreateTime":"2019-06-26 18:30:00","FinishedTime":"2019-06-26 19:00:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906269086","Sport":"篮球3v3","SiteName":"2北京甲乙电子商务","SiteMoney":10,"CreateTime":"2019-06-26 18:00:00","FinishedTime":"2019-06-26 18:30:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906268821","Sport":"乒乓球双打","SiteName":"2北京甲乙电子商务","SiteMoney":6,"CreateTime":"2019-06-26 17:30:00","FinishedTime":"2019-06-26 18:00:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906263100","Sport":"乒乓球单打","SiteName":"2北京甲乙电子商务","SiteMoney":6,"CreateTime":"2019-06-26 17:00:00","FinishedTime":"2019-06-26 17:30:00","RoyaltyMoney":0,"Royalty":20},{"orderId":"011906265308","Sport":"篮球3v3","SiteName":"1北京甲乙电子商务","SiteMoney":1,"CreateTime":"2019-06-26 16:30:00","FinishedTime":"2019-06-26 17:00:00","RoyaltyMoney":0,"Royalty":20}]
     */

    private int code;
    private String msg;
  private String other;
    private List<DataBean> data;

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }



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
         * orderId : 011906277941
         * Sport : 羽毛球双打
         * SiteName : 2北京甲乙电子商务
         * SiteMoney : 0
         * CreateTime : 2019-06-27 19:00:00
         * FinishedTime : 2019-06-27 19:30:00
         * RoyaltyMoney : 0
         * Royalty : 20
         */

        private String orderId;
        private String Sport;
        private String SiteName;
        private int SiteMoney;
        private String CreateTime;
        private String FinishedTime;
        private String RoyaltyMoney;
        private int Royalty;
     private int  PublicStatus;

        public int getPublicStatus() {
            return PublicStatus;
        }

        public void setPublicStatus(int publicStatus) {
            PublicStatus = publicStatus;
        }


        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSport() {
            return Sport;
        }

        public void setSport(String Sport) {
            this.Sport = Sport;
        }

        public String getSiteName() {
            return SiteName;
        }

        public void setSiteName(String SiteName) {
            this.SiteName = SiteName;
        }

        public int getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(int SiteMoney) {
            this.SiteMoney = SiteMoney;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getFinishedTime() {
            return FinishedTime;
        }

        public void setFinishedTime(String FinishedTime) {
            this.FinishedTime = FinishedTime;
        }

        public String getRoyaltyMoney() {
            return RoyaltyMoney;
        }

        public void setRoyaltyMoney(String RoyaltyMoney) {
            this.RoyaltyMoney = RoyaltyMoney;
        }

        public int getRoyalty() {
            return Royalty;
        }

        public void setRoyalty(int Royalty) {
            this.Royalty = Royalty;
        }
    }
}
