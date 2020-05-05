package com.example.android.tiaozhan.Entity;

public class TGIDEntity {

    /**
     * code : 2000
     * msg : 查询成功
     * data : {"PromotId":676464}
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
         * PromotId : 676464
         */

        private int PromotId;

        public int getPromotId() {
            return PromotId;
        }

        public void setPromotId(int PromotId) {
            this.PromotId = PromotId;
        }
    }
}
