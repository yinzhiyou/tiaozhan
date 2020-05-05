package com.example.android.tiaozhan.Entity;

public class TXMMEntity {


    /**
     * code : 2000
     * msg : 设置成功
     * data : {"putMoneyPwd":"111111"}
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
         * putMoneyPwd : 111111
         */

        private String putMoneyPwd;

        public String getPutMoneyPwd() {
            return putMoneyPwd;
        }

        public void setPutMoneyPwd(String putMoneyPwd) {
            this.putMoneyPwd = putMoneyPwd;
        }
    }
}
