package com.example.android.promoter.Entity;

public class DengjiEntity  {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"id":1,"level":"Lv1","mincoins":0,"maxcoins":20,"name":"羽毛球"}
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
         * id : 1
         * level : Lv1
         * mincoins : 0
         * maxcoins : 20
         * name : 羽毛球
         */

        private int id;
        private String level;
        private int mincoins;
        private int maxcoins;
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
