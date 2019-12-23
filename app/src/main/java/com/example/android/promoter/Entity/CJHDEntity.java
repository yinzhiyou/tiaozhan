package com.example.android.promoter.Entity;

public class CJHDEntity {

    /**
     * code : 2000
     * msg : 活动创建成功
     * data : {"uuid":"153c8e2a-b036-f22c-4147-58fe4a05e7a6"}
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
         * uuid : 153c8e2a-b036-f22c-4147-58fe4a05e7a6
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
