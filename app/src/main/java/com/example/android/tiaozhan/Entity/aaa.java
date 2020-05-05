package com.example.android.tiaozhan.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class aaa {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"uuid":"5058b7b5-f89a-9eff-f260-2941a2c9f638","wechartID":"","zfbID":"","account":"","nickname":"测试31号","realname":"","telephone":"13500000031","PhoneKey":"","sex":0,"birthday":"2006-09-17","tall":null,"weight":null,"address":"北京市,北京市,通州区","comment":null,"personalImgurl":["uploads/Personalprofile/2020-04-22/20200422132354295.png","uploads/Personalprofile/2020-04-22/20200422132354707.png"],"lat":"39.8866510","lng":"116.6898290","imgURL":"uploads/HeaderImgs/2020-04-16/20200416191133733.png","Password":"$2y$10$l7M0OoN/Vz1drUOHxb8PCekrwUNPUJVatzkf8AnClnEtoxps8CAb6","MoneyPassword":"$2y$10$/frX4NXINWiqYzrS25mCTuEZJSqGeTAUUCKhLEpzf5E6MElWf9L7m","RealNameRegistration":1,"NearBy":1,"acceptNearplayerInvite":1,"acceptFirendInvite":1,"loginType":1,"FinishedInfo":0,"status":0,"vipLevel":0,"vipTime":"0000-00-00 00:00:00","HeadImageURL":"","RegistDate":"2019-07-24 14:10:15","imNumber":"5058b7b5f89a9efff2602941a2c9f638","PromotId":null,"isPromoter":0,"isPromoterstop":0,"Royalty":0,"isPromotertime":null,"PromoterStadium":null,"Totalthroughput":0,"Treuthroughput":0,"Invitation":null,"FatherID":null,"Referee":0,"age":14,"commonCoins":392.5,"userEvaluate":5,"userPerform":"17.95%","userHightLevel":{"id":10,"level":"Lv10","grade":10,"mincoins":5210,"maxcoins":999999,"winCoins":1280,"name":"网球","sportID":7},"userLabel":[{"LabelName":"客观填写比赛结果","labelSort":4,"LabelCount":93},{"LabelName":"填写结果不客观","labelSort":13,"LabelCount":5},{"LabelName":"很热情","labelSort":2,"LabelCount":3},{"LabelName":"很大度","labelSort":3,"LabelCount":3},{"LabelName":"神准时","labelSort":1,"LabelCount":0},{"LabelName":"严格遵守比赛规则","labelSort":5,"LabelCount":0}],"faveriteSport":"排球/菜鸟,羽毛球/菜鸟,台球/菜鸟,乒乓球/初级","userTechcoins":[{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":1,"total":427.06,"sport_name":"羽毛球","nowlevel":"Lv6","mincoins":320,"maxcoins":640,"nextlevel":"Lv7"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":2,"total":1684,"spor 2020-04-22 14:11:07.513 12826-12826/com.example.android.tiaozhan D/1608: t_name":"乒乓球","nowlevel":"Lv8","mincoins":1280,"maxcoins":2560,"nextlevel":"Lv9"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":3,"total":3069,"sport_name":"台球","nowlevel":"Lv9","mincoins":2560,"maxcoins":5210,"nextlevel":"Lv10"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":4,"total":878.69,"sport_name":"篮球","nowlevel":"Lv7","mincoins":640,"maxcoins":1280,"nextlevel":"Lv8"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":5,"total":15,"sport_name":"足球","nowlevel":"Lv1","mincoins":"0","maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":6,"total":486.31,"sport_name":"排球","nowlevel":"Lv6","mincoins":320,"maxcoins":640,"nextlevel":"Lv7"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":7,"total":7816,"sport_name":"网球","nowlevel":"Lv10","mincoins":5210,"maxcoins":999999,"nextlevel":"Lv2"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":8,"total":128,"sport_name":"高尔夫","nowlevel":"Lv4","mincoins":80,"maxcoins":160,"nextlevel":"Lv5"}]}
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
         * uuid : 5058b7b5-f89a-9eff-f260-2941a2c9f638
         * wechartID :
         * zfbID :
         * account :
         * nickname : 测试31号
         * realname :
         * telephone : 13500000031
         * PhoneKey :
         * sex : 0
         * birthday : 2006-09-17
         * tall : null
         * weight : null
         * address : 北京市,北京市,通州区
         * comment : null
         * personalImgurl : ["uploads/Personalprofile/2020-04-22/20200422132354295.png","uploads/Personalprofile/2020-04-22/20200422132354707.png"]
         * lat : 39.8866510
         * lng : 116.6898290
         * imgURL : uploads/HeaderImgs/2020-04-16/20200416191133733.png
         * Password : $2y$10$l7M0OoN/Vz1drUOHxb8PCekrwUNPUJVatzkf8AnClnEtoxps8CAb6
         * MoneyPassword : $2y$10$/frX4NXINWiqYzrS25mCTuEZJSqGeTAUUCKhLEpzf5E6MElWf9L7m
         * RealNameRegistration : 1
         * NearBy : 1
         * acceptNearplayerInvite : 1
         * acceptFirendInvite : 1
         * loginType : 1
         * FinishedInfo : 0
         * status : 0
         * vipLevel : 0
         * vipTime : 0000-00-00 00:00:00
         * HeadImageURL :
         * RegistDate : 2019-07-24 14:10:15
         * imNumber : 5058b7b5f89a9efff2602941a2c9f638
         * PromotId : null
         * isPromoter : 0
         * isPromoterstop : 0
         * Royalty : 0
         * isPromotertime : null
         * PromoterStadium : null
         * Totalthroughput : 0
         * Treuthroughput : 0
         * Invitation : null
         * FatherID : null
         * Referee : 0
         * age : 14
         * commonCoins : 392.5
         * userEvaluate : 5
         * userPerform : 17.95%
         * userHightLevel : {"id":10,"level":"Lv10","grade":10,"mincoins":5210,"maxcoins":999999,"winCoins":1280,"name":"网球","sportID":7}
         * userLabel : [{"LabelName":"客观填写比赛结果","labelSort":4,"LabelCount":93},{"LabelName":"填写结果不客观","labelSort":13,"LabelCount":5},{"LabelName":"很热情","labelSort":2,"LabelCount":3},{"LabelName":"很大度","labelSort":3,"LabelCount":3},{"LabelName":"神准时","labelSort":1,"LabelCount":0},{"LabelName":"严格遵守比赛规则","labelSort":5,"LabelCount":0}]
         * faveriteSport : 排球/菜鸟,羽毛球/菜鸟,台球/菜鸟,乒乓球/初级
         * userTechcoins : [{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":1,"total":427.06,"sport_name":"羽毛球","nowlevel":"Lv6","mincoins":320,"maxcoins":640,"nextlevel":"Lv7"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":2,"total":1684,"spor 2020-04-22 14:11:07.513 12826-12826/com.example.android.tiaozhan D/1608: t_name":"乒乓球","nowlevel":"Lv8","mincoins":1280,"maxcoins":2560,"nextlevel":"Lv9"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":3,"total":3069,"sport_name":"台球","nowlevel":"Lv9","mincoins":2560,"maxcoins":5210,"nextlevel":"Lv10"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":4,"total":878.69,"sport_name":"篮球","nowlevel":"Lv7","mincoins":640,"maxcoins":1280,"nextlevel":"Lv8"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":5,"total":15,"sport_name":"足球","nowlevel":"Lv1","mincoins":"0","maxcoins":20,"nextlevel":"Lv2"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":6,"total":486.31,"sport_name":"排球","nowlevel":"Lv6","mincoins":320,"maxcoins":640,"nextlevel":"Lv7"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":7,"total":7816,"sport_name":"网球","nowlevel":"Lv10","mincoins":5210,"maxcoins":999999,"nextlevel":"Lv2"},{"PlayerUUID":"5058b7b5-f89a-9eff-f260-2941a2c9f638","SportID":8,"total":128,"sport_name":"高尔夫","nowlevel":"Lv4","mincoins":80,"maxcoins":160,"nextlevel":"Lv5"}]
         */

        private String uuid;
        private String wechartID;
        private String zfbID;
        private String account;
        private String nickname;
        private String realname;
        private String telephone;
        private String PhoneKey;
        private int sex;
        private String birthday;
        private Object tall;
        private Object weight;
        private String address;
        private Object comment;
        private String lat;
        private String lng;
        private String imgURL;
        private String Password;
        private String MoneyPassword;
        private int RealNameRegistration;
        private int NearBy;
        private int acceptNearplayerInvite;
        private int acceptFirendInvite;
        private int loginType;
        private int FinishedInfo;
        private int status;
        private int vipLevel;
        private String vipTime;
        private String HeadImageURL;
        private String RegistDate;
        private String imNumber;
        private Object PromotId;
        private int isPromoter;
        private int isPromoterstop;
        private int Royalty;
        private Object isPromotertime;
        private Object PromoterStadium;
        private int Totalthroughput;
        private int Treuthroughput;
        private Object Invitation;
        private Object FatherID;
        private int Referee;
        private int age;
        private double commonCoins;
        private int userEvaluate;
        private String userPerform;
        private UserHightLevelBean userHightLevel;
        private String faveriteSport;
        private List<String> personalImgurl;
        private List<UserLabelBean> userLabel;
        private List<UserTechcoinsBean> userTechcoins;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getWechartID() {
            return wechartID;
        }

        public void setWechartID(String wechartID) {
            this.wechartID = wechartID;
        }

        public String getZfbID() {
            return zfbID;
        }

        public void setZfbID(String zfbID) {
            this.zfbID = zfbID;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getPhoneKey() {
            return PhoneKey;
        }

        public void setPhoneKey(String PhoneKey) {
            this.PhoneKey = PhoneKey;
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

        public Object getTall() {
            return tall;
        }

        public void setTall(Object tall) {
            this.tall = tall;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
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

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getMoneyPassword() {
            return MoneyPassword;
        }

        public void setMoneyPassword(String MoneyPassword) {
            this.MoneyPassword = MoneyPassword;
        }

        public int getRealNameRegistration() {
            return RealNameRegistration;
        }

        public void setRealNameRegistration(int RealNameRegistration) {
            this.RealNameRegistration = RealNameRegistration;
        }

        public int getNearBy() {
            return NearBy;
        }

        public void setNearBy(int NearBy) {
            this.NearBy = NearBy;
        }

        public int getAcceptNearplayerInvite() {
            return acceptNearplayerInvite;
        }

        public void setAcceptNearplayerInvite(int acceptNearplayerInvite) {
            this.acceptNearplayerInvite = acceptNearplayerInvite;
        }

        public int getAcceptFirendInvite() {
            return acceptFirendInvite;
        }

        public void setAcceptFirendInvite(int acceptFirendInvite) {
            this.acceptFirendInvite = acceptFirendInvite;
        }

        public int getLoginType() {
            return loginType;
        }

        public void setLoginType(int loginType) {
            this.loginType = loginType;
        }

        public int getFinishedInfo() {
            return FinishedInfo;
        }

        public void setFinishedInfo(int FinishedInfo) {
            this.FinishedInfo = FinishedInfo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(int vipLevel) {
            this.vipLevel = vipLevel;
        }

        public String getVipTime() {
            return vipTime;
        }

        public void setVipTime(String vipTime) {
            this.vipTime = vipTime;
        }

        public String getHeadImageURL() {
            return HeadImageURL;
        }

        public void setHeadImageURL(String HeadImageURL) {
            this.HeadImageURL = HeadImageURL;
        }

        public String getRegistDate() {
            return RegistDate;
        }

        public void setRegistDate(String RegistDate) {
            this.RegistDate = RegistDate;
        }

        public String getImNumber() {
            return imNumber;
        }

        public void setImNumber(String imNumber) {
            this.imNumber = imNumber;
        }

        public Object getPromotId() {
            return PromotId;
        }

        public void setPromotId(Object PromotId) {
            this.PromotId = PromotId;
        }

        public int getIsPromoter() {
            return isPromoter;
        }

        public void setIsPromoter(int isPromoter) {
            this.isPromoter = isPromoter;
        }

        public int getIsPromoterstop() {
            return isPromoterstop;
        }

        public void setIsPromoterstop(int isPromoterstop) {
            this.isPromoterstop = isPromoterstop;
        }

        public int getRoyalty() {
            return Royalty;
        }

        public void setRoyalty(int Royalty) {
            this.Royalty = Royalty;
        }

        public Object getIsPromotertime() {
            return isPromotertime;
        }

        public void setIsPromotertime(Object isPromotertime) {
            this.isPromotertime = isPromotertime;
        }

        public Object getPromoterStadium() {
            return PromoterStadium;
        }

        public void setPromoterStadium(Object PromoterStadium) {
            this.PromoterStadium = PromoterStadium;
        }

        public int getTotalthroughput() {
            return Totalthroughput;
        }

        public void setTotalthroughput(int Totalthroughput) {
            this.Totalthroughput = Totalthroughput;
        }

        public int getTreuthroughput() {
            return Treuthroughput;
        }

        public void setTreuthroughput(int Treuthroughput) {
            this.Treuthroughput = Treuthroughput;
        }

        public Object getInvitation() {
            return Invitation;
        }

        public void setInvitation(Object Invitation) {
            this.Invitation = Invitation;
        }

        public Object getFatherID() {
            return FatherID;
        }

        public void setFatherID(Object FatherID) {
            this.FatherID = FatherID;
        }

        public int getReferee() {
            return Referee;
        }

        public void setReferee(int Referee) {
            this.Referee = Referee;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getCommonCoins() {
            return commonCoins;
        }

        public void setCommonCoins(double commonCoins) {
            this.commonCoins = commonCoins;
        }

        public int getUserEvaluate() {
            return userEvaluate;
        }

        public void setUserEvaluate(int userEvaluate) {
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

        public List<String> getPersonalImgurl() {
            return personalImgurl;
        }

        public void setPersonalImgurl(List<String> personalImgurl) {
            this.personalImgurl = personalImgurl;
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
             * id : 10
             * level : Lv10
             * grade : 10
             * mincoins : 5210
             * maxcoins : 999999
             * winCoins : 1280
             * name : 网球
             * sportID : 7
             */

            private int id;
            private String level;
            private int grade;
            private int mincoins;
            private int maxcoins;
            private int winCoins;
            private String name;
            private int sportID;

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

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
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

            public int getWinCoins() {
                return winCoins;
            }

            public void setWinCoins(int winCoins) {
                this.winCoins = winCoins;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSportID() {
                return sportID;
            }

            public void setSportID(int sportID) {
                this.sportID = sportID;
            }
        }

        public static class UserLabelBean {
            /**
             * LabelName : 客观填写比赛结果
             * labelSort : 4
             * LabelCount : 93
             */

            private String LabelName;
            private int labelSort;
            private int LabelCount;

            public String getLabelName() {
                return LabelName;
            }

            public void setLabelName(String LabelName) {
                this.LabelName = LabelName;
            }

            public int getLabelSort() {
                return labelSort;
            }

            public void setLabelSort(int labelSort) {
                this.labelSort = labelSort;
            }

            public int getLabelCount() {
                return LabelCount;
            }

            public void setLabelCount(int LabelCount) {
                this.LabelCount = LabelCount;
            }
        }

        public static class UserTechcoinsBean {
            /**
             * PlayerUUID : 5058b7b5-f89a-9eff-f260-2941a2c9f638
             * SportID : 1
             * total : 427.06
             * sport_name : 羽毛球
             * nowlevel : Lv6
             * mincoins : 320
             * maxcoins : 640
             * nextlevel : Lv7
             * spor 2020-04-22 14:11:07.513 12826-12826/com.example.android.tiaozhan D/1608: t_name : 乒乓球
             */

            private String PlayerUUID;
            private int SportID;
            private double total;
            private String sport_name;
            private String nowlevel;
            private int mincoins;
            private int maxcoins;
            private String nextlevel;
            @SerializedName("spor 2020-04-22 14:11:07.513 12826-12826/com.example.android.tiaozhan D/1608: t_name")
            private String _$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114; // FIXME check this code

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

            public String get_$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114() {
                return _$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114;
            }

            public void set_$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114(String _$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114) {
                this._$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114 = _$Spor202004221411075131282612826ComExampleAndroidTiaozhanD1608T_name114;
            }
        }
    }
}
