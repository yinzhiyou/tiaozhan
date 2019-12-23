package com.example.android.promoter.Entity;

import java.util.List;

public class ProMoneyDetailEntity {


    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"UUID":"ea79f2fa-e16b-8b5d-c7e4-6a2f2437de69","RecordReason":"推广员提成","RecordDate":"2019-11-25 17:30:02","InOrOut":1,"money":"0.30"},{"UUID":"0331eb8f-3d60-9be9-d6b1-3ac3f07083b2","RecordReason":"推广员提现","RecordDate":"2019-11-25 09:32:44","InOrOut":2,"money":"-10.00"},{"UUID":"bfa877e6-a341-bf5b-967a-f6b2161a396f","RecordReason":"推广员提现","RecordDate":"2019-11-22 19:15:38","InOrOut":2,"money":"-100.10"},{"UUID":"bd7a3d53-7461-6d29-56a4-9a57b217a3a0","RecordReason":"推广员提成","RecordDate":"2019-11-22 17:00:02","InOrOut":1,"money":"0.10"},{"UUID":"089fcbd8-9fb9-c40c-a6df-5d763b628e77","RecordReason":"推广员提成","RecordDate":"2019-11-22 15:33:54","InOrOut":1,"money":"0.02"},{"UUID":"938db938-dae7-be58-a50a-1a04733ac028","RecordReason":"推广员提成","RecordDate":"2019-11-22 15:31:43","InOrOut":1,"money":"0.10"},{"UUID":"8f60dd38-655f-bbf5-d099-4c95e404efca","RecordReason":"推广员提成","RecordDate":"2019-11-19 19:00:01","InOrOut":1,"money":"0.10"},{"UUID":"b1fdd347-5e5d-8225-96cc-7e1ae88ee73a","RecordReason":"推广员提成","RecordDate":"2019-11-18 17:48:49","InOrOut":1,"money":"0.10"},{"UUID":"2480a1c3-c01b-b2af-06ae-8b5afadc5134","RecordReason":"推广员提成","RecordDate":"2019-11-18 17:03:26","InOrOut":1,"money":"0.10"},{"UUID":"726ba00e-f164-5326-306b-3cd5d446fc9a","RecordReason":"推广员提成","RecordDate":"2019-11-14 07:13:06","InOrOut":1,"money":"0.10"}]
     * other :
     */

    private int code;
    private String msg;
    private String other;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * UUID : ea79f2fa-e16b-8b5d-c7e4-6a2f2437de69
         * RecordReason : 推广员提成
         * RecordDate : 2019-11-25 17:30:02
         * InOrOut : 1
         * money : 0.30
         */

        private String UUID;
        private String RecordReason;
        private String RecordDate;
        private int InOrOut;
        private String money;

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public String getRecordReason() {
            return RecordReason;
        }

        public void setRecordReason(String RecordReason) {
            this.RecordReason = RecordReason;
        }

        public String getRecordDate() {
            return RecordDate;
        }

        public void setRecordDate(String RecordDate) {
            this.RecordDate = RecordDate;
        }

        public int getInOrOut() {
            return InOrOut;
        }

        public void setInOrOut(int InOrOut) {
            this.InOrOut = InOrOut;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
