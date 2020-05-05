package com.example.android.tiaozhan.Entity;

import java.util.List;

public class BiaobiaoEntity {


    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"time":1,"Money":"0.00"},{"time":2,"Money":"0.00"},{"time":3,"Money":"0.00"},{"time":4,"Money":"0.00"},{"time":5,"Money":"0.00"},{"time":6,"Money":"0.00"},{"time":7,"Money":"0.00"},{"time":8,"Money":"0.00"},{"time":9,"Money":"0.00"},{"time":10,"Money":"0.00"},{"time":11,"Money":"0.00"},{"time":12,"Money":"0.00"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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
         * time : 1
         * Money : 0.00
         */

        private int time;
        private String Money;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }
    }
}
