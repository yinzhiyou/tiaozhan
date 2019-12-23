package com.example.android.promoter.Entity;

public class CeshiEntity  {

    /**
     * code : 2000
     * msg : 获取用户信息成功
     * data : {"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","RegistDate":"2018-10-26 18:56:42","wechartID":"","zfbID":"","account":"","nickname":"","realname":"","telephone":"13260272587","sex":0,"birthday":"0000-00-00","tall":"","weight":"","address":"","comment":"","imgURL":"uploads/2018-10-26/HeaderImgs/20181026185645139.png","MoneyPassword":"","RealNameRegistration":0,"NearBy":1,"Password":"$2y$10$ZMjDjruR/kUXX1VBwA5cTO.xkgLFjMtZ1cahDzSEyUFMH5hyTXFoe","faveriteSport":{"id":"","name":""}}
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
         * uuid : 54ca24d3-0f6b-2abe-ae50-3808e4eda735
         * RegistDate : 2018-10-26 18:56:42
         * wechartID :
         * zfbID :
         * account :
         * nickname :
         * realname :
         * telephone : 13260272587
         * sex : 0
         * birthday : 0000-00-00
         * tall :
         * weight :
         * address :
         * comment :
         * imgURL : uploads/2018-10-26/HeaderImgs/20181026185645139.png
         * MoneyPassword :
         * RealNameRegistration : 0
         * NearBy : 1
         * Password : $2y$10$ZMjDjruR/kUXX1VBwA5cTO.xkgLFjMtZ1cahDzSEyUFMH5hyTXFoe
         * faveriteSport : {"id":"","name":""}
         */

        private String uuid;
        private String RegistDate;
        private String wechartID;
        private String zfbID;
        private String account;
        private String nickname;
        private String realname;
        private String telephone;
        private int sex;
        private String birthday;
        private String tall;
        private String weight;
        private String address;
        private String comment;
        private String imgURL;
        private String MoneyPassword;
        private int RealNameRegistration;
        private int NearBy;
        private String Password;
        private FaveriteSportBean faveriteSport;

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

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public FaveriteSportBean getFaveriteSport() {
            return faveriteSport;
        }

        public void setFaveriteSport(FaveriteSportBean faveriteSport) {
            this.faveriteSport = faveriteSport;
        }

        public static class FaveriteSportBean {
            /**
             * id :
             * name :
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
