package com.example.android.tiaozhan.Entity;

public class PromoterMoneyDetailsEntity {


    /**
     * code : 2000
     * msg : 查询成功
     * data : {"InOrOut":1,"RecordDate":"2019-12-02 15:02:11","RecordReason":"推广员提成","publicuid":"a01c39b7-90c6-b891-061f-e67581ab88a8","money":"4.40","currentmoney":"10032.36","public_orderId":"011912021339565982","public_time":"2019-12-02 14:30~2019-12-02 15:00","sitename":"山西省体育健身馆"}
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
         * InOrOut : 1
         * RecordDate : 2019-12-02 15:02:11
         * RecordReason : 推广员提成
         * publicuid : a01c39b7-90c6-b891-061f-e67581ab88a8
         * money : 4.40
         * currentmoney : 10032.36
         * public_orderId : 011912021339565982
         * public_time : 2019-12-02 14:30~2019-12-02 15:00
         * sitename : 山西省体育健身馆
         */

        private int InOrOut;
        private String RecordDate;
        private String RecordReason;
        private String publicuid;
        private String money;
        private String currentmoney;
        private String public_orderId;
        private String public_time;
        private String sitename;

        public int getInOrOut() {
            return InOrOut;
        }

        public void setInOrOut(int InOrOut) {
            this.InOrOut = InOrOut;
        }

        public String getRecordDate() {
            return RecordDate;
        }

        public void setRecordDate(String RecordDate) {
            this.RecordDate = RecordDate;
        }

        public String getRecordReason() {
            return RecordReason;
        }

        public void setRecordReason(String RecordReason) {
            this.RecordReason = RecordReason;
        }

        public String getPublicuid() {
            return publicuid;
        }

        public void setPublicuid(String publicuid) {
            this.publicuid = publicuid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCurrentmoney() {
            return currentmoney;
        }

        public void setCurrentmoney(String currentmoney) {
            this.currentmoney = currentmoney;
        }

        public String getPublic_orderId() {
            return public_orderId;
        }

        public void setPublic_orderId(String public_orderId) {
            this.public_orderId = public_orderId;
        }

        public String getPublic_time() {
            return public_time;
        }

        public void setPublic_time(String public_time) {
            this.public_time = public_time;
        }

        public String getSitename() {
            return sitename;
        }

        public void setSitename(String sitename) {
            this.sitename = sitename;
        }
    }
}
