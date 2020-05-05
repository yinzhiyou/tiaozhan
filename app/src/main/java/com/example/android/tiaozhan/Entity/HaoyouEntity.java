package com.example.android.tiaozhan.Entity;

import java.util.List;

public class HaoyouEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":2,"Lst":[{"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","userInfo":{"nickname":"jsjsj","sex":0,"imgURL":"uploads/2018-11-09/HeaderImgs/20181109102725174.png","tall":180,"weight":80,"age":1},"sportidNameStr":"羽毛球,乒乓球,台球","hightLevel":"Lv1","hightName":"羽毛球","isFriends":"1"},{"uuid":"8965f02f-2aa3-6001-d968-d6d21d775c1d","userInfo":{"nickname":"12323","sex":0,"imgURL":"","tall":"","weight":"","age":2019},"sportidNameStr":"","hightLevel":"Lv1","hightName":"羽毛球","isFriends":"1"}]}
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
         * total : 2
         * Lst : [{"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","userInfo":{"nickname":"jsjsj","sex":0,"imgURL":"uploads/2018-11-09/HeaderImgs/20181109102725174.png","tall":180,"weight":80,"age":1},"sportidNameStr":"羽毛球,乒乓球,台球","hightLevel":"Lv1","hightName":"羽毛球","isFriends":"1"},{"uuid":"8965f02f-2aa3-6001-d968-d6d21d775c1d","userInfo":{"nickname":"12323","sex":0,"imgURL":"","tall":"","weight":"","age":2019},"sportidNameStr":"","hightLevel":"Lv1","hightName":"羽毛球","isFriends":"1"}]
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
             * uuid : 54ca24d3-0f6b-2abe-ae50-3808e4eda735
             * userInfo : {"nickname":"jsjsj","sex":0,"imgURL":"uploads/2018-11-09/HeaderImgs/20181109102725174.png","tall":180,"weight":80,"age":1}
             * sportidNameStr : 羽毛球,乒乓球,台球
             * hightLevel : Lv1
             * hightName : 羽毛球
             * isFriends : 1
             */

            private String uuid;
            private UserInfoBean userInfo;
            private String sportidNameStr;
            private String hightLevel;
            private String hightName;
            private String isFriends;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
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

            public String getIsFriends() {
                return isFriends;
            }

            public void setIsFriends(String isFriends) {
                this.isFriends = isFriends;
            }

            public static class UserInfoBean {
                /**
                 * nickname : jsjsj
                 * sex : 0
                 * imgURL : uploads/2018-11-09/HeaderImgs/20181109102725174.png
                 * tall : 180
                 * weight : 80
                 * age : 1
                 */

                private String nickname;
                private int sex;
                private String imgURL;
                private Object tall;
                private Object weight;
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
