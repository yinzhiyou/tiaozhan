package com.example.android.tiaozhan.Entity;

public class DengjiEntity  {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"id":1,"level":"Lv1","grade":1,"mincoins":0,"maxcoins":20,"winCoins":2.5,"name":"乒乓球","sportID":2}
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
         * id : 1
         * level : Lv1
         * grade : 1
         * mincoins : 0
         * maxcoins : 20
         * winCoins : 2.5
         * name : 乒乓球
         * sportID : 2
         */

        private int id;
        private String level;
        private int grade;
        private int mincoins;
        private int maxcoins;
        private double winCoins;
        private String name;
        private int sportID;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
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

        public double getWinCoins() {
            return winCoins;
        }

        public void setWinCoins(double winCoins) {
            this.winCoins = winCoins;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSportID() {
            return sportID;
        }

        public void setSportID(int sportID) {
            this.sportID = sportID;
        }
    }
}
