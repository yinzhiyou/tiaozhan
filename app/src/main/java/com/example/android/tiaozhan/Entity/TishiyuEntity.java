package com.example.android.tiaozhan.Entity;

public class TishiyuEntity {

    /**
     * code : 2000
     * msg : 操作成功
     * data : {"Tips":"目前履约人数有活动总人数一半以上，\u201c提前退出\u201d后平台将扣除您支付的2倍人均场地费。","site":""}
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
         * Tips : 目前履约人数有活动总人数一半以上，“提前退出”后平台将扣除您支付的2倍人均场地费。
         * site :
         */

        private String Tips;
        private String site;

        public String getTips() {
            return Tips;
        }

        public void setTips(String Tips) {
            this.Tips = Tips;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }
    }
}
