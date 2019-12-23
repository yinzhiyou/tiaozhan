package com.example.android.promoter.Entity;

import java.util.List;

public class GRXXEntity {


    /**
     * code : 2000
     * msg : 获取用户信息成功
     * data : {"uuid":"2d04c573-4052-ea7d-05c6-08f379b02155","RegistDate":"2018-10-29 17:38:31","wechartID":"","zfbID":"","account":"","nickname":"dasdas","realname":"","telephone":"18201395884","sex":0,"birthday":"2018-10-29","tall":150,"weight":220,"address":"北京市北京市东城区","comment":"ssadad","imgURL":"uploads/2018-10-26/HeaderImgs/20181026185355755.png","MoneyPassword":"","RealNameRegistration":0,"NearBy":1,"Password":"$2y$10$XesAk8B5bb8Hf7EvEkBi3.TicNEo6eY2oiwm/4bewp9RGcVkJUDIe","faveriteSport":[{"id":5,"name":"足球"},{"id":8,"name":"高尔夫"}]}
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
         * uuid : 2d04c573-4052-ea7d-05c6-08f379b02155
         * RegistDate : 2018-10-29 17:38:31
         * wechartID :
         * zfbID :
         * account :
         * nickname : dasdas
         * realname :
         * telephone : 18201395884
         * sex : 0
         * birthday : 2018-10-29
         * tall : 150
         * weight : 220
         * address : 北京市北京市东城区
         * comment : ssadad
         * imgURL : uploads/2018-10-26/HeaderImgs/20181026185355755.png
         * MoneyPassword :
         * RealNameRegistration : 0
         * NearBy : 1
         * Password : $2y$10$XesAk8B5bb8Hf7EvEkBi3.TicNEo6eY2oiwm/4bewp9RGcVkJUDIe
         * faveriteSport : [{"id":5,"name":"足球"},{"id":8,"name":"高尔夫"}]
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
        private double tall;
        private double weight;
        private String address;
        private String comment;
        private String imgURL;
        private String MoneyPassword;
        private int RealNameRegistration;
        private int NearBy;
        private String Password;
        private List<FaveriteSportBean> faveriteSport;

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

        public double getTall() {
            return tall;
        }

        public void setTall(double tall) {
            this.tall = tall;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
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

        public List<FaveriteSportBean> getFaveriteSport() {
            return faveriteSport;
        }

        public void setFaveriteSport(List<FaveriteSportBean> faveriteSport) {
            this.faveriteSport = faveriteSport;
        }

        public static class FaveriteSportBean {
            /**
             * id : 5
             * name : 足球
             */

            private int id;
            private String name;
            private int grade_status;
            private String grade_name;

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
        }
    }
}
