package com.example.android.tiaozhan.Entity;

public class SMRZEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"PlayerUUID":"b08e78a9-7bf4-c5f9-16c4-7474290ea2a5","PlayerRealName":"张建宇","PlayerID":"11022319911015787x"}
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
         * PlayerUUID : b08e78a9-7bf4-c5f9-16c4-7474290ea2a5
         * PlayerRealName : 张建宇
         * PlayerID : 11022319911015787x
         */

        private String PlayerUUID;
        private String PlayerRealName;
        private String PlayerID;

        public String getPlayerUUID() {
            return PlayerUUID;
        }

        public void setPlayerUUID(String PlayerUUID) {
            this.PlayerUUID = PlayerUUID;
        }

        public String getPlayerRealName() {
            return PlayerRealName;
        }

        public void setPlayerRealName(String PlayerRealName) {
            this.PlayerRealName = PlayerRealName;
        }

        public String getPlayerID() {
            return PlayerID;
        }

        public void setPlayerID(String PlayerID) {
            this.PlayerID = PlayerID;
        }
    }
}
