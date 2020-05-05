package com.example.android.tiaozhan.Entity;

import java.util.List;

public class JBSMEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"level":"1","mincoins":0,"maxcoins":20,"winCoins":2.5},{"level":"2","mincoins":20,"maxcoins":40,"winCoins":5},{"level":"3","mincoins":40,"maxcoins":80,"winCoins":10},{"level":"4","mincoins":80,"maxcoins":160,"winCoins":20},{"level":"5","mincoins":160,"maxcoins":320,"winCoins":40},{"level":"6","mincoins":320,"maxcoins":640,"winCoins":80},{"level":"7","mincoins":640,"maxcoins":1280,"winCoins":160},{"level":"8","mincoins":1280,"maxcoins":2560,"winCoins":320},{"level":"9","mincoins":2560,"maxcoins":5210,"winCoins":640},{"level":"10","mincoins":5210,"maxcoins":99999,"winCoins":1280}]
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
         * level : 1
         * mincoins : 0
         * maxcoins : 20
         * winCoins : 2.5
         */

        private String level;
        private int mincoins;
        private int maxcoins;
        private double winCoins;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getMincoins() {
            return mincoins;
        }

        public void setMincoins(int mincoins) {
            this.mincoins = mincoins;
        }

        public int getMaxcoins() {
            return maxcoins;
        }

        public void setMaxcoins(int maxcoins) {
            this.maxcoins = maxcoins;
        }

        public double getWinCoins() {
            return winCoins;
        }

        public void setWinCoins(double winCoins) {
            this.winCoins = winCoins;
        }
    }
}
