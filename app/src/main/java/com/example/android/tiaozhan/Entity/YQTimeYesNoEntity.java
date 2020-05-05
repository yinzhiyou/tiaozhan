package com.example.android.tiaozhan.Entity;

public class YQTimeYesNoEntity {

    /**
     * code : 2000
     * msg : 可以加入此活动
     * data : {"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","nickname":"王大大","imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","sex":0,"coinsSum":10,"level":"Lv1"}
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
         * uuid : 54ca24d3-0f6b-2abe-ae50-3808e4eda735
         * nickname : 王大大
         * imgURL : uploads/2018-11-13/HeaderImgs/20181113200402128.png
         * sex : 0
         * coinsSum : 10
         * level : Lv1
         */

        private String uuid;
        private String nickname;
        private String imgURL;
        private int sex;
        private double coinsSum;
        private String level;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public double getCoinsSum() {
            return coinsSum;
        }

        public void setCoinsSum(double coinsSum) {
            this.coinsSum = coinsSum;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
