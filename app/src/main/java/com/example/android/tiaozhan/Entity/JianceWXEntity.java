package com.example.android.tiaozhan.Entity;

public class JianceWXEntity {

    /**
     * code : 2000
     * msg : 已绑定微信
     * data : {"wechartID":"ovEr01XhsBTV87B-9Q6VIco5RiAA"}
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
         * wechartID : ovEr01XhsBTV87B-9Q6VIco5RiAA
         */

        private String wechartID;

        public String getWechartID() {
            return wechartID;
        }

        public void setWechartID(String wechartID) {
            this.wechartID = wechartID;
        }
    }
}
