package com.example.android.tiaozhan.Entity;

import java.util.List;

public class JingcaiEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":20,"Lst":[{"UUID":"65e3576a-91c3-7c10-ab40-66ee580e24ec","playerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","PublicDate":"2018-11-15 17:07:15","Comment":"视频2","ContentCount":1,"SportId":1,"SportName":"羽毛球","isPublic":1,"userInfo":{"nickname":"dfdsfsdf","imgURL":"uploads/2018-11-13/HeaderImgs/20181113140157461.png","sex":0},"fullPath":["/uploads/WonderFulImgs/2018-11-15/20181115170715345.mp4"],"commentCount":0,"praiseCount":0,"isPraise":0,"times":"17:07"}]}
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
         * total : 20
         * Lst : [{"UUID":"65e3576a-91c3-7c10-ab40-66ee580e24ec","playerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","PublicDate":"2018-11-15 17:07:15","Comment":"视频2","ContentCount":1,"SportId":1,"SportName":"羽毛球","isPublic":1,"userInfo":{"nickname":"dfdsfsdf","imgURL":"uploads/2018-11-13/HeaderImgs/20181113140157461.png","sex":0},"fullPath":["/uploads/WonderFulImgs/2018-11-15/20181115170715345.mp4"],"commentCount":0,"praiseCount":0,"isPraise":0,"times":"17:07"}]
         */

        private int nowPage;
        private int total;
        private List<LstBean> Lst;

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

        public List<LstBean> getLst() {
            return Lst;
        }

        public void setLst(List<LstBean> Lst) {
            this.Lst = Lst;
        }

        public static class LstBean {
            /**
             * UUID : 65e3576a-91c3-7c10-ab40-66ee580e24ec
             * playerUUID : 2d04c573-4052-ea7d-05c6-08f379b02155
             * PublicDate : 2018-11-15 17:07:15
             * Comment : 视频2
             * ContentCount : 1
             * SportId : 1
             * SportName : 羽毛球
             * isPublic : 1
             * userInfo : {"nickname":"dfdsfsdf","imgURL":"uploads/2018-11-13/HeaderImgs/20181113140157461.png","sex":0}
             * fullPath : ["/uploads/WonderFulImgs/2018-11-15/20181115170715345.mp4"]
             * commentCount : 0
             * praiseCount : 0
             * isPraise : 0
             * times : 17:07
             */

            private String UUID;
            private String playerUUID;
            private String PublicDate;
            private String Comment;
            private int ContentCount;
            private int SportId;
            private String SportName;
            private int isPublic;
            private UserInfoBean userInfo;
            private int commentCount;
            private int praiseCount;
            private int isPraise;
            private String times;
              private String oneImgs;
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

            public String getPublicDate() {
                return PublicDate;
            }

            public void setPublicDate(String PublicDate) {
                this.PublicDate = PublicDate;
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

            public int getIsPublic() {
                return isPublic;
            }

            public void setIsPublic(int isPublic) {
                this.isPublic = isPublic;
            }

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
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

            public int getIsPraise() {
                return isPraise;
            }

            public void setIsPraise(int isPraise) {
                this.isPraise = isPraise;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public List<String> getFullPath() {
                return fullPath;
            }

            public void setFullPath(List<String> fullPath) {
                this.fullPath = fullPath;
            }

            public static class UserInfoBean {
                /**
                 * nickname : dfdsfsdf
                 * imgURL : uploads/2018-11-13/HeaderImgs/20181113140157461.png
                 * sex : 0
                 */

                private String nickname;
                private String imgURL;
                private int sex;

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

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }
            }
        }
    }
}
