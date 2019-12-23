package com.example.android.promoter.Entity;

import java.util.List;

public class HqSportEntity {


    /**
     * code : 2000
     * msg : 获取安卓信息成功
     * data : [{"grade_status":4,"grade_name":"","sport_id":1,"sport_name":"羽毛球"},{"grade_status":4,"grade_name":"","sport_id":2,"sport_name":"乒乓球"},{"grade_status":2,"grade_name":"中级","sport_id":3,"sport_name":"台球"},{"grade_status":1,"grade_name":"初级","sport_id":4,"sport_name":"篮球"},{"grade_status":2,"grade_name":"中级","sport_id":5,"sport_name":"足球"},{"grade_status":2,"grade_name":"中级","sport_id":6,"sport_name":"排球"},{"grade_status":1,"grade_name":"初级","sport_id":7,"sport_name":"网球"},{"grade_status":3,"grade_name":"高级","sport_id":8,"sport_name":"高尔夫"}]
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
         * grade_status : 4
         * grade_name :
         * sport_id : 1
         * sport_name : 羽毛球
         */

        private int grade_status;
        private String grade_name;
        private int sport_id;
        private String sport_name;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getGrade_status() {
            return grade_status;
        }

        public void setGrade_status(int grade_status) {
            this.grade_status = grade_status;
        }

        public String getGrade_name() {
            return grade_name;
        }

        public void setGrade_name(String grade_name) {
            this.grade_name = grade_name;
        }

        public int getSport_id() {
            return sport_id;
        }

        public void setSport_id(int sport_id) {
            this.sport_id = sport_id;
        }

        public String getSport_name() {
            return sport_name;
        }

        public void setSport_name(String sport_name) {
            this.sport_name = sport_name;
        }
    }
}
