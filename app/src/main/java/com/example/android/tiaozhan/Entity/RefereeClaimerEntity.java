package com.example.android.tiaozhan.Entity;

public class RefereeClaimerEntity {



    /**
     * code : 4005
     * msg : 您不是该球的裁判
     * data : {"status":2,"comm":"抱歉，您还不是三级篮球裁判，无法报名"}
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
         * status : 2
         * comm : 抱歉，您还不是三级篮球裁判，无法报名
         */

        private int status;
        private String comm;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getComm() {
            return comm;
        }

        public void setComm(String comm) {
            this.comm = comm;
        }
    }
}
