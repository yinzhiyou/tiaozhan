package com.example.android.tiaozhan.Entity;

import java.util.List;

public class SSFujinEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":1,"Lst":[{"uuid":"b08e78a9-7bf4-c5f9-16c4-7474290ea2a5","nickname":"你你你","sex":1,"tall":182,"weight":90,"imgURL":"uploads/2018-12-17/HeaderImgs/20181217145433294.png","age":1,"sportidNameStr":"羽毛球,台球,篮球,足球,高尔夫","hightLevel":"Lv1","hightName":"乒乓球","userRage":"0.01Km"}]}
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
         * Lst : [{"uuid":"b08e78a9-7bf4-c5f9-16c4-7474290ea2a5","nickname":"你你你","sex":1,"tall":182,"weight":90,"imgURL":"uploads/2018-12-17/HeaderImgs/20181217145433294.png","age":1,"sportidNameStr":"羽毛球,台球,篮球,足球,高尔夫","hightLevel":"Lv1","hightName":"乒乓球","userRage":"0.01Km"}]
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
             * uuid : b08e78a9-7bf4-c5f9-16c4-7474290ea2a5
             * nickname : 你你你
             * sex : 1
             * tall : 182
             * weight : 90
             * imgURL : uploads/2018-12-17/HeaderImgs/20181217145433294.png
             * age : 1
             * sportidNameStr : 羽毛球,台球,篮球,足球,高尔夫
             * hightLevel : Lv1
             * hightName : 乒乓球
             * userRage : 0.01Km
             */

            private String uuid;
            private String nickname;
            private int sex;
            private int tall;
            private int weight;
            private String imgURL;
            private int age;
            private String sportidNameStr;
            private String hightLevel;
            private String hightName;
            private String userRage;

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

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getSportidNameStr() {
                return sportidNameStr;
            }

            public void setSportidNameStr(String sportidNameStr) {
                this.sportidNameStr = sportidNameStr;
            }

            public String getHightLevel() {
                return hightLevel;
            }

            public void setHightLevel(String hightLevel) {
                this.hightLevel = hightLevel;
            }

            public String getHightName() {
                return hightName;
            }

            public void setHightName(String hightName) {
                this.hightName = hightName;
            }

            public String getUserRage() {
                return userRage;
            }

            public void setUserRage(String userRage) {
                this.userRage = userRage;
            }
        }
    }
}
