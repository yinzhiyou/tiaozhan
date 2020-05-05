package com.example.android.tiaozhan.Entity;

import java.util.List;

public class MyComplaintListEntity {


    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"uuid":"d435a9a0-02de-5a9a-ba47-c2c9f46f95ef","complainName":"裁判未到场","comment":"没到场","imgurl":"uploads/Audio/2020-03-09/20200309155335649.mp3","addTime":"2020-03-09 15:53:36","isHandle":0,"playerimgurl":"uploads/HeaderImgs/2019-09-17/20190917110306248.png","nickname":"测试42","type":2,"signs":"1","refereeimg":[{"refereeimgurl":"uploads/HeaderImgs/2019-10-12/20191012161420756.png","refereeid":"c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba","C":"C1"}],"yuyin":"","status":1,"endcom":"","conclu":"等待推广专员处理"},{"uuid":"294f5bc1-b5e0-7b0d-a3bf-e469d97030b1","complainName":"场馆未预留场地","comment":"留了","imgurl":"uploads/Audio/2020-03-09/20200309155247777.mp3","addTime":"2020-03-09 15:52:48","isHandle":2,"playerimgurl":"uploads/HeaderImgs/2019-09-17/20190917110306248.png","nickname":"测试42","type":1,"signs":"1","yuyin":"","status":5,"endcom":"用户不同意推广专员的处理结果，平台已介入","conclu":"等待平台受理"}]
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
         * uuid : d435a9a0-02de-5a9a-ba47-c2c9f46f95ef
         * complainName : 裁判未到场
         * comment : 没到场
         * imgurl : uploads/Audio/2020-03-09/20200309155335649.mp3
         * addTime : 2020-03-09 15:53:36
         * isHandle : 0
         * playerimgurl : uploads/HeaderImgs/2019-09-17/20190917110306248.png
         * nickname : 测试42
         * type : 2
         * signs : 1
         * refereeimg : [{"refereeimgurl":"uploads/HeaderImgs/2019-10-12/20191012161420756.png","refereeid":"c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba","C":"C1"}]
         * yuyin :
         * status : 1
         * endcom :
         * conclu : 等待推广专员处理
         */

        private String uuid;
        private String complainName;
        private String comment;
        private String imgurl;
        private String addTime;
        private int isHandle;
        private String playerimgurl;
        private String nickname;
        private int type;
        private String signs;
        private String yuyin;
        private int status;
        private String endcom;
        private String conclu;
        private List<RefereeimgBean> refereeimg;


        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getComplainName() {
            return complainName;
        }

        public void setComplainName(String complainName) {
            this.complainName = complainName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getIsHandle() {
            return isHandle;
        }

        public void setIsHandle(int isHandle) {
            this.isHandle = isHandle;
        }

        public String getPlayerimgurl() {
            return playerimgurl;
        }

        public void setPlayerimgurl(String playerimgurl) {
            this.playerimgurl = playerimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSigns() {
            return signs;
        }

        public void setSigns(String signs) {
            this.signs = signs;
        }

        public String getYuyin() {
            return yuyin;
        }

        public void setYuyin(String yuyin) {
            this.yuyin = yuyin;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getEndcom() {
            return endcom;
        }

        public void setEndcom(String endcom) {
            this.endcom = endcom;
        }

        public String getConclu() {
            return conclu;
        }

        public void setConclu(String conclu) {
            this.conclu = conclu;
        }

        public List<RefereeimgBean> getRefereeimg() {
            return refereeimg;
        }

        public void setRefereeimg(List<RefereeimgBean> refereeimg) {
            this.refereeimg = refereeimg;
        }

        public static class RefereeimgBean {
            /**
             * refereeimgurl : uploads/HeaderImgs/2019-10-12/20191012161420756.png
             * refereeid : c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba
             * C : C1
             */

            private String refereeimgurl;
            private String refereeid;
            private String C;

            public String getRefereeimgurl() {
                return refereeimgurl;
            }

            public void setRefereeimgurl(String refereeimgurl) {
                this.refereeimgurl = refereeimgurl;
            }

            public String getRefereeid() {
                return refereeid;
            }

            public void setRefereeid(String refereeid) {
                this.refereeid = refereeid;
            }

            public String getC() {
                return C;
            }

            public void setC(String C) {
                this.C = C;
            }
        }
    }
}
