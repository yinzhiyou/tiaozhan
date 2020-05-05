package com.example.android.tiaozhan.Entity;

public class PromoterNewsReadEntity {

    /**
     * code : 2000
     * msg : 查询成功
     * data : {"content":"您好，您推广的\u201c山西省体育健身馆\u201d有参与人员撤回了投诉，请及时查看！","isred":1,"intime":"2019-11-25 11:57:27"}
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
         * content : 您好，您推广的“山西省体育健身馆”有参与人员撤回了投诉，请及时查看！
         * isred : 1
         * intime : 2019-11-25 11:57:27
         */

        private String content;
        private int isred;
        private String intime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsred() {
            return isred;
        }

        public void setIsred(int isred) {
            this.isred = isred;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }
    }
}
