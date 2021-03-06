package com.example.android.tiaozhan.Entity;

import java.util.List;

public class FujinEntity  {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":4,"Lst":[{"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","lat":"39.8769480","lng":"116.7013690","userInfo":{"nickname":"jsjsj","sex":0,"imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","tall":180,"weight":80,"age":1},"sportidNameStr":"羽毛球,乒乓球,台球","hightLevel":"Lv1","hightName":"羽毛球","range":"0.16Km","isFriend":1},{"uuid":"a7c016b7-526d-ce10-5538-edce0279159f","lat":"39.8698890","lng":"116.6886980","userInfo":{"nickname":"在家闲着","sex":0,"imgURL":"uploads/2018-11-02/HeaderImgs/20181102145818676.png","tall":175,"weight":80,"age":8},"sportidNameStr":"","hightLevel":"Lv1","hightName":"羽毛球","range":"1.38Km","isFriend":0},{"uuid":"b60d8e06-1ff3-f048-d42f-49f42b7f0e2b","lat":"39.8697670","lng":"116.6888170","userInfo":{"nickname":"kook","sex":0,"imgURL":"uploads/2018-10-31/HeaderImgs/20181031173544594.png","tall":175,"weight":80,"age":10},"sportidNameStr":"台球,排球","hightLevel":"Lv1","hightName":"羽毛球","range":"1.38Km","isFriend":0}]}
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
         * total : 4
         * Lst : [{"uuid":"54ca24d3-0f6b-2abe-ae50-3808e4eda735","lat":"39.8769480","lng":"116.7013690","userInfo":{"nickname":"jsjsj","sex":0,"imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","tall":180,"weight":80,"age":1},"sportidNameStr":"羽毛球,乒乓球,台球","hightLevel":"Lv1","hightName":"羽毛球","range":"0.16Km","isFriend":1},{"uuid":"a7c016b7-526d-ce10-5538-edce0279159f","lat":"39.8698890","lng":"116.6886980","userInfo":{"nickname":"在家闲着","sex":0,"imgURL":"uploads/2018-11-02/HeaderImgs/20181102145818676.png","tall":175,"weight":80,"age":8},"sportidNameStr":"","hightLevel":"Lv1","hightName":"羽毛球","range":"1.38Km","isFriend":0},{"uuid":"b60d8e06-1ff3-f048-d42f-49f42b7f0e2b","lat":"39.8697670","lng":"116.6888170","userInfo":{"nickname":"kook","sex":0,"imgURL":"uploads/2018-10-31/HeaderImgs/20181031173544594.png","tall":175,"weight":80,"age":10},"sportidNameStr":"台球,排球","hightLevel":"Lv1","hightName":"羽毛球","range":"1.38Km","isFriend":0}]
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
             * lat : 39.8769480
             * lng : 116.7013690
             * userInfo : {"nickname":"jsjsj","sex":0,"imgURL":"uploads/2018-11-13/HeaderImgs/20181113200402128.png","tall":180,"weight":80,"age":1}
             * sportidNameStr : 羽毛球,乒乓球,台球
             * hightLevel : Lv1
             * hightName : 羽毛球
             * range : 0.16Km
             * isFriend : 1
             */

            private String uuid;
            private String lat;
            private String lng;
            private UserInfoBean userInfo;
            private String sportidNameStr;
            private String hightLevel;
            private String hightName;
            private String range;
            private int isFriend;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
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

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public int getIsFriend() {
                return isFriend;
            }

            public void setIsFriend(int isFriend) {
                this.isFriend = isFriend;
            }

            public static class UserInfoBean {
                /**
                 * nickname : jsjsj
                 * sex : 0
                 * imgURL : uploads/2018-11-13/HeaderImgs/20181113200402128.png
                 * tall : 180
                 * weight : 80
                 * age : 1
                 */

                private String nickname;
                private int sex;
                private String imgURL;
                private int tall;
                private int weight;
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
