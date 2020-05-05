package com.example.android.tiaozhan.Entity;

public class CJYQEntity  {

    /**
     * code : 2000
     * msg : 邀请成功
     * data : {"uuid":"743e0cd4-af41-7fec-dd5c-4ef0f29b696a","nickname":"你瞅啥","imgURL":"uploads/HeaderImgs/2019-01-02/20190102101815886.png","coinsSum":10,"level":"Lv1"}
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
         * uuid : 743e0cd4-af41-7fec-dd5c-4ef0f29b696a
         * nickname : 你瞅啥
         * imgURL : uploads/HeaderImgs/2019-01-02/20190102101815886.png
         * coinsSum : 10
         * level : Lv1
         */

        private String uuid;
        private String nickname;
        private String imgURL;
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
