package com.example.android.promoter.Entity;

import java.util.List;

public class PromoterFJCGEntity {


    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"siteimgs":"","uid":"012hcdb3e9cc3c5bbbf729ok","name":"1北京市花石匠体育场","fraction":5,"range":"0.41Km","type":"非合作场馆"},{"siteimgs":"","uid":"03565a2e16a83d1c2e47333f","name":"通州时代体育羽毛球乒乓球馆","fraction":5,"range":"2.72Km","type":"非合作场馆"},{"siteimgs":"","uid":"0d69412d4b9b9d14b23c81c6","name":"通州区篮球场","fraction":5,"range":"5.11Km","type":"非合作场馆"},{"siteimgs":"","uid":"0d1a264bdc84ae539d82ea61","name":"来力台球会所","fraction":5,"range":"5.49Km","type":"非合作场馆"},{"siteimgs":"","uid":"09185c56d3717f44f1193711","name":"天创好友台球厅","fraction":5,"range":"5.98Km","type":"非合作场馆"},{"siteimgs":"","uid":"01a624e9d4d1b7649f09f737","name":"北京工业大学通州校区篮球场","fraction":5,"range":"6.96Km","type":"非合作场馆"},{"siteimgs":"","uid":"0dd632b3953d00b8eedfca60","name":"中少五彩足球俱乐部北京有限公司","fraction":5,"range":"8.64Km","type":"非合作场馆"},{"siteimgs":"","uid":"05802c1b3255fdba373a71d9","name":"三河燕郊京华高尔夫俱乐部有限公司工会委员会","fraction":5,"range":"9.77Km","type":"非合作场馆"},{"siteimgs":"","uid":"06029838a839c3b39ef77972","name":"零度台球俱乐部","fraction":5,"range":"11.06Km","type":"非合作场馆"},{"siteimgs":"","uid":"02e4229139d27556d2f49e61","name":"友加台球","fraction":5,"range":"12.55Km","type":"非合作场馆"}]
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
         * siteimgs :
         * uid : 012hcdb3e9cc3c5bbbf729ok
         * name : 1北京市花石匠体育场
         * fraction : 5
         * range : 0.41Km
         * type : 非合作场馆
         */

        private String siteimgs;
        private String uid;
        private String name;
        private int fraction;
        private String range;
        private String type;

        private int compcount;

        public int getCompcount() {
            return compcount;
        }

        public void setCompcount(int compcount) {
            this.compcount = compcount;
        }


        public String getSiteimgs() {
            return siteimgs;
        }

        public void setSiteimgs(String siteimgs) {
            this.siteimgs = siteimgs;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFraction() {
            return fraction;
        }

        public void setFraction(int fraction) {
            this.fraction = fraction;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
