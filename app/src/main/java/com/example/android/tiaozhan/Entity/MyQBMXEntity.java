package com.example.android.tiaozhan.Entity;

import java.util.List;

public class MyQBMXEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"UUID":"36a6b9c1-72ee-4373-3db8-f5b944b4f865","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"22ccfd10-d1df-e7b4-1c6b-cde09b5da61d","Money":-1,"InOrOut":2,"type":"payMoney","RecordDate":"2019-08-21 22:55:43","RecordReason":"发布活动费用支付","sportName":"羽毛球-单打","StartTime":"2019-08-21 23:00:00"},{"UUID":"a20b9865-98d4-951a-c67b-8166a9d3cad5","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"3ad0ea2b-7b5a-87a9-018f-bdea68583a16","Money":0.02,"InOrOut":1,"type":"promoter","RecordDate":"2019-08-21 22:51:41","RecordReason":"推广员提成费","sportName":"乒乓球-单打","StartTime":"2019-08-20 09:30:00"},{"UUID":"b5b87db5-3238-865e-f319-085f33dd5308","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"424a0125-d625-b5bc-f7ee-985ad633fac7","Money":0.02,"InOrOut":1,"type":"promoter","RecordDate":"2019-08-21 22:51:27","RecordReason":"推广员提成费","sportName":"羽毛球-单打","StartTime":"2019-08-21 17:30:00"},{"UUID":"a8feef8c-032c-47b4-7e23-f671e8b6c56a","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"eaaf8957-7c51-1e74-dd80-16fef27d949a","Money":0,"InOrOut":1,"type":"siteMoney","RecordDate":"2019-08-21 21:43:23","RecordReason":"退还支付的场地费","sportName":"羽毛球-单打","StartTime":"2019-08-21 20:00:00"},{"UUID":"45550c47-f9bc-f6d4-6214-ae684564b2ac","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"e6cded9e-aa53-4f81-1ab2-d136f41ba386","Money":0,"InOrOut":1,"type":"promoter","RecordDate":"2019-08-21 20:02:39","RecordReason":"推广员提成费","sportName":"羽毛球-单打","StartTime":"2019-08-19 14:30:00"},{"UUID":"1077af36-8101-0291-1b77-f20b550f9108","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"cd663464-ed62-33c3-7e29-ad2f7691baa5","Money":0,"InOrOut":1,"type":"siteMoney","RecordDate":"2019-08-21 20:00:54","RecordReason":"审核过后，该活动投诉情况属实，活动已取消，退还陪练费、打赏费","sportName":"羽毛球-单打","StartTime":"2019-08-19 15:30:00"},{"UUID":"e5bb52c6-5e7e-efb3-bbd2-75d7d94b2dad","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"cd663464-ed62-33c3-7e29-ad2f7691baa5","Money":0,"InOrOut":1,"type":"promoter","RecordDate":"2019-08-21 20:00:54","RecordReason":"推广员提成费","sportName":"羽毛球-单打","StartTime":"2019-08-19 15:30:00"},{"UUID":"2e6021c3-e53c-7e8d-0d3c-06b8b9beb79c","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"f11b6a45-4e26-d37b-d3b4-adcc413b0dee","Money":0,"InOrOut":1,"type":"siteMoney","RecordDate":"2019-08-21 20:00:25","RecordReason":"审核过后，该活动投诉情况属实，活动已取消，退还陪练费、打赏费","sportName":"羽毛球-单打","StartTime":"2019-08-17 09:30:00"},{"UUID":"b9783a95-7b90-f0d2-bf88-14f27837ad9f","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"f11b6a45-4e26-d37b-d3b4-adcc413b0dee","Money":0,"InOrOut":1,"type":"promoter","RecordDate":"2019-08-21 20:00:25","RecordReason":"推广员提成费","sportName":"羽毛球-单打","StartTime":"2019-08-17 09:30:00"},{"UUID":"98f1f220-2334-58c9-96a5-e4caf94bc447","PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","publicuid":"eaf76aa1-706e-14a0-d366-9205e98f80dd","Money":0,"InOrOut":1,"type":"promoter","RecordDate":"2019-08-21 18:48:21","RecordReason":"推广员提成费","sportName":"羽毛球-单打","StartTime":"2019-08-21 16:30:00"}]
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
         * UUID : 36a6b9c1-72ee-4373-3db8-f5b944b4f865
         * PlayerUUID : 26d00c58-3004-c05c-bf31-996578f9f5d4
         * publicuid : 22ccfd10-d1df-e7b4-1c6b-cde09b5da61d
         * Money : -1
         * InOrOut : 2
         * type : payMoney
         * RecordDate : 2019-08-21 22:55:43
         * RecordReason : 发布活动费用支付
         * sportName : 羽毛球-单打
         * StartTime : 2019-08-21 23:00:00
         */

        private String UUID;
        private String PlayerUUID;
        private String publicuid;
        private String Money;
        private int InOrOut;
        private String type;
        private String RecordDate;
        private String RecordReason;
        private String sportName;
        private String StartTime;

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

        public String getPublicuid() {
            return publicuid;
        }

        public void setPublicuid(String publicuid) {
            this.publicuid = publicuid;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public int getInOrOut() {
            return InOrOut;
        }

        public void setInOrOut(int InOrOut) {
            this.InOrOut = InOrOut;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRecordDate() {
            return RecordDate;
        }

        public void setRecordDate(String RecordDate) {
            this.RecordDate = RecordDate;
        }

        public String getRecordReason() {
            return RecordReason;
        }

        public void setRecordReason(String RecordReason) {
            this.RecordReason = RecordReason;
        }

        public String getSportName() {
            return sportName;
        }

        public void setSportName(String sportName) {
            this.sportName = sportName;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }
    }
}
