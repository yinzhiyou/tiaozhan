package com.example.android.tiaozhan.Entity;

import java.util.List;

public class MyjingcaiEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":10,"resLst":[{"UUID":"5cf91102-b681-58c2-6ab9-14ec46b11007","playerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","month":"09","day":"27","time":"17:39","Comment":"这里是测试精彩瞬间内容","ContentCount":6,"SportId":1,"SportName":"羽毛球","fullPath":["/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg"],"commentCount":4,"praiseCount":1}]}
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
         * nowPage : 1
         * total : 10
         * resLst : [{"UUID":"5cf91102-b681-58c2-6ab9-14ec46b11007","playerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","month":"09","day":"27","time":"17:39","Comment":"这里是测试精彩瞬间内容","ContentCount":6,"SportId":1,"SportName":"羽毛球","fullPath":["/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg"],"commentCount":4,"praiseCount":1}]
         */

        private int nowPage;
        private int total;
        private List<ResLstBean> resLst;

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

        public List<ResLstBean> getResLst() {
            return resLst;
        }

        public void setResLst(List<ResLstBean> resLst) {
            this.resLst = resLst;
        }

        public static class ResLstBean {
            /**
             * UUID : 5cf91102-b681-58c2-6ab9-14ec46b11007
             * playerUUID : 2d04c573-4052-ea7d-05c6-08f379b02155
             * month : 09
             * day : 27
             * time : 17:39
             * Comment : 这里是测试精彩瞬间内容
             * ContentCount : 6
             * SportId : 1
             * SportName : 羽毛球
             * fullPath : ["/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg","/public/uploads/2018-09-10/20180910154418379.jpg"]
             * commentCount : 4
             * praiseCount : 1
             */

            private String UUID;
            private String playerUUID;
            private String month;
            private String day;
            private String time;
            private String Comment;

            private String oneImgs;
            private int ContentCount;
            private int SportId;
            private String SportName;
            private int commentCount;
            private int praiseCount;
            private List<String> fullPath;


            public String getOneImgs() {
                return oneImgs;
            }

            public void setOneImgs(String oneImgs) {
                this.oneImgs = oneImgs;
            }


            public String getUUID() {
                return UUID;
            }

            public void setUUID(String UUID) {
                this.UUID = UUID;
            }

            public String getPlayerUUID() {
                return playerUUID;
            }

            public void setPlayerUUID(String playerUUID) {
                this.playerUUID = playerUUID;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getComment() {
                return Comment;
            }

            public void setComment(String Comment) {
                this.Comment = Comment;
            }

            public int getContentCount() {
                return ContentCount;
            }

            public void setContentCount(int ContentCount) {
                this.ContentCount = ContentCount;
            }

            public int getSportId() {
                return SportId;
            }

            public void setSportId(int SportId) {
                this.SportId = SportId;
            }

            public String getSportName() {
                return SportName;
            }

            public void setSportName(String SportName) {
                this.SportName = SportName;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(int praiseCount) {
                this.praiseCount = praiseCount;
            }

            public List<String> getFullPath() {
                return fullPath;
            }

            public void setFullPath(List<String> fullPath) {
                this.fullPath = fullPath;
            }
        }
    }
}
