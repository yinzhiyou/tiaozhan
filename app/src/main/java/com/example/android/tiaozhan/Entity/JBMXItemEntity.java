package com.example.android.tiaozhan.Entity;

public class JBMXItemEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"UUID":"18cf0f11-ddc4-aeb8-cbfa-6728ebd3eb57","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","GetCommonCoins":"5","Reason":"参与活动奖励","CoinsDate":"2019-04-28 12:00:02","PublicUUID":"cecb0b15-5c17-2536-ef87-47fa49552147","totalCoins":320}
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
         * UUID : 18cf0f11-ddc4-aeb8-cbfa-6728ebd3eb57
         * PlayerUUID : 26d00c58-3004-c05c-bf31-996578f9f5d4
         * GetCommonCoins : 5
         * Reason : 参与活动奖励
         * CoinsDate : 2019-04-28 12:00:02
         * PublicUUID : cecb0b15-5c17-2536-ef87-47fa49552147
         * totalCoins : 320
         */

        private String UUID;
        private String PlayerUUID;
        private String GetCommonCoins;
        private String Reason;
        private String CoinsDate;
        private String PublicUUID;
        private String totalCoins;

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

        public String getGetCommonCoins() {
            return GetCommonCoins;
        }

        public void setGetCommonCoins(String GetCommonCoins) {
            this.GetCommonCoins = GetCommonCoins;
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

        public String getPublicUUID() {
            return PublicUUID;
        }

        public void setPublicUUID(String PublicUUID) {
            this.PublicUUID = PublicUUID;
        }

        public String getTotalCoins() {
            return totalCoins;
        }

        public void setTotalCoins(String totalCoins) {
            this.totalCoins = totalCoins;
        }
    }
}
