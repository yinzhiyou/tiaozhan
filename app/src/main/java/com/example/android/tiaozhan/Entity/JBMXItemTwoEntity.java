package com.example.android.tiaozhan.Entity;

public class JBMXItemTwoEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"UUID":"61907278-0224-c588-dc2d-ca90d61bfde6","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","SportID":1,"GetCoins":-320,"PublicUUID":"41aa0c8e-fa5b-8292-664a-8e3ba0a4f737","Reason":"由于没有进行场地签到减少对应等级的技术金币","CoinsDate":"2019-04-19 19:31:02","oldTotalCoins":1985.9299999999998,"oldLevel":"Lv8","nowTotalCoins":1665.9299999999998,"nowLevel":"Lv8"}
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
         * UUID : 61907278-0224-c588-dc2d-ca90d61bfde6
         * PlayerUUID : 26d00c58-3004-c05c-bf31-996578f9f5d4
         * SportID : 1
         * GetCoins : -320
         * PublicUUID : 41aa0c8e-fa5b-8292-664a-8e3ba0a4f737
         * Reason : 由于没有进行场地签到减少对应等级的技术金币
         * CoinsDate : 2019-04-19 19:31:02
         * oldTotalCoins : 1985.9299999999998
         * oldLevel : Lv8
         * nowTotalCoins : 1665.9299999999998
         * nowLevel : Lv8
         */

        private String UUID;
        private String PlayerUUID;
        private int SportID;
        private String GetCoins;
        private String PublicUUID;
        private String Reason;
        private String CoinsDate;
        private double oldTotalCoins;
        private String oldLevel;
        private double nowTotalCoins;
        private String nowLevel;

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

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

        public String getGetCoins() {
            return GetCoins;
        }

        public void setGetCoins(String GetCoins) {
            this.GetCoins = GetCoins;
        }

        public String getPublicUUID() {
            return PublicUUID;
        }

        public void setPublicUUID(String PublicUUID) {
            this.PublicUUID = PublicUUID;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getCoinsDate() {
            return CoinsDate;
        }

        public void setCoinsDate(String CoinsDate) {
            this.CoinsDate = CoinsDate;
        }

        public double getOldTotalCoins() {
            return oldTotalCoins;
        }

        public void setOldTotalCoins(double oldTotalCoins) {
            this.oldTotalCoins = oldTotalCoins;
        }

        public String getOldLevel() {
            return oldLevel;
        }

        public void setOldLevel(String oldLevel) {
            this.oldLevel = oldLevel;
        }

        public double getNowTotalCoins() {
            return nowTotalCoins;
        }

        public void setNowTotalCoins(double nowTotalCoins) {
            this.nowTotalCoins = nowTotalCoins;
        }

        public String getNowLevel() {
            return nowLevel;
        }

        public void setNowLevel(String nowLevel) {
            this.nowLevel = nowLevel;
        }
    }
}
