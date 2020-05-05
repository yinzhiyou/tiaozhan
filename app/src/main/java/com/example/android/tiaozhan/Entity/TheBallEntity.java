package com.example.android.tiaozhan.Entity;

import java.util.List;

public class TheBallEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : {"comm":[{"SportLevel":"4","money":40},{"SportLevel":"5","money":50},{"SportLevel":"6","money":60},{"SportLevel":"7","money":70},{"SportLevel":"8","money":80},{"SportLevel":"9","money":90},{"SportLevel":"10","money":100}],"name":"羽毛球","CityName":"北京市"}
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
         * comm : [{"SportLevel":"4","money":40},{"SportLevel":"5","money":50},{"SportLevel":"6","money":60},{"SportLevel":"7","money":70},{"SportLevel":"8","money":80},{"SportLevel":"9","money":90},{"SportLevel":"10","money":100}]
         * name : 羽毛球
         * CityName : 北京市
         */

        private String name;
        private String CityName;
        private List<CommBean> comm;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public List<CommBean> getComm() {
            return comm;
        }

        public void setComm(List<CommBean> comm) {
            this.comm = comm;
        }

        public static class CommBean {
            /**
             * SportLevel : 4
             * money : 40
             */

            private String SportLevel;
            private int money;

            public String getSportLevel() {
                return SportLevel;
            }

            public void setSportLevel(String SportLevel) {
                this.SportLevel = SportLevel;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }
    }
}
