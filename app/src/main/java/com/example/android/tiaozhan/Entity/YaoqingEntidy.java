package com.example.android.tiaozhan.Entity;

import java.util.List;

public class YaoqingEntidy  {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":1,"Lst":[{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","FriendUUID":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","FriendCustomGroupID":0,"nickName":"王大大","userInfo":{"nickname":"王大大","sex":0,"imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","tall":180,"weight":80,"age":1},"sportidNameStr":"羽毛球,乒乓球,台球","techcoins":10,"userLevel":"1"}]}
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
         * total : 1
         * Lst : [{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","FriendUUID":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","FriendCustomGroupID":0,"nickName":"王大大","userInfo":{"nickname":"王大大","sex":0,"imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","tall":180,"weight":80,"age":1},"sportidNameStr":"羽毛球,乒乓球,台球","techcoins":10,"userLevel":"1"}]
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
             * PlayerUUID : 2d04c573-4052-ea7d-05c6-08f379b02155
             * FriendUUID : 54ca24d3-0f6b-2abe-ae50-3808e4eda735
             * FriendCustomGroupID : 0
             * nickName : 王大大
             * userInfo : {"nickname":"王大大","sex":0,"imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","tall":180,"weight":80,"age":1}
             * sportidNameStr : 羽毛球,乒乓球,台球
             * techcoins : 10
             * userLevel : 1
             */

            private String PlayerUUID;
            private String FriendUUID;
            private int FriendCustomGroupID;
            private String nickName;
            private UserInfoBean userInfo;
            private String sportidNameStr;
            private double techcoins;
            private String userLevel;

            private String range;

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public String getPlayerUUID() {
                return PlayerUUID;
            }

            public void setPlayerUUID(String PlayerUUID) {
                this.PlayerUUID = PlayerUUID;
            }

            public String getFriendUUID() {
                return FriendUUID;
            }

            public void setFriendUUID(String FriendUUID) {
                this.FriendUUID = FriendUUID;
            }

            public int getFriendCustomGroupID() {
                return FriendCustomGroupID;
            }

            public void setFriendCustomGroupID(int FriendCustomGroupID) {
                this.FriendCustomGroupID = FriendCustomGroupID;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public String getSportidNameStr() {
                return sportidNameStr;
            }

            public void setSportidNameStr(String sportidNameStr) {
                this.sportidNameStr = sportidNameStr;
            }

            public double getTechcoins() {
                return techcoins;
            }

            public void setTechcoins(double techcoins) {
                this.techcoins = techcoins;
            }

            public String getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(String userLevel) {
                this.userLevel = userLevel;
            }

            public static class UserInfoBean {
                /**
                 * nickname : 王大大
                 * sex : 0
                 * imgURL : uploads/2018-11-13/HeaderImgs/20181113200402128.png
                 * tall : 180
                 * weight : 80
                 * age : 1
                 */

                private String nickname;
                private int sex;
                private String imgURL;
                private int tall;
                private int weight;
                private int age;

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getImgURL() {
                    return imgURL;
                }

                public void setImgURL(String imgURL) {
                    this.imgURL = imgURL;
                }

                public int getTall() {
                    return tall;
                }

                public void setTall(int tall) {
                    this.tall = tall;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }
            }
        }
    }
}
