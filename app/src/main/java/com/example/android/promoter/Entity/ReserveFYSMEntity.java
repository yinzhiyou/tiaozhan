package com.example.android.promoter.Entity;

public class ReserveFYSMEntity {

    /**
     * code : 2000
     * msg : 获取费用说明成功
     * data : {"field":80,"siteMoneyInfo":"2019-12-19 16:00:00前可以无责取消预订，场地费全部退还。","Total":80}
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
         * field : 80
         * siteMoneyInfo : 2019-12-19 16:00:00前可以无责取消预订，场地费全部退还。
         * Total : 80
         */

        private String field;
        private String siteMoneyInfo;
        private String Total;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getSiteMoneyInfo() {
            return siteMoneyInfo;
        }

        public void setSiteMoneyInfo(String siteMoneyInfo) {
            this.siteMoneyInfo = siteMoneyInfo;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }
    }
}
