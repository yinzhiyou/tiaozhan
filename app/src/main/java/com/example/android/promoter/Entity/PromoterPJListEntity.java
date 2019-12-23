package com.example.android.promoter.Entity;

import java.util.List;

public class PromoterPJListEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":8,"commentLst":[{"uid":"3e302ca1-758c-d8c4-c979-4c79914f3807","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"362ab754-aa9f-e48d-3aff-bfd39b42d619","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试22","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104002307.png","images":[""]},{"uid":"21472756-3565-2800-205a-543623002948","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"362ab754-aa9f-e48d-3aff-bfd39b42d619","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试22","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104002307.png","images":[""]},{"uid":"a0af1f3e-f1cb-eb04-0e85-e824f9cc060c","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"3741f435-ffb4-d258-5e78-6115a4401952","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试23","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104116128.png","images":[""]},{"uid":"cac3c876-68b4-0b05-245b-305e4058c954","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"3741f435-ffb4-d258-5e78-6115a4401952","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试23","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104116128.png","images":[""]},{"uid":"adfea511-f874-3d4f-0b08-1f5a2132d371","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"362ab754-aa9f-e48d-3aff-bfd39b42d619","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试22","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104002307.png","images":[""]}]}
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
         * nowPage : 1
         * total : 8
         * commentLst : [{"uid":"3e302ca1-758c-d8c4-c979-4c79914f3807","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"362ab754-aa9f-e48d-3aff-bfd39b42d619","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试22","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104002307.png","images":[""]},{"uid":"21472756-3565-2800-205a-543623002948","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"362ab754-aa9f-e48d-3aff-bfd39b42d619","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试22","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104002307.png","images":[""]},{"uid":"a0af1f3e-f1cb-eb04-0e85-e824f9cc060c","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"3741f435-ffb4-d258-5e78-6115a4401952","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试23","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104116128.png","images":[""]},{"uid":"cac3c876-68b4-0b05-245b-305e4058c954","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"3741f435-ffb4-d258-5e78-6115a4401952","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试23","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104116128.png","images":[""]},{"uid":"adfea511-f874-3d4f-0b08-1f5a2132d371","site_uid":"da470d6d-68d1-a880-0125-f55d8951d478","user_uid":"362ab754-aa9f-e48d-3aff-bfd39b42d619","cost":0,"xjbScore":"5.0","envscore":"5.0","equscore":"5.0","score":"5.0","content":"场馆很不错","imgcount":0,"imgbaseurl":"","commentDate":"12月05日","goodcomment":0,"nickname":"测试22","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104002307.png","images":[""]}]
         */

        private int nowPage;
        private int total;
        private List<CommentLstBean> commentLst;

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<CommentLstBean> getCommentLst() {
            return commentLst;
        }

        public void setCommentLst(List<CommentLstBean> commentLst) {
            this.commentLst = commentLst;
        }

        public static class CommentLstBean {
            /**
             * uid : 3e302ca1-758c-d8c4-c979-4c79914f3807
             * site_uid : da470d6d-68d1-a880-0125-f55d8951d478
             * user_uid : 362ab754-aa9f-e48d-3aff-bfd39b42d619
             * cost : 0
             * xjbScore : 5.0
             * envscore : 5.0
             * equscore : 5.0
             * score : 5.0
             * content : 场馆很不错
             * imgcount : 0
             * imgbaseurl :
             * commentDate : 12月05日
             * goodcomment : 0
             * nickname : 测试22
             * imgURL : uploads/HeaderImgs/2019-09-17/20190917104002307.png
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
