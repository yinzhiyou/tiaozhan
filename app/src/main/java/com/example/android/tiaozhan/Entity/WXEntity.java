package com.example.android.tiaozhan.Entity;

public class WXEntity {


    /**
     * code : 2000
     * msg : 登陆成功
     * data : {"uuid":"54585b5b-1f0c-4b27-f2ed-2829fd33a12f","wechartID":"o2xit0VrtWueuutZ8MtLLnB-gRm4","zfbID":"","account":"","nickname":"VIVO","realname":"","telephone":"","PhoneKey":"","sex":0,"birthday":"0000-00-00","tall":0,"weight":0,"address":"","comment":"","lat":"0.0000000","lng":"0.0000000","imgURL":"","Password":"","MoneyPassword":"","RealNameRegistration":0,"NearBy":1,"acceptNearplayerInvite":0,"acceptFirendInvite":1,"loginType":"","FinishedInfo":0,"status":0,"vipLevel":0,"vipTime":"0000-00-00 00:00:00","HeadImageURL":"","RegistDate":"2019-05-17 16:21:39","imNumber":"54585b5b1f0c4b27f2ed2829fd33a12f","token":"VY5DKreZPVHvyvn43OmTt9jBKiprsOYoVqee0fJ1kLxKCUl1iP5FlariKaPZQMEE"}
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
         * uuid : 54585b5b-1f0c-4b27-f2ed-2829fd33a12f
         * wechartID : o2xit0VrtWueuutZ8MtLLnB-gRm4
         * zfbID :
         * account :
         * nickname : VIVO
         * realname :
         * telephone :
         * PhoneKey :
         * sex : 0
         * birthday : 0000-00-00
         * tall : 0
         * weight : 0
         * address :
         * comment :
         * lat : 0.0000000
         * lng : 0.0000000
         * imgURL :
         * Password :
         * MoneyPassword :
         * RealNameRegistration : 0
         * NearBy : 1
         * acceptNearplayerInvite : 0
         * acceptFirendInvite : 1
         * loginType :
         * FinishedInfo : 0
         * status : 0
         * vipLevel : 0
         * vipTime : 0000-00-00 00:00:00
         * HeadImageURL :
         * RegistDate : 2019-05-17 16:21:39
         * imNumber : 54585b5b1f0c4b27f2ed2829fd33a12f
         * token : VY5DKreZPVHvyvn43OmTt9jBKiprsOYoVqee0fJ1kLxKCUl1iP5FlariKaPZQMEE
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
        private String loginType;
        private int FinishedInfo;
        private int status;
        private int vipLevel;
        private String vipTime;
        private String HeadImageURL;
        private String RegistDate;
        private String imNumber;
        private String token;

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

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
