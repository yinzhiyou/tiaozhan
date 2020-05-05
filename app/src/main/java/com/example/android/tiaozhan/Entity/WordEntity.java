package com.example.android.tiaozhan.Entity;

public class WordEntity {

    /**
     * code : 2000
     * msg : 获取成功
     * data : {"comm":"活动匹配成功后，为保证所有人利益，请于2020-02-12 16:30:00前到达场馆并签到。否则按规则须多承担一定场地费（场地费+裁判费）。"}
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
         * comm : 活动匹配成功后，为保证所有人利益，请于2020-02-12 16:30:00前到达场馆并签到。否则按规则须多承担一定场地费（场地费+裁判费）。
         */

        private String comm;

        private String comms;

        public String getComms() {
            return comms;
        }

        public void setComms(String comms) {
            this.comms = comms;
        }

        public String getComm() {
            return comm;
        }

        public void setComm(String comm) {
            this.comm = comm;
        }
    }
}
