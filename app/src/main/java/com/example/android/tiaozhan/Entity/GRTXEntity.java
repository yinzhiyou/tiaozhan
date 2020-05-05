package com.example.android.tiaozhan.Entity;

import java.util.List;

public class GRTXEntity  {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"uuid":"24c74571-9659-8bf9-b0c5-78b2e35acae7","nickname":"王二二","sex":0,"birthday":"1991-10-15","tall":100,"weight":30,"address":"北京市北京市通州区","comment":"娃娃娃娃屋阿瓦问题","imgURL":"uploads/HeaderImgs/2019-04-09/20190409202701678.png","age":28,"commonCoins":297230,"userEvaluate":"4.8","userPerform":"38%","userHightLevel":{"id":7,"level":"Lv7","mincoins":641,"maxcoins":1280,"name":"羽毛球"},"userLabel":[{"LabelCount":1,"LabelName":"人品不行"},{"LabelCount":1,"LabelName":"球品不行"},{"LabelCount":12,"LabelName":"态度很好"},{"LabelCount":15,"LabelName":"未在场馆签到"},{"LabelCount":12,"LabelName":"技术很棒"},{"LabelCount":1,"LabelName":"技术很差"}],"faveriteSport":"羽毛球,乒乓球,台球,篮球,足球,排球,网球,高尔夫","userTechcoins":[{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":1,"total":652.67,"sport_name":"羽毛球","nowlevel":"Lv7","mincoins":641,"maxcoins":1280,"nextlevel":"Lv8"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":2,"total":510,"sport_name":"乒乓球","nowlevel":"Lv6","mincoins":321,"maxcoins":640,"nextlevel":"Lv7"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":3,"total":10,"sport_name":"台球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":4,"total":10,"sport_name":"篮球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":5,"total":10,"sport_name":"足球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":6,"total":10,"sport_name":"排球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":7,"total":10,"sport_name":"网球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":8,"total":10,"sport_name":"高尔夫","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"}],"isFriends":0,"isFollow":1,"isBlack":0}
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
         * uuid : 24c74571-9659-8bf9-b0c5-78b2e35acae7
         * nickname : 王二二
         * sex : 0
         * birthday : 1991-10-15
         * tall : 100
         * weight : 30
         * address : 北京市北京市通州区
         * comment : 娃娃娃娃屋阿瓦问题
         * imgURL : uploads/HeaderImgs/2019-04-09/20190409202701678.png
         * age : 28
         * commonCoins : 297230
         * userEvaluate : 4.8
         * userPerform : 38%
         * userHightLevel : {"id":7,"level":"Lv7","mincoins":641,"maxcoins":1280,"name":"羽毛球"}
         * userLabel : [{"LabelCount":1,"LabelName":"人品不行"},{"LabelCount":1,"LabelName":"球品不行"},{"LabelCount":12,"LabelName":"态度很好"},{"LabelCount":15,"LabelName":"未在场馆签到"},{"LabelCount":12,"LabelName":"技术很棒"},{"LabelCount":1,"LabelName":"技术很差"}]
         * faveriteSport : 羽毛球,乒乓球,台球,篮球,足球,排球,网球,高尔夫
         * userTechcoins : [{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":1,"total":652.67,"sport_name":"羽毛球","nowlevel":"Lv7","mincoins":641,"maxcoins":1280,"nextlevel":"Lv8"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":2,"total":510,"sport_name":"乒乓球","nowlevel":"Lv6","mincoins":321,"maxcoins":640,"nextlevel":"Lv7"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":3,"total":10,"sport_name":"台球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":4,"total":10,"sport_name":"篮球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":5,"total":10,"sport_name":"足球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":6,"total":10,"sport_name":"排球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":7,"total":10,"sport_name":"网球","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","SportID":8,"total":10,"sport_name":"高尔夫","nowlevel":"Lv1","mincoins":0,"maxcoins":20,"nextlevel":"Lv2"}]
         * isFriends : 0
         * isFollow : 1
         * isBlack : 0
         */

        private String uuid;
        private String nickname;
        private int sex;
        private String birthday;
        private String tall;
        private String weight;
        private String address;
        private String comment;
        private String imgURL;
        private String age;
        private String commonCoins;
        private String userEvaluate;
        private String userPerform;
        private UserHightLevelBean userHightLevel;
        private String faveriteSport;
        private int isFriends;
        private int isFollow;
        private int isBlack;
        private List<UserLabelBean> userLabel;
        private List<UserTechcoinsBean> userTechcoins;
        private List<String> personalImgurl;

        public List<String> getPersonalImgurl() {
            return personalImgurl;
        }

        public void setPersonalImgurl(List<String> personalImgurl) {
            this.personalImgurl = personalImgurl;
        }
        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getTall() {
            return tall;
        }

        public void setTall(String tall) {
            this.tall = tall;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getCommonCoins() {
            return commonCoins;
        }

        public void setCommonCoins(String commonCoins) {
            this.commonCoins = commonCoins;
        }

        public String getUserEvaluate() {
            return userEvaluate;
        }

        public void setUserEvaluate(String userEvaluate) {
            this.userEvaluate = userEvaluate;
        }

        public String getUserPerform() {
            return userPerform;
        }

        public void setUserPerform(String userPerform) {
            this.userPerform = userPerform;
        }

        public UserHightLevelBean getUserHightLevel() {
            return userHightLevel;
        }

        public void setUserHightLevel(UserHightLevelBean userHightLevel) {
            this.userHightLevel = userHightLevel;
        }

        public String getFaveriteSport() {
            return faveriteSport;
        }

        public void setFaveriteSport(String faveriteSport) {
            this.faveriteSport = faveriteSport;
        }

        public int getIsFriends() {
            return isFriends;
        }

        public void setIsFriends(int isFriends) {
            this.isFriends = isFriends;
        }

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public int getIsBlack() {
            return isBlack;
        }

        public void setIsBlack(int isBlack) {
            this.isBlack = isBlack;
        }

        public List<UserLabelBean> getUserLabel() {
            return userLabel;
        }

        public void setUserLabel(List<UserLabelBean> userLabel) {
            this.userLabel = userLabel;
        }

        public List<UserTechcoinsBean> getUserTechcoins() {
            return userTechcoins;
        }

        public void setUserTechcoins(List<UserTechcoinsBean> userTechcoins) {
            this.userTechcoins = userTechcoins;
        }

        public static class UserHightLevelBean {
            /**
             * id : 7
             * level : Lv7
             * mincoins : 641
             * maxcoins : 1280
             * name : 羽毛球
             */

            private int id;


            private int sportID;
            private String level;
            private int mincoins;
            private int maxcoins;
            private String name;
            public int getSportID() {
                return sportID;
            }

            public void setSportID(int sportID) {
                this.sportID = sportID;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public int getMincoins() {
                return mincoins;
            }

            public void setMincoins(int mincoins) {
                this.mincoins = mincoins;
            }

            public int getMaxcoins() {
                return maxcoins;
            }

            public void setMaxcoins(int maxcoins) {
                this.maxcoins = maxcoins;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class UserLabelBean {
            /**
             * LabelCount : 1
             * LabelName : 人品不行
             */

            private int LabelCount;
            private String LabelName;

            public int getLabelCount() {
                return LabelCount;
            }

            public void setLabelCount(int LabelCount) {
                this.LabelCount = LabelCount;
            }

            public String getLabelName() {
                return LabelName;
            }

            public void setLabelName(String LabelName) {
                this.LabelName = LabelName;
            }
        }

        public static class UserTechcoinsBean {
            /**
             * PlayerUUID : 24c74571-9659-8bf9-b0c5-78b2e35acae7
             * SportID : 1
             * total : 652.67
             * sport_name : 羽毛球
             * nowlevel : Lv7
             * mincoins : 641
             * maxcoins : 1280
             * nextlevel : Lv8
             */

            private String PlayerUUID;
            private int SportID;
            private double total;
            private String sport_name;
            private String nowlevel;
            private int mincoins;
            private int maxcoins;
            private String nextlevel;

            public String getPlayerUUID() {
                return PlayerUUID;
            }

            public void setPlayerUUID(String PlayerUUID) {
                this.PlayerUUID = PlayerUUID;
            }

            public int getSportID() {
                return SportID;
            }

            public void setSportID(int SportID) {
                this.SportID = SportID;
            }

            public double getTotal() {
                return total;
            }

            public void setTotal(double total) {
                this.total = total;
            }

            public String getSport_name() {
                return sport_name;
            }

            public void setSport_name(String sport_name) {
                this.sport_name = sport_name;
            }

            public String getNowlevel() {
                return nowlevel;
            }

            public void setNowlevel(String nowlevel) {
                this.nowlevel = nowlevel;
            }

            public int getMincoins() {
                return mincoins;
            }

            public void setMincoins(int mincoins) {
                this.mincoins = mincoins;
            }

            public int getMaxcoins() {
                return maxcoins;
            }

            public void setMaxcoins(int maxcoins) {
                this.maxcoins = maxcoins;
            }

            public String getNextlevel() {
                return nextlevel;
            }

            public void setNextlevel(String nextlevel) {
                this.nextlevel = nextlevel;
            }
        }
    }
}
