package com.example.android.promoter.Entity;

import java.util.List;

public class YonghuxinxiEntidy {

    /**
     * code : 2000
     * msg : 获取用户信息成功
     * data : {"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","wechartID":"","zfbID":"","account":"","nickname":"王大大","realname":"","telephone":"13260272587","PhoneKey":"","sex":0,"birthday":"2018-01-01","tall":180,"weight":80,"address":"北京市","comment":"low 吐了","lat":"39.8769490","lng":"116.7013700","imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","Password":"$2y$10$ZMjDjruR/kUXX1VBwA5cTO.xkgLFjMtZ1cahDzSEyUFMH5hyTXFoe","MoneyPassword":"","RealNameRegistration":0,"NearBy":1,"acceptNearplayerInvite":0,"acceptFirendInvite":1,"loginType":"","FinishedInfo":0,"status":0,"vipLevel":0,"vipTime":"2018-11-22 14:53:42","HeadImageURL":"","RegistDate":"2018-11-22 14:53:42","faveriteSport":[{"id":1,"name":"羽毛球"},{"id":2,"name":"乒乓球"},{"id":3,"name":"台球"}]}
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
         * wechartID :
         * zfbID :
         * account :
         * nickname : 王大大
         * realname :
         * telephone : 13260272587
         * PhoneKey :
         * sex : 0
         * birthday : 2018-01-01
         * tall : 180
         * weight : 80
         * address : 北京市
         * comment : low 吐了
         * lat : 39.8769490
         * lng : 116.7013700
         * imgURL : uploads/2018-11-13/HeaderImgs/20181113200402128.png
         * Password : $2y$10$ZMjDjruR/kUXX1VBwA5cTO.xkgLFjMtZ1cahDzSEyUFMH5hyTXFoe
         * MoneyPassword :
         * RealNameRegistration : 0
         * NearBy : 1
         * acceptNearplayerInvite : 0
         * acceptFirendInvite : 1
         * loginType :
         * FinishedInfo : 0
         * status : 0
         * vipLevel : 0
         * vipTime : 2018-11-22 14:53:42
         * HeadImageURL :
         * RegistDate : 2018-11-22 14:53:42
         * faveriteSport : [{"id":1,"name":"羽毛球"},{"id":2,"name":"乒乓球"},{"id":3,"name":"台球"}]
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
        private int tall;
        private int weight;
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

        public List<FaveriteSportBean> getFaveriteSport() {
            return faveriteSport;
        }

        public void setFaveriteSport(List<FaveriteSportBean> faveriteSport) {
            this.faveriteSport = faveriteSport;
        }

        public static class FaveriteSportBean {
            /**
             * id : 1
             * name : 羽毛球
             */

            private int id;
            private String name;

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
