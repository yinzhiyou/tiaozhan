package com.example.android.promoter.Entity;

public class GzXqEntity {

    /**
     * code : 2000
     * msg : 获取成功
     * data : {"tqtc":"A2","wqd":"B2","zc":"B1,A1","cdzt":"无","ProfessionalGoldNotes":"娱乐模式无专用金币输赢","GeneralGoldNotes":"除提前退出,未到场者外,其他都奖励5枚/人","Statementofsitefee":"扣场地费顺序,提前退出方（扣2倍人均场费，由先到后）→迟到方（未签到方扣2倍人均场地费，迟到方扣罚金和1倍人均场费，不分先后）→早退方（扣罚金和1倍人均场费，由先到后）。够整个场地费就不再扣，多扣部分均分所有签到方。如不够，不够部分由正常签到方均摊","ExplanationofRewardFee":"如果有的话,根据缺勤时间扣除一定比例打赏费退还至发布者","Default":"只记未到场方违约，其它提前退出、迟到、早退均不记违约","getRemarks":"活动总时长=30分钟时，缺勤0-7.5分钟，扣20%人均场地费、应得打赏费；7.5-15分钟，扣50%；15分钟以上，扣100%。"}
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
         * tqtc : A2
         * wqd : B2
         * zc : B1,A1
         * cdzt : 无
         * ProfessionalGoldNotes : 娱乐模式无专用金币输赢
         * GeneralGoldNotes : 除提前退出,未到场者外,其他都奖励5枚/人
         * Statementofsitefee : 扣场地费顺序,提前退出方（扣2倍人均场费，由先到后）→迟到方（未签到方扣2倍人均场地费，迟到方扣罚金和1倍人均场费，不分先后）→早退方（扣罚金和1倍人均场费，由先到后）。够整个场地费就不再扣，多扣部分均分所有签到方。如不够，不够部分由正常签到方均摊
         * ExplanationofRewardFee : 如果有的话,根据缺勤时间扣除一定比例打赏费退还至发布者
         * Default : 只记未到场方违约，其它提前退出、迟到、早退均不记违约
         * getRemarks : 活动总时长=30分钟时，缺勤0-7.5分钟，扣20%人均场地费、应得打赏费；7.5-15分钟，扣50%；15分钟以上，扣100%。
         */

        private String tqtc;
        private String wqd;
        private String zc;
        private String cdzt;
        private String ProfessionalGoldNotes;
        private String GeneralGoldNotes;
        private String Statementofsitefee;
        private String ExplanationofRewardFee;
        private String Default;
        private String getRemarks;

        public String getTqtc() {
            return tqtc;
        }

        public void setTqtc(String tqtc) {
            this.tqtc = tqtc;
        }

        public String getWqd() {
            return wqd;
        }

        public void setWqd(String wqd) {
            this.wqd = wqd;
        }

        public String getZc() {
            return zc;
        }

        public void setZc(String zc) {
            this.zc = zc;
        }

        public String getCdzt() {
            return cdzt;
        }

        public void setCdzt(String cdzt) {
            this.cdzt = cdzt;
        }

        public String getProfessionalGoldNotes() {
            return ProfessionalGoldNotes;
        }

        public void setProfessionalGoldNotes(String ProfessionalGoldNotes) {
            this.ProfessionalGoldNotes = ProfessionalGoldNotes;
        }

        public String getGeneralGoldNotes() {
            return GeneralGoldNotes;
        }

        public void setGeneralGoldNotes(String GeneralGoldNotes) {
            this.GeneralGoldNotes = GeneralGoldNotes;
        }

        public String getStatementofsitefee() {
            return Statementofsitefee;
        }

        public void setStatementofsitefee(String Statementofsitefee) {
            this.Statementofsitefee = Statementofsitefee;
        }

        public String getExplanationofRewardFee() {
            return ExplanationofRewardFee;
        }

        public void setExplanationofRewardFee(String ExplanationofRewardFee) {
            this.ExplanationofRewardFee = ExplanationofRewardFee;
        }

        public String getDefault() {
            return Default;
        }

        public void setDefault(String Default) {
            this.Default = Default;
        }

        public String getGetRemarks() {
            return getRemarks;
        }

        public void setGetRemarks(String getRemarks) {
            this.getRemarks = getRemarks;
        }
    }
}
