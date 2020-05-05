package com.example.android.tiaozhan.Entity;

public class FYSMEntity {


    /**
     * code : 2000
     * msg : 获取费用说明成功
     * data : {"siteMoneyInfo":"预付0元,1、因是非合作场馆，平台无法直接将场地费支付给场馆；2、平台将根据比赛结果、所有人有无正常到场并完成比赛，先将其他参与者应付的场地费支付给您（发布者）；3、请您（发布者）再将总场地费支付给场馆方。","field":"0.00","Reward":"1.00","TipsMoney":"发布者用于支付给报名者的费用，报名者均分该打赏费用，打赏费可提高匹配成功率。","Accompany":"0.00","Total":1}
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
         * siteMoneyInfo : 预付0元,1、因是非合作场馆，平台无法直接将场地费支付给场馆；2、平台将根据比赛结果、所有人有无正常到场并完成比赛，先将其他参与者应付的场地费支付给您（发布者）；3、请您（发布者）再将总场地费支付给场馆方。
         * field : 0.00
         * Reward : 1.00
         * TipsMoney : 发布者用于支付给报名者的费用，报名者均分该打赏费用，打赏费可提高匹配成功率。
         * Accompany : 0.00
         * Total : 1
         */

        private String siteMoneyInfo;
        private String field;
        private String Reward;
        private String TipsMoney;
        private String Accompany;
        private String MoneyPerhour;
        private String Total;


        public String getMoneyPerhour() {
            return MoneyPerhour;
        }

        public void setMoneyPerhour(String moneyPerhour) {
            MoneyPerhour = moneyPerhour;
        }




        public String getSiteMoneyInfo() {
            return siteMoneyInfo;
        }

        public void setSiteMoneyInfo(String siteMoneyInfo) {
            this.siteMoneyInfo = siteMoneyInfo;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getReward() {
            return Reward;
        }

        public void setReward(String Reward) {
            this.Reward = Reward;
        }

        public String getTipsMoney() {
            return TipsMoney;
        }

        public void setTipsMoney(String TipsMoney) {
            this.TipsMoney = TipsMoney;
        }

        public String getAccompany() {
            return Accompany;
        }

        public void setAccompany(String Accompany) {
            this.Accompany = Accompany;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }
    }
}
