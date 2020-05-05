package com.example.android.tiaozhan.Entity;

import java.util.List;

public class CGXXEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","name":"山西省体育健身馆","telephone":"18810902033","lat":"39.8770090","lng":"116.7014060","province":"北京市","city":"北京市","area":"朝阳区","address":"吕梁市汾阳市英雄中路17号","detail":0,"isregistedsite":1,"comment":"承接羽毛球、网球、篮球、台球、乒乓球比赛","addres":"","scores":5,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","isCooper":1,"siteInfoext":{"siteuuid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","parking":1,"wifi":1,"shower":1},"supportSportName":"羽毛球 乒乓球 台球 篮球 足球 排球 网球 高尔夫","supportSportID":[{"name":"羽毛球","id":1},{"name":"乒乓球","id":2},{"name":"台球","id":3},{"name":"篮球","id":4},{"name":"足球","id":5},{"name":"排球","id":6},{"name":"网球","id":7},{"name":"高尔夫","id":8}],"commentsCount":295,"comments":[{"uid":"31d2c6a6-623a-7592-dde3-576c409c5227","site_uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","user_uid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"11月11日","goodcomment":0,"nickname":"xxoo","imgURL":"uploads/HeaderImgs/2019-08-14/20190814184059613.png","images":[""]},{"uid":"a14f9388-0c1e-aa93-d41a-20faf850e016","site_uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","user_uid":"4fc781ef-72cf-91c3-9b23-398647bbe7cc","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"11月11日","goodcomment":0,"nickname":"测试30","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105226317.png","images":[""]},{"uid":"a3ba36b3-844b-0aaf-2a8c-19e756514cf0","site_uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","user_uid":"cc3ba085-5f4d-0a4c-f347-c7bf53d900b8","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"11月11日","goodcomment":0,"nickname":"测试60","imgURL":"uploads/HeaderImgs/2019-09-18/20190918114311958.png","images":[""]}],"filesURL":["uploads/Venue/2019-09-06/20190906171119306.png","uploads/Venue/2019-09-06/20190906171124920.png"],"starttime":"00:00","endtime":"24:00"}
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
         * uid : 94da6c9c-8ced-d0e2-d54f-ad690d247134
         * name : 山西省体育健身馆
         * telephone : 18810902033
         * lat : 39.8770090
         * lng : 116.7014060
         * province : 北京市
         * city : 北京市
         * area : 朝阳区
         * address : 吕梁市汾阳市英雄中路17号
         * detail : 0
         * isregistedsite : 1
         * comment : 承接羽毛球、网球、篮球、台球、乒乓球比赛
         * addres :
         * scores : 5
         * xjbScore : 5.0
         * envscore : 5.0
         * equscore : 5.0
         * isCooper : 1
         * siteInfoext : {"siteuuid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","parking":1,"wifi":1,"shower":1}
         * supportSportName : 羽毛球 乒乓球 台球 篮球 足球 排球 网球 高尔夫
         * supportSportID : [{"name":"羽毛球","id":1},{"name":"乒乓球","id":2},{"name":"台球","id":3},{"name":"篮球","id":4},{"name":"足球","id":5},{"name":"排球","id":6},{"name":"网球","id":7},{"name":"高尔夫","id":8}]
         * commentsCount : 295
         * comments : [{"uid":"31d2c6a6-623a-7592-dde3-576c409c5227","site_uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","user_uid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"11月11日","goodcomment":0,"nickname":"xxoo","imgURL":"uploads/HeaderImgs/2019-08-14/20190814184059613.png","images":[""]},{"uid":"a14f9388-0c1e-aa93-d41a-20faf850e016","site_uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","user_uid":"4fc781ef-72cf-91c3-9b23-398647bbe7cc","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"11月11日","goodcomment":0,"nickname":"测试30","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105226317.png","images":[""]},{"uid":"a3ba36b3-844b-0aaf-2a8c-19e756514cf0","site_uid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","user_uid":"cc3ba085-5f4d-0a4c-f347-c7bf53d900b8","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"11月11日","goodcomment":0,"nickname":"测试60","imgURL":"uploads/HeaderImgs/2019-09-18/20190918114311958.png","images":[""]}]
         * filesURL : ["uploads/Venue/2019-09-06/20190906171119306.png","uploads/Venue/2019-09-06/20190906171124920.png"]
         * starttime : 00:00
         * endtime : 24:00
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
        private String comment;
        private String addres;
        private String scores;
        private String xjbScore;
        private String envscore;
        private String equscore;
        private int isCooper;
        private SiteInfoextBean siteInfoext;
        private String supportSportName;
        private int commentsCount;
        private String starttime;
        private String endtime;
        private List<SupportSportIDBean> supportSportID;
        private List<CommentsBean> comments;
        private String[] filesURL;

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

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAddres() {
            return addres;
        }

        public void setAddres(String addres) {
            this.addres = addres;
        }

        public String getScores() {
            return scores;
        }

        public void setScores(String scores) {
            this.scores = scores;
        }

        public String getXjbScore() {
            return xjbScore;
        }

        public void setXjbScore(String xjbScore) {
            this.xjbScore = xjbScore;
        }

        public String getEnvscore() {
            return envscore;
        }

        public void setEnvscore(String envscore) {
            this.envscore = envscore;
        }

        public String getEquscore() {
            return equscore;
        }

        public void setEquscore(String equscore) {
            this.equscore = equscore;
        }

        public int getIsCooper() {
            return isCooper;
        }

        public void setIsCooper(int isCooper) {
            this.isCooper = isCooper;
        }

        public SiteInfoextBean getSiteInfoext() {
            return siteInfoext;
        }

        public void setSiteInfoext(SiteInfoextBean siteInfoext) {
            this.siteInfoext = siteInfoext;
        }

        public String getSupportSportName() {
            return supportSportName;
        }

        public void setSupportSportName(String supportSportName) {
            this.supportSportName = supportSportName;
        }

        public int getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(int commentsCount) {
            this.commentsCount = commentsCount;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public List<SupportSportIDBean> getSupportSportID() {
            return supportSportID;
        }

        public void setSupportSportID(List<SupportSportIDBean> supportSportID) {
            this.supportSportID = supportSportID;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public String[] getFilesURL() {
            return filesURL;
        }

        public void setFilesURL(String[] filesURL) {
            this.filesURL = filesURL;
        }

        public static class SiteInfoextBean {
            /**
             * siteuuid : 94da6c9c-8ced-d0e2-d54f-ad690d247134
             * parking : 1
             * wifi : 1
             * shower : 1
             */

            private String siteuuid;
            private int parking;
            private int wifi;
            private int shower;

            public String getSiteuuid() {
                return siteuuid;
            }

            public void setSiteuuid(String siteuuid) {
                this.siteuuid = siteuuid;
            }

            public int getParking() {
                return parking;
            }

            public void setParking(int parking) {
                this.parking = parking;
            }

            public int getWifi() {
                return wifi;
            }

            public void setWifi(int wifi) {
                this.wifi = wifi;
            }

            public int getShower() {
                return shower;
            }

            public void setShower(int shower) {
                this.shower = shower;
            }
        }

        public static class SupportSportIDBean {
            /**
             * name : 羽毛球
             * id : 1
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class CommentsBean {
            /**
             * uid : 31d2c6a6-623a-7592-dde3-576c409c5227
             * site_uid : 94da6c9c-8ced-d0e2-d54f-ad690d247134
             * user_uid : b9e420dc-b95b-8d59-8e24-836f0d8667ac
             * cost : 0
             * xjbScore : 5.0
             * envscore : 5.0
             * equscore : 5.0
             * score : 5.0
             * content : 场馆很不错
             * imgcount : 0
             * imgbaseurl :
             * commentDate : 11月11日
             * goodcomment : 0
             * nickname : xxoo
             * imgURL : uploads/HeaderImgs/2019-08-14/20190814184059613.png
             * images : [""]
             */

            private String uid;
            private String site_uid;
            private String user_uid;
            private int cost;
            private String xjbScore;
            private String envscore;
            private String equscore;
            private float score;
            private String content;
            private int imgcount;
            private String imgbaseurl;
            private String commentDate;
            private int goodcomment;
            private String nickname;
            private String imgURL;
            private List<String> images;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getSite_uid() {
                return site_uid;
            }

            public void setSite_uid(String site_uid) {
                this.site_uid = site_uid;
            }

            public String getUser_uid() {
                return user_uid;
            }

            public void setUser_uid(String user_uid) {
                this.user_uid = user_uid;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public String getXjbScore() {
                return xjbScore;
            }

            public void setXjbScore(String xjbScore) {
                this.xjbScore = xjbScore;
            }

            public String getEnvscore() {
                return envscore;
            }

            public void setEnvscore(String envscore) {
                this.envscore = envscore;
            }

            public String getEquscore() {
                return equscore;
            }

            public void setEquscore(String equscore) {
                this.equscore = equscore;
            }

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getImgcount() {
                return imgcount;
            }

            public void setImgcount(int imgcount) {
                this.imgcount = imgcount;
            }

            public String getImgbaseurl() {
                return imgbaseurl;
            }

            public void setImgbaseurl(String imgbaseurl) {
                this.imgbaseurl = imgbaseurl;
            }

            public String getCommentDate() {
                return commentDate;
            }

            public void setCommentDate(String commentDate) {
                this.commentDate = commentDate;
            }

            public int getGoodcomment() {
                return goodcomment;
            }

            public void setGoodcomment(int goodcomment) {
                this.goodcomment = goodcomment;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
