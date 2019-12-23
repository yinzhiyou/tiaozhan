package com.example.android.promoter.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YHKEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":4,"Playeruid":"24c74571-9659-8bf9-b0c5-78b2e35acae7","BankCardNum":"6226370073122190","BankName":"华夏银行","SubBankName":"","PlayerPhoneNum":"18201395884","cardType":"华夏信用卡金卡","cardCategory":"贷记卡","bindDate":"2019-01-31 20:48:24","default":0,"isDelete":0},{"id":5,"Playeruid":"24c74571-9659-8bf9-b0c5-78b2e35acae7","BankCardNum":"6226370073122190","BankName":"华夏银行","SubBankName":"","PlayerPhoneNum":"18201395884","cardType":"华夏信用卡金卡","cardCategory":"贷记卡","bindDate":"2019-01-31 20:48:30","default":0,"isDelete":0}]
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
         * id : 4
         * Playeruid : 24c74571-9659-8bf9-b0c5-78b2e35acae7
         * BankCardNum : 6226370073122190
         * BankName : 华夏银行
         * SubBankName :
         * PlayerPhoneNum : 18201395884
         * cardType : 华夏信用卡金卡
         * cardCategory : 贷记卡
         * bindDate : 2019-01-31 20:48:24
         * default : 0
         * isDelete : 0
         */

        private int id;
        private String Playeruid;
        private String BankCardNum;
        private String BankName;
        private String SubBankName;
        private String PlayerPhoneNum;
        private String cardType;
        private String cardCategory;
        private String bindDate;
        @SerializedName("default")
        private int defaultX;
        private int isDelete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlayeruid() {
            return Playeruid;
        }

        public void setPlayeruid(String Playeruid) {
            this.Playeruid = Playeruid;
        }

        public String getBankCardNum() {
            return BankCardNum;
        }

        public void setBankCardNum(String BankCardNum) {
            this.BankCardNum = BankCardNum;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getSubBankName() {
            return SubBankName;
        }

        public void setSubBankName(String SubBankName) {
            this.SubBankName = SubBankName;
        }

        public String getPlayerPhoneNum() {
            return PlayerPhoneNum;
        }

        public void setPlayerPhoneNum(String PlayerPhoneNum) {
            this.PlayerPhoneNum = PlayerPhoneNum;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardCategory() {
            return cardCategory;
        }

        public void setCardCategory(String cardCategory) {
            this.cardCategory = cardCategory;
        }

        public String getBindDate() {
            return bindDate;
        }

        public void setBindDate(String bindDate) {
            this.bindDate = bindDate;
        }

        public int getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(int defaultX) {
            this.defaultX = defaultX;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }
    }
}
