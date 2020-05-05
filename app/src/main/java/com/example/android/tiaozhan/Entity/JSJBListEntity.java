package com.example.android.tiaozhan.Entity;

import java.util.List;

public class JSJBListEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":1,"total":10,"sport_name":"羽毛球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":2,"total":10,"sport_name":"乒乓球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":3,"total":10,"sport_name":"台球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":4,"total":10,"sport_name":"篮球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":5,"total":10,"sport_name":"足球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":6,"total":10,"sport_name":"排球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":7,"total":10,"sport_name":"网球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"17cc900b-a1bd-dd53-c455-b12c28ab823e","SportID":8,"total":10,"sport_name":"高尔夫","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"}]
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * PlayerUUID : 17cc900b-a1bd-dd53-c455-b12c28ab823e
         * SportID : 1
         * total : 10
         * sport_name : 羽毛球
         * nowlevel : Lv1
         * mincoins : 0
         * maxcoins : 20
         * nextlevel : Lv2
         */

        private String PlayerUUID;
        private int SportID;
        private double total;
        private String sport_name;
        private String nowlevel;
        private int mincoins;
        private int maxcoins;
        private String nextlevel;

        public String getPlayerUUID() {
            return PlayerUUID;
        }

        public void setPlayerUUID(String PlayerUUID) {
            this.PlayerUUID = PlayerUUID;
        }

        public int getSportID() {
            return SportID;
        }

        public void setSportID(int SportID) {
            this.SportID = SportID;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getSport_name() {
            return sport_name;
        }

        public void setSport_name(String sport_name) {
            this.sport_name = sport_name;
        }

        public String getNowlevel() {
            return nowlevel;
        }

        public void setNowlevel(String nowlevel) {
            this.nowlevel = nowlevel;
        }

        public int getMincoins() {
            return mincoins;
        }

        public void setMincoins(int mincoins) {
            this.mincoins = mincoins;
        }

        public int getMaxcoins() {
            return maxcoins;
        }

        public void setMaxcoins(int maxcoins) {
            this.maxcoins = maxcoins;
        }

        public String getNextlevel() {
            return nextlevel;
        }

        public void setNextlevel(String nextlevel) {
            this.nextlevel = nextlevel;
        }
    }
}
