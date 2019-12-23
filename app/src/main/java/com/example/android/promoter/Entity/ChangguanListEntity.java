package com.example.android.promoter.Entity;

import java.util.List;

public class ChangguanListEntity {


    /**
     * code : 2000
     * msg : 获取场馆列表成功
     * data : {"total":516,"nowPage":1,"sitelst":[{"uid":"51783881-0805-f298-40ae-81201ed57872","name":"甲乙电子飞飞飞","telephone":"18514253569","lat":"39.8771170","lng":"116.7012130","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区土桥中路华远·好天地内,啤酒花园西北76米","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"0.00km","scores":"5.0"},{"uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","name":"山西省体育健身馆","telephone":"18810902033","lat":"39.8770090","lng":"116.7014060","province":"北京市","city":"北京市","area":"朝阳区","address":"吕梁市汾阳市英雄中路17号","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-11-05/20191105144203169.jpg","isCooper":1,"range":"0.02km","scores":"5.0"},{"uid":"47ef4bfe-00c5-7c33-1e50-4ed5d6e0a988","name":"北京羽乐球馆","telephone":"18513552811","lat":"39.8768714","lng":"116.7016435","province":"北京市","city":"北京市","area":"通州区","address":"华远好天地a坐1107","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-11-07/20191107094456686.jpg","isCooper":1,"range":"0.05km","scores":"4.9"},{"uid":"27f1b9d3-991a-c7a1-b9ae-c655964a4e15","name":"好天地球场2号馆","telephone":"","lat":"39.8766182","lng":"116.7023455","province":"北京市","city":"北京市","area":"通州区","address":"砖厂南里46号A坐1109","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-10-17/20191017143517422.jpg","isCooper":1,"range":"0.11km","scores":5},{"uid":"df647a9a-69ed-23b8-9b8e-d7065afa7c78","name":"金隅花石匠体育馆","telephone":"12345678901","lat":"39.8798303","lng":"116.7038122","province":"北京市","city":"北京市","area":"通州区","address":"二期西区126号楼","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-11-05/20191105153047325.jpg","isCooper":1,"range":"0.38km","scores":5},{"uid":"012hcdb3e9cc3c5bbbf729ok","name":"1北京市花石匠体育场","telephone":"80895077","lat":"39.8798230","lng":"116.7041550","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区砖厂北里126号楼","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"0.39km","scores":"3.6"},{"uid":"828a996e617932bed6b74e7e","name":"健龙森体育健身俱乐部-羽毛球1号馆","telephone":"(010)81556565","lat":"39.8985530","lng":"116.7093810","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区滨河中路108号健龙森体育健身俱乐部羽毛球馆","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"2.49km","scores":"5.0"},{"uid":"3476c8dbffbfd728cc06a407","name":"健龙森体育健身俱乐部-羽毛球二号馆","telephone":"(010)81556969","lat":"39.8982980","lng":"116.7107040","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区滨河中路108号","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"2.49km","scores":"5.0"},{"uid":"8b2fa485586f260e246e9898","name":"健龙森羽毛球馆(通州店)","telephone":"(010)81592388","lat":"39.8985230","lng":"116.7106480","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区滨河中路108号","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"2.52km","scores":5},{"uid":"9115f3a7cb579e3d43afe699","name":"天天羽毛球馆","telephone":"(010)51674091","lat":"39.8935770","lng":"116.6701300","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区九棵树145号","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"3.23km","scores":5}]}
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
         * total : 516
         * nowPage : 1
         * sitelst : [{"uid":"51783881-0805-f298-40ae-81201ed57872","name":"甲乙电子飞飞飞","telephone":"18514253569","lat":"39.8771170","lng":"116.7012130","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区土桥中路华远·好天地内,啤酒花园西北76米","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"0.00km","scores":"5.0"},{"uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","name":"山西省体育健身馆","telephone":"18810902033","lat":"39.8770090","lng":"116.7014060","province":"北京市","city":"北京市","area":"朝阳区","address":"吕梁市汾阳市英雄中路17号","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-11-05/20191105144203169.jpg","isCooper":1,"range":"0.02km","scores":"5.0"},{"uid":"47ef4bfe-00c5-7c33-1e50-4ed5d6e0a988","name":"北京羽乐球馆","telephone":"18513552811","lat":"39.8768714","lng":"116.7016435","province":"北京市","city":"北京市","area":"通州区","address":"华远好天地a坐1107","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-11-07/20191107094456686.jpg","isCooper":1,"range":"0.05km","scores":"4.9"},{"uid":"27f1b9d3-991a-c7a1-b9ae-c655964a4e15","name":"好天地球场2号馆","telephone":"","lat":"39.8766182","lng":"116.7023455","province":"北京市","city":"北京市","area":"通州区","address":"砖厂南里46号A坐1109","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-10-17/20191017143517422.jpg","isCooper":1,"range":"0.11km","scores":5},{"uid":"df647a9a-69ed-23b8-9b8e-d7065afa7c78","name":"金隅花石匠体育馆","telephone":"12345678901","lat":"39.8798303","lng":"116.7038122","province":"北京市","city":"北京市","area":"通州区","address":"二期西区126号楼","detail":0,"isregistedsite":1,"siteimg":"uploads/Venue/2019-11-05/20191105153047325.jpg","isCooper":1,"range":"0.38km","scores":5},{"uid":"012hcdb3e9cc3c5bbbf729ok","name":"1北京市花石匠体育场","telephone":"80895077","lat":"39.8798230","lng":"116.7041550","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区砖厂北里126号楼","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"0.39km","scores":"3.6"},{"uid":"828a996e617932bed6b74e7e","name":"健龙森体育健身俱乐部-羽毛球1号馆","telephone":"(010)81556565","lat":"39.8985530","lng":"116.7093810","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区滨河中路108号健龙森体育健身俱乐部羽毛球馆","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"2.49km","scores":"5.0"},{"uid":"3476c8dbffbfd728cc06a407","name":"健龙森体育健身俱乐部-羽毛球二号馆","telephone":"(010)81556969","lat":"39.8982980","lng":"116.7107040","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区滨河中路108号","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"2.49km","scores":"5.0"},{"uid":"8b2fa485586f260e246e9898","name":"健龙森羽毛球馆(通州店)","telephone":"(010)81592388","lat":"39.8985230","lng":"116.7106480","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区滨河中路108号","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"2.52km","scores":5},{"uid":"9115f3a7cb579e3d43afe699","name":"天天羽毛球馆","telephone":"(010)51674091","lat":"39.8935770","lng":"116.6701300","province":"北京市","city":"北京市","area":"通州区","address":"北京市通州区九棵树145号","detail":0,"isregistedsite":0,"siteimg":"","isCooper":0,"range":"3.23km","scores":5}]
         */

        private int total;
        private int nowPage;
        private List<SitelstBean> sitelst;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public List<SitelstBean> getSitelst() {
            return sitelst;
        }

        public void setSitelst(List<SitelstBean> sitelst) {
            this.sitelst = sitelst;
        }

        public static class SitelstBean {
            /**
             * uid : 51783881-0805-f298-40ae-81201ed57872
             * name : 甲乙电子飞飞飞
             * telephone : 18514253569
             * lat : 39.8771170
             * lng : 116.7012130
             * province : 北京市
             * city : 北京市
             * area : 通州区
             * address : 北京市通州区土桥中路华远·好天地内,啤酒花园西北76米
             * detail : 0
             * isregistedsite : 0
             * siteimg :
             * isCooper : 0
             * range : 0.00km
             * scores : 5.0
             */

            private String uid;
            private String name;
            private String telephone;
            private String lat;
            private String lng;
            private String province;
            private String city;
            private String area;
            private String address;
            private int detail;
            private int isregistedsite;
            private String siteimg;
            private int isCooper;
            private String range;
            private float scores;

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

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getDetail() {
                return detail;
            }

            public void setDetail(int detail) {
                this.detail = detail;
            }

            public int getIsregistedsite() {
                return isregistedsite;
            }

            public void setIsregistedsite(int isregistedsite) {
                this.isregistedsite = isregistedsite;
            }

            public String getSiteimg() {
                return siteimg;
            }

            public void setSiteimg(String siteimg) {
                this.siteimg = siteimg;
            }

            public int getIsCooper() {
                return isCooper;
            }

            public void setIsCooper(int isCooper) {
                this.isCooper = isCooper;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public float getScores() {
                return scores;
            }

            public void setScores(float scores) {
                this.scores = scores;
            }
        }
    }
}
