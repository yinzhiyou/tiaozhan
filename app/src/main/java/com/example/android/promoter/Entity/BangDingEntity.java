package com.example.android.promoter.Entity;

public class BangDingEntity {


    /**
     * code : 2000
     * msg : 绑定成功
     * data : {"token":"XqrxmlFeR8Rx7cSqPhLiYKHRNrFlwZJ3qEoGzqbdpafUNgDV0XlWBuvli29bmovs","uuid":"26d00c58-3004-c05c-bf31-996578f9f5d4","imgURL":"uploads/HeaderImgs/2019-09-02/20190902170202187.png","nickname":"王大大","sex":0}
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
         * token : XqrxmlFeR8Rx7cSqPhLiYKHRNrFlwZJ3qEoGzqbdpafUNgDV0XlWBuvli29bmovs
         * uuid : 26d00c58-3004-c05c-bf31-996578f9f5d4
         * imgURL : uploads/HeaderImgs/2019-09-02/20190902170202187.png
         * nickname : 王大大
         * sex : 0
         */

        private String token;
        private String uuid;
        private String imgURL;
        private String nickname;
        private int sex;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
