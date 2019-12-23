package com.example.android.promoter.Entity;

public class ZhuceEntity {


    /**
     * code : 2000
     * msg : 注册成功
     * data : {"uuid":"2d04c573-4052-ea7d-05c6-08f379b02155","token":"qxJ7rSJJtfphKU0uJTIMt3KsgnAap2WHF8HwxkAz59eryHgzfRUFbkMKNVm2C1PQ"}
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
         * uuid : 2d04c573-4052-ea7d-05c6-08f379b02155
         * token : qxJ7rSJJtfphKU0uJTIMt3KsgnAap2WHF8HwxkAz59eryHgzfRUFbkMKNVm2C1PQ
         */

        private String uuid;
        private String token;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
