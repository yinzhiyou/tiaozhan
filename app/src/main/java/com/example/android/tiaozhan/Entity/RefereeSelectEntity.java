package com.example.android.tiaozhan.Entity;

import java.util.List;

public class RefereeSelectEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"certificate":"ChinaLQ2000FIRST","certificate_img":"uploads/referee/2019-12-27/20191227140607609.png","sportid":8,"grade":"国家级","id":7146,"status":2,"reason":"333333333333","sport":"高尔夫"},{"certificate":"11111111","certificate_img":"uploads/HeaderImgs/2019-12-31/20191231173624634.png","sportid":4,"grade":"一级","id":192151,"status":0,"reason":"","sport":"篮球"},{"certificate":"3455323445544","certificate_img":"uploads/referee/2019-12-31/20191231150820624.png","sportid":3,"grade":"二级","id":2,"status":1,"reason":"","sport":"台球"},{"certificate":"11111111","certificate_img":"uploads/HeaderImgs/2019-12-31/20191231173204705.png","sportid":2,"grade":"一级","id":4,"status":0,"reason":"","sport":"乒乓球"},{"certificate":"123456","certificate_img":"uploads/HeaderImgs/2019-12-31/20191231173959441.png","sportid":5,"grade":"一级","id":517620000000,"status":0,"reason":"","sport":"足球"},{"certificate":"ChinaLQ2000FIRST","certificate_img":"uploads/referee/2019-12-27/20191227114801697.png","sportid":1,"grade":"国家级","id":0,"status":1,"reason":"","sport":"羽毛球"}]
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
         * certificate : ChinaLQ2000FIRST
         * certificate_img : uploads/referee/2019-12-27/20191227140607609.png
         * sportid : 8
         * grade : 国家级
         * id : 7146
         * status : 2
         * reason : 333333333333
         * sport : 高尔夫
         */

        private String certificate;
        private String certificate_img;
        private String sportid;
        private String grade;
        private String refereeid;
        private int status;
        private String reason;
        private String sport;

        public String getRefereeid() {
            return refereeid;
        }

        public void setRefereeid(String refereeid) {
            this.refereeid = refereeid;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getCertificate_img() {
            return certificate_img;
        }

        public void setCertificate_img(String certificate_img) {
            this.certificate_img = certificate_img;
        }

        public String getSportid() {
            return sportid;
        }

        public void setSportid(String  sportid) {
            this.sportid = sportid;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }



        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }
    }
}
