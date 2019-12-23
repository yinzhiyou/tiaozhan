package com.example.android.promoter.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TYJBMingxiEntity  {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"params":{"/api/getUserGoldLst":null,"goldType":"commonCoins","sportType":null},"nowPage":1,"goldLst":[{"UUID":"26ac58d7-9b44-b602-611c-fd617780275f","GetCoins":5,"Reason":"参与活动奖励","CoinsDate":"2018-12-12 19:00:02"},{"UUID":"4991ace6-0dc3-561a-2f7a-ec16f2b8f7a5","GetCoins":5,"Reason":"新用户注册奖励","CoinsDate":"2018-12-11 14:32:31"},{"UUID":"7a49b401-6ec1-efaa-6151-7b6be5c2e184","GetCoins":5,"Reason":"参与活动奖励","CoinsDate":"2018-12-12 15:41:55"},{"UUID":"9932badc-01a6-1aa4-b316-4bb2d5add82a","GetCoins":5,"Reason":"参与活动奖励","CoinsDate":"2018-12-12 15:30:03"}]}
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
         * params : {"/api/getUserGoldLst":null,"goldType":"commonCoins","sportType":null}
         * nowPage : 1
         * goldLst : [{"UUID":"26ac58d7-9b44-b602-611c-fd617780275f","GetCoins":5,"Reason":"参与活动奖励","CoinsDate":"2018-12-12 19:00:02"},{"UUID":"4991ace6-0dc3-561a-2f7a-ec16f2b8f7a5","GetCoins":5,"Reason":"新用户注册奖励","CoinsDate":"2018-12-11 14:32:31"},{"UUID":"7a49b401-6ec1-efaa-6151-7b6be5c2e184","GetCoins":5,"Reason":"参与活动奖励","CoinsDate":"2018-12-12 15:41:55"},{"UUID":"9932badc-01a6-1aa4-b316-4bb2d5add82a","GetCoins":5,"Reason":"参与活动奖励","CoinsDate":"2018-12-12 15:30:03"}]
         */

        private ParamsBean params;
        private int nowPage;
        private List<GoldLstBean> goldLst;

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public List<GoldLstBean> getGoldLst() {
            return goldLst;
        }

        public void setGoldLst(List<GoldLstBean> goldLst) {
            this.goldLst = goldLst;
        }

        public static class ParamsBean {
            @SerializedName("/api/getUserGoldLst")
            private Object _$ApiGetUserGoldLst27; // FIXME check this code
            private String goldType;
            private Object sportType;

            public Object get_$ApiGetUserGoldLst27() {
                return _$ApiGetUserGoldLst27;
            }

            public void set_$ApiGetUserGoldLst27(Object _$ApiGetUserGoldLst27) {
                this._$ApiGetUserGoldLst27 = _$ApiGetUserGoldLst27;
            }

            public String getGoldType() {
                return goldType;
            }

            public void setGoldType(String goldType) {
                this.goldType = goldType;
            }

            public Object getSportType() {
                return sportType;
            }

            public void setSportType(Object sportType) {
                this.sportType = sportType;
            }
        }

        public static class GoldLstBean {
            /**
             * UUID : 26ac58d7-9b44-b602-611c-fd617780275f
             * GetCoins : 5
             * Reason : 参与活动奖励
             * CoinsDate : 2018-12-12 19:00:02
             */

            private String UUID;
            private String GetCoins;
            private String Reason;
            private String CoinsDate;

            public String getUUID() {
                return UUID;
            }

            public void setUUID(String UUID) {
                this.UUID = UUID;
            }

            public String getGetCoins() {
                return GetCoins;
            }

            public void setGetCoins(String GetCoins) {
                this.GetCoins = GetCoins;
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
        }
    }
}
