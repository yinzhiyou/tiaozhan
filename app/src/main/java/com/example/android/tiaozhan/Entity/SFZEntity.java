package com.example.android.tiaozhan.Entity;

public class SFZEntity {


    /**
     * code : 2000
     * msg : 上传成功
     * data : {"baseURL":"uploads/PromoterIdImgs/2019-06-28/","filesURL":"20190628175454725.png"}
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
         * baseURL : uploads/PromoterIdImgs/2019-06-28/
         * filesURL : 20190628175454725.png
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
