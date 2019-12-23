package com.example.android.promoter.Entity;

public class JianceZFBEntity {

    /**
     * code : 2000
     * msg : 已绑定支付宝
     * data : {"zfbID":"729092557@qq.com"}
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
         * zfbID : 729092557@qq.com
         */

        private String zfbID;

        public String getZfbID() {
            return zfbID;
        }

        public void setZfbID(String zfbID) {
            this.zfbID = zfbID;
        }
    }
}
