package com.example.android.promoter.Entity;

public class QBMXItemEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"orderId":"011909025715","uuid":"b6be6b7e-69ec-7876-a6f1-a622710afc73","InOrOut":2,"Money":"-1.00","RecordDate":"2019-09-02 10:08:50","RecordReason":"发布活动费用支付：-1.00元。","total":"12,376.70"}
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
         * orderId : 011909025715
         * uuid : b6be6b7e-69ec-7876-a6f1-a622710afc73
         * InOrOut : 2
         * Money : -1.00
         * RecordDate : 2019-09-02 10:08:50
         * RecordReason : 发布活动费用支付：-1.00元。
         * total : 12,376.70
         */

        private String orderId;
        private String uuid;
        private int InOrOut;
        private String Money;
        private String RecordDate;
        private String RecordReason;
        private String total;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getInOrOut() {
            return InOrOut;
        }

        public void setInOrOut(int InOrOut) {
            this.InOrOut = InOrOut;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
