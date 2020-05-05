package com.example.android.tiaozhan.Entity;

import java.util.List;

public class GRxxInfoEntity {


    /**
     * code : 2000
     * msg : 获取用户信息成功
     * data : {"uuid":"5058b7b5-f89a-9eff-f260-2941a2c9f638","wechartID":"","zfbID":"","account":"","nickname":"测试31号","realname":"","telephone":"13500000031","PhoneKey":"","sex":0,"birthday":"2006-09-17","tall":"","weight":"","address":"北京市,北京市,通州区","comment":"","personalImgurl":["uploads/Personalprofile/2020-04-21/20200421181030679.png","uploads/Personalprofile/2020-04-21/20200421181030949.png"],"lat":"39.8866120","lng":"116.6898330","imgURL":"uploads/HeaderImgs/2020-04-16/20200416191133733.png","Password":"$2y$10$l7M0OoN/Vz1drUOHxb8PCekrwUNPUJVatzkf8AnClnEtoxps8CAb6","MoneyPassword":"$2y$10$/frX4NXINWiqYzrS25mCTuEZJSqGeTAUUCKhLEpzf5E6MElWf9L7m","RealNameRegistration":1,"NearBy":1,"acceptNearplayerInvite":1,"acceptFirendInvite":1,"loginType":1,"FinishedInfo":0,"status":0,"vipLevel":0,"vipTime":"0000-00-00 00:00:00","HeadImageURL":"","RegistDate":"2019-07-24 14:10:15","imNumber":"5058b7b5f89a9efff2602941a2c9f638","PromotId":"","isPromoter":0,"isPromoterstop":0,"Royalty":0,"isPromotertime":"","PromoterStadium":"","Totalthroughput":0,"Treuthroughput":0,"Invitation":"","FatherID":"","Referee":0,"faveriteSport":[{"id":6,"name":"排球","grade_status":0,"grade_name":"菜鸟"},{"id":1,"name":"羽毛球","grade_status":0,"grade_name":"菜鸟"},{"id":3,"name":"台球","grade_status":0,"grade_name":"菜鸟"},{"id":2,"name":"乒乓球","grade_status":1,"grade_name":"初级"}]}
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
         * tall :
         * weight :
         * address : 北京市,北京市,通州区
         * comment :
         * personalImgurl : ["uploads/Personalprofile/2020-04-21/20200421181030679.png","uploads/Personalprofile/2020-04-21/20200421181030949.png"]
         * lat : 39.8866120
         * lng : 116.6898330
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
         * PromotId :
         * isPromoter : 0
         * isPromoterstop : 0
         * Royalty : 0
         * isPromotertime :
         * PromoterStadium :
         * Totalthroughput : 0
         * Treuthroughput : 0
         * Invitation :
         * FatherID :
         * Referee : 0
         * faveriteSport : [{"id":6,"name":"排球","grade_status":0,"grade_name":"菜鸟"},{"id":1,"name":"羽毛球","grade_status":0,"grade_name":"菜鸟"},{"id":3,"name":"台球","grade_status":0,"grade_name":"菜鸟"},{"id":2,"name":"乒乓球","grade_status":1,"grade_name":"初级"}]
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
        private String tall;
        private String weight;
        private String address;
        private String comment;
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
        private String PromotId;
        private int isPromoter;
        private int isPromoterstop;
        private int Royalty;
        private String isPromotertime;
        private String PromoterStadium;
        private int Totalthroughput;
        private int Treuthroughput;
        private String Invitation;
        private String FatherID;
        private int Referee;
        private List<String> personalImgurl;
        private List<FaveriteSportBean> faveriteSport;

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

        public String getPromotId() {
            return PromotId;
        }

        public void setPromotId(String PromotId) {
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

        public String getIsPromotertime() {
            return isPromotertime;
        }

        public void setIsPromotertime(String isPromotertime) {
            this.isPromotertime = isPromotertime;
        }

        public String getPromoterStadium() {
            return PromoterStadium;
        }

        public void setPromoterStadium(String PromoterStadium) {
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

        public String getInvitation() {
            return Invitation;
        }

        public void setInvitation(String Invitation) {
            this.Invitation = Invitation;
        }

        public String getFatherID() {
            return FatherID;
        }

        public void setFatherID(String FatherID) {
            this.FatherID = FatherID;
        }

        public int getReferee() {
            return Referee;
        }

        public void setReferee(int Referee) {
            this.Referee = Referee;
        }

        public List<String> getPersonalImgurl() {
            return personalImgurl;
        }

        public void setPersonalImgurl(List<String> personalImgurl) {
            this.personalImgurl = personalImgurl;
        }

        public List<FaveriteSportBean> getFaveriteSport() {
            return faveriteSport;
        }

        public void setFaveriteSport(List<FaveriteSportBean> faveriteSport) {
            this.faveriteSport = faveriteSport;
        }

        public static class FaveriteSportBean {
            /**
             * id : 6
             * name : 排球
             * grade_status : 0
             * grade_name : 菜鸟
             */

            private int id;
            private String name;
            private int grade_status;
            private String grade_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGrade_status() {
                return grade_status;
            }

            public void setGrade_status(int grade_status) {
                this.grade_status = grade_status;
            }

            public String getGrade_name() {
                return grade_name;
            }

            public void setGrade_name(String grade_name) {
                this.grade_name = grade_name;
            }
        }
    }
}
