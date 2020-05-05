package com.example.android.tiaozhan.Entity;

public class DengluEntity {


    /**
     * code : 2000
     * msg : 登陆成功
     * data : {"uuid":"a0b9c201-2181-fd60-cbe5-0f06627691ab","RegistDate":"2018-09-18 16:14:04","wechartID":null,"zfbID":null,"account":null,"nickname":"","realname":"","telephone":"18201395884","sex":0,"birthday":"0000-00-00","tall":null,"weight":null,"address":null,"comment":null,"imgURL":"","MoneyPassword":null,"RealNameRegistration":0,"NearBy":1,"Password":"$2y$10$JowidqA8B1y6oIOL62BHhu4icI.YzpG8VPi/0YoU7p0A3gkBpeNyO","lng":"0.0000000","lat":"0.0000000","token":"mJVRFPx064MVwYEwrQeUrJHHjvJaMq6P5MS1OG7oW52702eqEkAGepfjEVHqtIe1"}
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
         * uuid : a0b9c201-2181-fd60-cbe5-0f06627691ab
         * RegistDate : 2018-09-18 16:14:04
         * wechartID : null
         * zfbID : null
         * account : null
         * nickname :
         * realname :
         * telephone : 18201395884
         * sex : 0
         * birthday : 0000-00-00
         * tall : null
         * weight : null
         * address : null
         * comment : null
         * imgURL :
         * MoneyPassword : null
         * RealNameRegistration : 0
         * NearBy : 1
         * Password : $2y$10$JowidqA8B1y6oIOL62BHhu4icI.YzpG8VPi/0YoU7p0A3gkBpeNyO
         * lng : 0.0000000
         * lat : 0.0000000
         * token : mJVRFPx064MVwYEwrQeUrJHHjvJaMq6P5MS1OG7oW52702eqEkAGepfjEVHqtIe1
         */

        private String uuid;
        private String RegistDate;
        private Object wechartID;
        private Object zfbID;
        private Object account;
        private String nickname;
        private String realname;
        private String telephone;
        private int sex;
        private String birthday;
        private Object tall;
        private Object weight;
        private Object address;
        private Object comment;
        private String imgURL;
        private Object MoneyPassword;
        private int RealNameRegistration;
        private int NearBy;
        private String Password;
        private String lng;
        private String lat;
        private String token;
        private int perfect;

        public int getPerfect() {
            return perfect;
        }

        public void setPerfect(int perfect) {
            this.perfect = perfect;
        }

        public int getIsPromoter() {
            return isPromoter;
        }

        public void setIsPromoter(int isPromoter) {
            this.isPromoter = isPromoter;
        }

        private int isPromoter;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getRegistDate() {
            return RegistDate;
        }

        public void setRegistDate(String RegistDate) {
            this.RegistDate = RegistDate;
        }

        public Object getWechartID() {
            return wechartID;
        }

        public void setWechartID(Object wechartID) {
            this.wechartID = wechartID;
        }

        public Object getZfbID() {
            return zfbID;
        }

        public void setZfbID(Object zfbID) {
            this.zfbID = zfbID;
        }

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
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

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public Object getMoneyPassword() {
            return MoneyPassword;
        }

        public void setMoneyPassword(Object MoneyPassword) {
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

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
