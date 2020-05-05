package com.example.android.tiaozhan.Entity;

public class BastEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : {"todaymoney":"0.00"}
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
         * todaymoney : 0.00
         */

        private String todaymoney;

        public String getTodaymoney() {
            return todaymoney;
        }

        public void setTodaymoney(String todaymoney) {
            this.todaymoney = todaymoney;
        }
    }
}
