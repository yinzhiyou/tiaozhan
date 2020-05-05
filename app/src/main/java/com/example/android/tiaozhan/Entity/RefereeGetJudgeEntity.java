package com.example.android.tiaozhan.Entity;

import java.util.List;

public class RefereeGetJudgeEntity {


    /**
     * code : 2000
     * msg : 获取裁判信息成功
     * data : [{"uuid":"4dfb2a91-0d00-1d91-8bcd-a885d6e8b7cb","nickname":"t和斤斤计较","imgURL":"uploads/HeaderImgs/2020-01-03/20200103175708644.png"},{"uuid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2020-01-02/20200102112103655.png"}]
     * other :
     */

    private int code;
    private String msg;
    private String other;
    private List<DataBean> data;

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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uuid : 4dfb2a91-0d00-1d91-8bcd-a885d6e8b7cb
         * nickname : t和斤斤计较
         * imgURL : uploads/HeaderImgs/2020-01-03/20200103175708644.png
         */

        private String uuid;
        private String nickname;
        private String imgURL;
        private boolean boxChecked;

        public boolean getBoxChecked() {
            return boxChecked;
        }

        public void setBoxChecked(boolean boxChecked) {
            this.boxChecked = boxChecked;
        }

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
    }
}
