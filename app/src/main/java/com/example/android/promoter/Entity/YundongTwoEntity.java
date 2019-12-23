package com.example.android.promoter.Entity;

import java.util.List;

public class YundongTwoEntity  {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":6,"sportid":2,"name":"单打","playerNumber":2,"PrepareMinite":30},{"id":7,"sportid":2,"name":"双打","playerNumber":4,"PrepareMinite":60}]
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
         * id : 6
         * sportid : 2
         * name : 单打
         * playerNumber : 2
         * PrepareMinite : 30
         */

        private int id;
        private int sportid;
        private String name;
        private int playerNumber;
        private int PrepareMinite;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSportid() {
            return sportid;
        }

        public void setSportid(int sportid) {
            this.sportid = sportid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlayerNumber() {
            return playerNumber;
        }

        public void setPlayerNumber(int playerNumber) {
            this.playerNumber = playerNumber;
        }

        public int getPrepareMinite() {
            return PrepareMinite;
        }

        public void setPrepareMinite(int PrepareMinite) {
            this.PrepareMinite = PrepareMinite;
        }
    }
}
