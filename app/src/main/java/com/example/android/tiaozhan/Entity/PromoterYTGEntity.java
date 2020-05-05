package com.example.android.tiaozhan.Entity;

import java.util.List;

public class PromoterYTGEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"siteUid":"f964cdab-63a5-d7ef-3b0c-f1f755ed7f3f","cg_name":"帝豪体育","city":"天津市","area":"河西区","siteImg":"uploads/Venue/2019-11-04/20191104141753249.jpg","count":0,"status":"0"},{"siteUid":"df5ed135-4751-faa4-58f2-793d25013d76","cg_name":"帝皇娱乐","city":"北京市","area":"朝阳区","siteImg":"uploads/Venue/2019-09-30/20190930110044200.png","count":0,"status":"0"},{"siteUid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","cg_name":"山西省体育健身馆","city":"北京市","area":"朝阳区","siteImg":"uploads/Venue/2019-11-05/20191105144203169.jpg","count":0,"status":"0"},{"siteUid":"91c13f931dc42d8420f01789","cg_name":"太玉园-羽毛球馆","city":"北京市","area":"通州区","siteImg":"","count":0,"status":"0"}]
     * other : {"status0":0,"status1":14,"status2":0}
     */

    private int code;
    private String msg;
    private OtherBean other;
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

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OtherBean {
        /**
         * status0 : 0
         * status1 : 14
         * status2 : 0
         */

        private int status0;
        private int status1;
        private int status2;

        public int getStatus0() {
            return status0;
        }

        public void setStatus0(int status0) {
            this.status0 = status0;
        }

        public int getStatus1() {
            return status1;
        }

        public void setStatus1(int status1) {
            this.status1 = status1;
        }

        public int getStatus2() {
            return status2;
        }

        public void setStatus2(int status2) {
            this.status2 = status2;
        }
    }

    public static class DataBean {
        /**
         * siteUid : f964cdab-63a5-d7ef-3b0c-f1f755ed7f3f
         * cg_name : 帝豪体育
         * city : 天津市
         * area : 河西区
         * siteImg : uploads/Venue/2019-11-04/20191104141753249.jpg
         * count : 0
         * status : 0
         */

        private String siteUid;
        private String cg_name;
        private String city;
        private String area;
        private String siteImg;
        private int count;
        private int status;

        public String getSiteUid() {
            return siteUid;
        }

        public void setSiteUid(String siteUid) {
            this.siteUid = siteUid;
        }

        public String getCg_name() {
            return cg_name;
        }

        public void setCg_name(String cg_name) {
            this.cg_name = cg_name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getSiteImg() {
            return siteImg;
        }

        public void setSiteImg(String siteImg) {
            this.siteImg = siteImg;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
