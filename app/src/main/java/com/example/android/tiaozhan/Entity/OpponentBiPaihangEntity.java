package com.example.android.tiaozhan.Entity;

import java.util.List;

public class OpponentBiPaihangEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"userRankInfo":{"PlayerUUID":"658cec96-a558-c019-a190-bba51f57d3ec","nickname":"笨小孩","imgURL":"uploads/HeaderImgs/2019-10-22/20191022111205865.png","total":"10.00","rank":66,"total_temp":"10.00"},"topThree":[{"PlayerUUID":"bdc1bf13-7f0a-e03c-2e17-68ae966d9557","nickname":"甲乙~刘志伟","imgURL":"uploads/HeaderImgs/2019-06-14/20190614134509968.png","total":"1000248.84","rank":1,"totle_temp":"1000248.84"},{"PlayerUUID":"c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba","nickname":"一颗酸柠檬","imgURL":"uploads/HeaderImgs/2019-10-12/20191012161420756.png","total":"964675.84","rank":2,"totle_temp":"964675.84"},{"PlayerUUID":"4d77c79f-4157-9ad6-6408-4061c856da3d","nickname":"iPhone","imgURL":"/uploads/HeaderImgs/2019-01-20/20190120163528246.png","total":"299085.00","rank":3,"totle_temp":"299085.00"}],"otherRankInfo":[{"PlayerUUID":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","nickname":"不怕不怕","imgURL":"uploads/HeaderImgs/2019-09-12/20190912135226894.png","total":"289999.41","rank":4,"totle_temp":"289999.41"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-04-09/20190409202701678.png","total":"288283.84","rank":5,"totle_temp":"288283.84"},{"PlayerUUID":"67004120-aa15-3ebb-d159-bda39816fe8a","nickname":"羽球菜鸟","imgURL":"uploads/HeaderImgs/2019-06-30/20190630101508136.png","total":"85893.67","rank":6,"totle_temp":"85893.67"},{"PlayerUUID":"bcb5e880-96b4-3872-d577-5ae560e3e20f","nickname":"hello kittiy","imgURL":"uploads/HeaderImgs/2019-05-14/20190514143048459.png","total":"1004.51","rank":7,"totle_temp":"1004.51"},{"PlayerUUID":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2019-08-14/20190814184059613.png","total":"912.17","rank":8,"totle_temp":"912.17"},{"PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","nickname":"王大大","imgURL":"uploads/HeaderImgs/2019-09-02/20190902170202187.png","total":"837.51","rank":9,"totle_temp":"837.51"},{"PlayerUUID":"2d7c53fc-6906-640e-3b4d-79da683989bf","nickname":"郁美净","imgURL":"uploads/HeaderImgs/2019-06-30/20190630101701904.png","total":"672.01","rank":10,"totle_temp":"672.01"}]}
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
         * userRankInfo : {"PlayerUUID":"658cec96-a558-c019-a190-bba51f57d3ec","nickname":"笨小孩","imgURL":"uploads/HeaderImgs/2019-10-22/20191022111205865.png","total":"10.00","rank":66,"total_temp":"10.00"}
         * topThree : [{"PlayerUUID":"bdc1bf13-7f0a-e03c-2e17-68ae966d9557","nickname":"甲乙~刘志伟","imgURL":"uploads/HeaderImgs/2019-06-14/20190614134509968.png","total":"1000248.84","rank":1,"totle_temp":"1000248.84"},{"PlayerUUID":"c215762c-6ddb-3d7a-ac15-45d6e7ccf9ba","nickname":"一颗酸柠檬","imgURL":"uploads/HeaderImgs/2019-10-12/20191012161420756.png","total":"964675.84","rank":2,"totle_temp":"964675.84"},{"PlayerUUID":"4d77c79f-4157-9ad6-6408-4061c856da3d","nickname":"iPhone","imgURL":"/uploads/HeaderImgs/2019-01-20/20190120163528246.png","total":"299085.00","rank":3,"totle_temp":"299085.00"}]
         * otherRankInfo : [{"PlayerUUID":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","nickname":"不怕不怕","imgURL":"uploads/HeaderImgs/2019-09-12/20190912135226894.png","total":"289999.41","rank":4,"totle_temp":"289999.41"},{"PlayerUUID":"24c74571-9659-8bf9-b0c5-78b2e35acae7","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-04-09/20190409202701678.png","total":"288283.84","rank":5,"totle_temp":"288283.84"},{"PlayerUUID":"67004120-aa15-3ebb-d159-bda39816fe8a","nickname":"羽球菜鸟","imgURL":"uploads/HeaderImgs/2019-06-30/20190630101508136.png","total":"85893.67","rank":6,"totle_temp":"85893.67"},{"PlayerUUID":"bcb5e880-96b4-3872-d577-5ae560e3e20f","nickname":"hello kittiy","imgURL":"uploads/HeaderImgs/2019-05-14/20190514143048459.png","total":"1004.51","rank":7,"totle_temp":"1004.51"},{"PlayerUUID":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2019-08-14/20190814184059613.png","total":"912.17","rank":8,"totle_temp":"912.17"},{"PlayerUUID":"26d00c58-3004-c05c-bf31-996578f9f5d4","nickname":"王大大","imgURL":"uploads/HeaderImgs/2019-09-02/20190902170202187.png","total":"837.51","rank":9,"totle_temp":"837.51"},{"PlayerUUID":"2d7c53fc-6906-640e-3b4d-79da683989bf","nickname":"郁美净","imgURL":"uploads/HeaderImgs/2019-06-30/20190630101701904.png","total":"672.01","rank":10,"totle_temp":"672.01"}]
         */

        private UserRankInfoBean userRankInfo;
        private List<TopThreeBean> topThree;
        private List<OtherRankInfoBean> otherRankInfo;

        public UserRankInfoBean getUserRankInfo() {
            return userRankInfo;
        }

        public void setUserRankInfo(UserRankInfoBean userRankInfo) {
            this.userRankInfo = userRankInfo;
        }

        public List<TopThreeBean> getTopThree() {
            return topThree;
        }

        public void setTopThree(List<TopThreeBean> topThree) {
            this.topThree = topThree;
        }

        public List<OtherRankInfoBean> getOtherRankInfo() {
            return otherRankInfo;
        }

        public void setOtherRankInfo(List<OtherRankInfoBean> otherRankInfo) {
            this.otherRankInfo = otherRankInfo;
        }

        public static class UserRankInfoBean {
            /**
             * PlayerUUID : 658cec96-a558-c019-a190-bba51f57d3ec
             * nickname : 笨小孩
             * imgURL : uploads/HeaderImgs/2019-10-22/20191022111205865.png
             * total : 10.00
             * rank : 66
             * total_temp : 10.00
             */

            private String PlayerUUID;
            private String nickname;
            private String imgURL;
            private String total;
            private int rank;
            private String total_temp;

            public String getPlayerUUID() {
                return PlayerUUID;
            }

            public void setPlayerUUID(String PlayerUUID) {
                this.PlayerUUID = PlayerUUID;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public String getTotal_temp() {
                return total_temp;
            }

            public void setTotal_temp(String total_temp) {
                this.total_temp = total_temp;
            }
        }

        public static class TopThreeBean {
            /**
             * PlayerUUID : bdc1bf13-7f0a-e03c-2e17-68ae966d9557
             * nickname : 甲乙~刘志伟
             * imgURL : uploads/HeaderImgs/2019-06-14/20190614134509968.png
             * total : 1000248.84
             * rank : 1
             * totle_temp : 1000248.84
             */

            private String PlayerUUID;
            private String nickname;
            private String imgURL;
            private String total;
            private int rank;
            private String totle_temp;

            public String getPlayerUUID() {
                return PlayerUUID;
            }

            public void setPlayerUUID(String PlayerUUID) {
                this.PlayerUUID = PlayerUUID;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public String getTotle_temp() {
                return totle_temp;
            }

            public void setTotle_temp(String totle_temp) {
                this.totle_temp = totle_temp;
            }
        }

        public static class OtherRankInfoBean {
            /**
             * PlayerUUID : e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1
             * nickname : 不怕不怕
             * imgURL : uploads/HeaderImgs/2019-09-12/20190912135226894.png
             * total : 289999.41
             * rank : 4
             * totle_temp : 289999.41
             */

            private String PlayerUUID;
            private String nickname;
            private String imgURL;
            private String total;
            private int rank;
            private String totle_temp;

            public String getPlayerUUID() {
                return PlayerUUID;
            }

            public void setPlayerUUID(String PlayerUUID) {
                this.PlayerUUID = PlayerUUID;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public String getTotle_temp() {
                return totle_temp;
            }

            public void setTotle_temp(String totle_temp) {
                this.totle_temp = totle_temp;
            }
        }
    }
}
