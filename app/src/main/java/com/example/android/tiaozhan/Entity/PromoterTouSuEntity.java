package com.example.android.tiaozhan.Entity;

import java.util.List;

public class PromoterTouSuEntity {

    /**
     * code : 2000
     * msg : 获取成功
     * data : [{"uuid":"4becc7fd-434a-615e-8504-f1e9686cdbd2","complainName":"裁判未到场","comment":"","imgurl":"","addTime":"2020-03-06 13:04:50","isHandle":0,"playerimgurl":"uploads/HeaderImgs/2020-02-08/20200208122644437.png","nickname":"测试3502","type":2,"refereeimg":[{"refereeimgurl":"uploads/HeaderImgs/2019-06-30/20190630101508136.png","refereeid":"67004120-aa15-3ebb-d159-bda39816fe8a","C":"C1"}],"yuyin":"","status":1,"endcom":"","conclu":"等待推广专员处理"},{"uuid":"3a51352c-e8a2-5539-3f0f-3101a9e20748","complainName":"裁判未到场","comment":"","imgurl":"","addTime":"2020-03-06 13:04:50","isHandle":0,"playerimgurl":"uploads/HeaderImgs/2020-02-08/20200208122644437.png","nickname":"测试3502","type":2,"refereeimg":[{"refereeimgurl":"uploads/HeaderImgs/2019-11-07/20191107152157144.png","refereeid":"f32aca76-7a3e-0682-5c39-88984c998ccf","C":"C3"}],"yuyin":"","status":1,"endcom":"","conclu":"等待推广专员处理"}]
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
         * uuid : 4becc7fd-434a-615e-8504-f1e9686cdbd2
         * complainName : 裁判未到场
         * comment :
         * imgurl :
         * addTime : 2020-03-06 13:04:50
         * isHandle : 0
         * playerimgurl : uploads/HeaderImgs/2020-02-08/20200208122644437.png
         * nickname : 测试3502
         * type : 2
         * refereeimg : [{"refereeimgurl":"uploads/HeaderImgs/2019-06-30/20190630101508136.png","refereeid":"67004120-aa15-3ebb-d159-bda39816fe8a","C":"C1"}]
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
             * refereeimgurl : uploads/HeaderImgs/2019-06-30/20190630101508136.png
             * refereeid : 67004120-aa15-3ebb-d159-bda39816fe8a
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
