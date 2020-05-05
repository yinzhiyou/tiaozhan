package com.example.android.tiaozhan.Entity;

public class RefereeUploadreEntity {

    /**
     * code : 2000
     * msg : 上传成功
     * data : {"baseURL":"uploads/referee/2020-01-03/","filesURL":"20200103134342363.png"}
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
         * baseURL : uploads/referee/2020-01-03/
         * filesURL : 20200103134342363.png
         */

        private String baseURL;
        private String filesURL;

        public String getBaseURL() {
            return baseURL;
        }

        public void setBaseURL(String baseURL) {
            this.baseURL = baseURL;
        }

        public String getFilesURL() {
            return filesURL;
        }

        public void setFilesURL(String filesURL) {
            this.filesURL = filesURL;
        }
    }
}
