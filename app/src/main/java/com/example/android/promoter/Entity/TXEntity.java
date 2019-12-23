package com.example.android.promoter.Entity;

public class TXEntity {


    /**
     * code : 2000
     * msg : 申请提现成功
     * data : {"uuid":"b8820d1a-987e-b1d6-9bd0-c4b172f90da5"}
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
         * uuid : b8820d1a-987e-b1d6-9bd0-c4b172f90da5
         */

        private String uuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
