package com.example.android.tiaozhan.Entity;

public class JudgerefereeJEntity {

    /**
     * code : 2000
     * msg : 查询成功
     * data : {"PlayerRealName":"王鹏飞","PlayerAppPhoneNumber":"13691289892","PlayerID":"130823199109081018"}
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
         * PlayerRealName : 王鹏飞
         * PlayerAppPhoneNumber : 13691289892
         * PlayerID : 130823199109081018
         */

        private String PlayerRealName;
        private String PlayerAppPhoneNumber;
        private String PlayerID;

        public String getPlayerRealName() {
            return PlayerRealName;
        }

        public void setPlayerRealName(String PlayerRealName) {
            this.PlayerRealName = PlayerRealName;
        }

        public String getPlayerAppPhoneNumber() {
            return PlayerAppPhoneNumber;
        }

        public void setPlayerAppPhoneNumber(String PlayerAppPhoneNumber) {
            this.PlayerAppPhoneNumber = PlayerAppPhoneNumber;
        }

        public String getPlayerID() {
            return PlayerID;
        }

        public void setPlayerID(String PlayerID) {
            this.PlayerID = PlayerID;
        }
    }
}
