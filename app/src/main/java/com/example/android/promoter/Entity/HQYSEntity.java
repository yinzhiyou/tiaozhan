package com.example.android.promoter.Entity;

public class HQYSEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"acceptNearplayerInvite":1,"acceptFirendInvite":1}
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
         * acceptNearplayerInvite : 1
         * acceptFirendInvite : 1
         */

        private int acceptNearplayerInvite;
        private int acceptFirendInvite;

        public int getAcceptNearplayerInvite() {
            return acceptNearplayerInvite;
        }

        public void setAcceptNearplayerInvite(int acceptNearplayerInvite) {
            this.acceptNearplayerInvite = acceptNearplayerInvite;
        }

        public int getAcceptFirendInvite() {
            return acceptFirendInvite;
        }

        public void setAcceptFirendInvite(int acceptFirendInvite) {
            this.acceptFirendInvite = acceptFirendInvite;
        }
    }
}
