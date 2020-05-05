package com.example.android.tiaozhan.Entity;

import java.util.List;

public class HomeRefereeResultEntity {

    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"referee":"C1","playerimg":"uploads/HeaderImgs/2020-01-08/20200108105039812.png","result":2},{"referee":"C2","playerimg":"uploads/HeaderImgs/2019-09-09/20190909095616925.png","result":1}]
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
         * referee : C1
         * playerimg : uploads/HeaderImgs/2020-01-08/20200108105039812.png
         * result : 2
         */

        private String referee;
        private String playerimg;
        private int result;

        public String getReferee() {
            return referee;
        }

        public void setReferee(String referee) {
            this.referee = referee;
        }

        public String getPlayerimg() {
            return playerimg;
        }

        public void setPlayerimg(String playerimg) {
            this.playerimg = playerimg;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}
