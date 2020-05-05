package com.example.android.tiaozhan.Entity;

import java.util.List;

public class JCItemEntity  {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"UUID":"5cf91102-b681-58c2-6ab9-14ec46b11007","playerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","PublicDate":"2018-09-27 17:39:39","Comment":"这里是测试精彩瞬间内容","ContentCount":6,"ContentBasePath":"/uploads/WonderFulImgs/2018-11-08/","SportId":1,"SportName":"羽毛球","isPublic":1,"userInfo":{"nickname":"dfdsfsdf","imgURL":"uploads/2018-11-09/HeaderImgs/20181109201014599.png","sex":0},"commentCount":4,"praiseCount":1,"isPraise":0,"imgPath":["/uploads/WonderFulImgs/2018-11-08/20181108162506822.jpg","/uploads/WonderFulImgs/2018-11-08/20181108141040510.jpg"],"times":"2018-09-27","comments":{"nowPage":1,"total":4,"commentLst":[{"UUID":"5cf91111-b681-58c2-6ab9-14ec46b18813","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf96604-b681-58c2-6ab9-14ec46b14445","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf97041-b681-58c2-6ab9-14ec46b16965","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf98779-b681-58c2-6ab9-14ec46b16572","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""}]}}
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
         * UUID : 5cf91102-b681-58c2-6ab9-14ec46b11007
         * playerUUID : 2d04c573-4052-ea7d-05c6-08f379b02155
         * PublicDate : 2018-09-27 17:39:39
         * Comment : 这里是测试精彩瞬间内容
         * ContentCount : 6
         * ContentBasePath : /uploads/WonderFulImgs/2018-11-08/
         * SportId : 1
         * SportName : 羽毛球
         * isPublic : 1
         * userInfo : {"nickname":"dfdsfsdf","imgURL":"uploads/2018-11-09/HeaderImgs/20181109201014599.png","sex":0}
         * commentCount : 4
         * praiseCount : 1
         * isPraise : 0
         * imgPath : ["/uploads/WonderFulImgs/2018-11-08/20181108162506822.jpg","/uploads/WonderFulImgs/2018-11-08/20181108141040510.jpg"]
         * times : 2018-09-27
         * comments : {"nowPage":1,"total":4,"commentLst":[{"UUID":"5cf91111-b681-58c2-6ab9-14ec46b18813","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf96604-b681-58c2-6ab9-14ec46b14445","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf97041-b681-58c2-6ab9-14ec46b16965","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf98779-b681-58c2-6ab9-14ec46b16572","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""}]}
         */

        private String UUID;
        private String playerUUID;
        private String PublicDate;
        private String Comment;
        private int ContentCount;
        private String ContentBasePath;
        private int SportId;
        private String SportName;
        private int isPublic;
        private UserInfoBean userInfo;
        private int commentCount;
        private int praiseCount;
        private int isPraise;
        private String times;
        private CommentsBean comments;
        private List<String> imgPath;

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

        public String getContentBasePath() {
            return ContentBasePath;
        }

        public void setContentBasePath(String ContentBasePath) {
            this.ContentBasePath = ContentBasePath;
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

        public CommentsBean getComments() {
            return comments;
        }

        public void setComments(CommentsBean comments) {
            this.comments = comments;
        }

        public List<String> getImgPath() {
            return imgPath;
        }

        public void setImgPath(List<String> imgPath) {
            this.imgPath = imgPath;
        }

        public static class UserInfoBean {
            /**
             * nickname : dfdsfsdf
             * imgURL : uploads/2018-11-09/HeaderImgs/20181109201014599.png
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

        public static class CommentsBean {
            /**
             * nowPage : 1
             * total : 4
             * commentLst : [{"UUID":"5cf91111-b681-58c2-6ab9-14ec46b18813","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf96604-b681-58c2-6ab9-14ec46b14445","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf97041-b681-58c2-6ab9-14ec46b16965","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""},{"UUID":"5cf98779-b681-58c2-6ab9-14ec46b16572","date":"2018-09-27","FirendUUID":"8965f02f-2aa3-6001-d968-d6d21d775c1d","Comment":"这里是评论测试内容","userNickName":"12323","userImgURL":""}]
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
                 * UUID : 5cf91111-b681-58c2-6ab9-14ec46b18813
                 * date : 2018-09-27
                 * FirendUUID : 8965f02f-2aa3-6001-d968-d6d21d775c1d
                 * Comment : 这里是评论测试内容
                 * userNickName : 12323
                 * userImgURL :
                 */

                private String UUID;
                private String date;
                private String FirendUUID;
                private String Comment;
                private String userNickName;
                private String userImgURL;

                public String getUUID() {
                    return UUID;
                }

                public void setUUID(String UUID) {
                    this.UUID = UUID;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getFirendUUID() {
                    return FirendUUID;
                }

                public void setFirendUUID(String FirendUUID) {
                    this.FirendUUID = FirendUUID;
                }

                public String getComment() {
                    return Comment;
                }

                public void setComment(String Comment) {
                    this.Comment = Comment;
                }

                public String getUserNickName() {
                    return userNickName;
                }

                public void setUserNickName(String userNickName) {
                    this.userNickName = userNickName;
                }

                public String getUserImgURL() {
                    return userImgURL;
                }

                public void setUserImgURL(String userImgURL) {
                    this.userImgURL = userImgURL;
                }
            }
        }
    }
}
