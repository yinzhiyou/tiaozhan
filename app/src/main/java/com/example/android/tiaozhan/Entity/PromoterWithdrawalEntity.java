package com.example.android.tiaozhan.Entity;

public class PromoterWithdrawalEntity {

    /**
     * code : 2000
     * msg : 提现申请成功
     * data : {"uuid":"79113ae0-53a6-0dc2-f35a-14212572ad8d"}
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
         * uuid : 79113ae0-53a6-0dc2-f35a-14212572ad8d
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
