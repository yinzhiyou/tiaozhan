package com.hyphenate.easeui;

public class HXchaxunEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : {"imgURL":"uploads/HeaderImgs/2019-04-09/20190409202701678.png","nickname":"王二二"}
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
         * imgURL : uploads/HeaderImgs/2019-04-09/20190409202701678.png
         * nickname : 王二二
         */

        private String imgURL;
        private String nickname;

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
