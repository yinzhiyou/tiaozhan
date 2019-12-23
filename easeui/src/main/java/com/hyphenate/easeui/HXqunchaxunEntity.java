package com.hyphenate.easeui;

public class HXqunchaxunEntity {


    /**
     * code : 2000
     * msg : 获取成功成功
     * data : {"img":"uploads/pictures/2019-08-31/20190831144242852785.png","group_name":"篮球5v520190831 14:00"}
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
         * img : uploads/pictures/2019-08-31/20190831144242852785.png
         * group_name : 篮球5v520190831 14:00
         */

        private String img;
        private String group_name;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }
    }
}
