package com.example.android.promoter.Entity;

import java.util.List;

public class PaihangEntity  {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"userRankInfo":{"PlayerUUID":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","nickname":"不怕不怕","imgURL":"uploads/HeaderImgs/2019-09-12/20190912135226894.png","total":"312.53","rank":66,"level":"Lv5","total_temp":"312.53"},"topThree":[{"PlayerUUID":"db354c10-94d2-c9d0-aeeb-5e5790a997d3","nickname":"测试0412","imgURL":"uploads/HeaderImgs/2019-11-07/20191107151313869.png","total":"9937.00","rank":1,"totle_temp":"9937.00","level":"Lv10"},{"PlayerUUID":"e2aa2ac7-2bad-1b33-4c1b-95f28e7cb7a7","nickname":"测试0314","imgURL":"uploads/HeaderImgs/2019-11-07/20191107151501929.png","total":"9776.00","rank":2,"totle_temp":"9776.00","level":"Lv10"},{"PlayerUUID":"4a352032-f6d6-329a-6180-c2e9ec782e89","nickname":"测试28","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104734160.png","total":"9219.00","rank":3,"totle_temp":"9219.00","level":"Lv10"}],"otherRankInfo":[{"PlayerUUID":"3741f435-ffb4-d258-5e78-6115a4401952","nickname":"测试23","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104116128.png","total":"9138.00","rank":4,"totle_temp":"9138.00","level":"Lv10"},{"PlayerUUID":"bff98f69-d31c-b8d0-1889-b4d4ae3632ba","nickname":"测试52","imgURL":"uploads/HeaderImgs/2019-09-18/20190918113602399.png","total":"8871.00","rank":5,"totle_temp":"8871.00","level":"Lv10"},{"PlayerUUID":"564d5f9a-69c8-73ea-7417-1313a7799e3f","nickname":"测试34","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105534104.png","total":"8860.00","rank":6,"totle_temp":"8860.00","level":"Lv10"},{"PlayerUUID":"b36ab2fd-57d9-9992-4317-ec503678d44f","nickname":"测试50","imgURL":"uploads/HeaderImgs/2019-09-18/20190918113409131.png","total":"8499.00","rank":7,"totle_temp":"8499.00","level":"Lv10"},{"PlayerUUID":"ec2dcd95-524c-ec68-96c5-d0bba455881a","nickname":"测试0222","imgURL":"uploads/HeaderImgs/2019-11-07/20191107151918715.png","total":"8464.00","rank":8,"totle_temp":"8464.00","level":"Lv10"},{"PlayerUUID":"d1e243bb-d5ad-6479-6e97-14e791f4d5be","nickname":"测试021","imgURL":"uploads/HeaderImgs/2019-11-07/20191107150743281.png","total":"8435.00","rank":9,"totle_temp":"8435.00","level":"Lv10"},{"PlayerUUID":"4c73e2e7-3540-5674-9ed2-40c02173e38f","nickname":"测试29","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104852148.png","total":"8420.00","rank":10,"totle_temp":"8420.00","level":"Lv10"}]}
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
         * userRankInfo : {"PlayerUUID":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","nickname":"不怕不怕","imgURL":"uploads/HeaderImgs/2019-09-12/20190912135226894.png","total":"312.53","rank":66,"level":"Lv5","total_temp":"312.53"}
         * topThree : [{"PlayerUUID":"db354c10-94d2-c9d0-aeeb-5e5790a997d3","nickname":"测试0412","imgURL":"uploads/HeaderImgs/2019-11-07/20191107151313869.png","total":"9937.00","rank":1,"totle_temp":"9937.00","level":"Lv10"},{"PlayerUUID":"e2aa2ac7-2bad-1b33-4c1b-95f28e7cb7a7","nickname":"测试0314","imgURL":"uploads/HeaderImgs/2019-11-07/20191107151501929.png","total":"9776.00","rank":2,"totle_temp":"9776.00","level":"Lv10"},{"PlayerUUID":"4a352032-f6d6-329a-6180-c2e9ec782e89","nickname":"测试28","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104734160.png","total":"9219.00","rank":3,"totle_temp":"9219.00","level":"Lv10"}]
         * otherRankInfo : [{"PlayerUUID":"3741f435-ffb4-d258-5e78-6115a4401952","nickname":"测试23","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104116128.png","total":"9138.00","rank":4,"totle_temp":"9138.00","level":"Lv10"},{"PlayerUUID":"bff98f69-d31c-b8d0-1889-b4d4ae3632ba","nickname":"测试52","imgURL":"uploads/HeaderImgs/2019-09-18/20190918113602399.png","total":"8871.00","rank":5,"totle_temp":"8871.00","level":"Lv10"},{"PlayerUUID":"564d5f9a-69c8-73ea-7417-1313a7799e3f","nickname":"测试34","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105534104.png","total":"8860.00","rank":6,"totle_temp":"8860.00","level":"Lv10"},{"PlayerUUID":"b36ab2fd-57d9-9992-4317-ec503678d44f","nickname":"测试50","imgURL":"uploads/HeaderImgs/2019-09-18/20190918113409131.png","total":"8499.00","rank":7,"totle_temp":"8499.00","level":"Lv10"},{"PlayerUUID":"ec2dcd95-524c-ec68-96c5-d0bba455881a","nickname":"测试0222","imgURL":"uploads/HeaderImgs/2019-11-07/20191107151918715.png","total":"8464.00","rank":8,"totle_temp":"8464.00","level":"Lv10"},{"PlayerUUID":"d1e243bb-d5ad-6479-6e97-14e791f4d5be","nickname":"测试021","imgURL":"uploads/HeaderImgs/2019-11-07/20191107150743281.png","total":"8435.00","rank":9,"totle_temp":"8435.00","level":"Lv10"},{"PlayerUUID":"4c73e2e7-3540-5674-9ed2-40c02173e38f","nickname":"测试29","imgURL":"uploads/HeaderImgs/2019-09-17/20190917104852148.png","total":"8420.00","rank":10,"totle_temp":"8420.00","level":"Lv10"}]
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
             * PlayerUUID : e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1
             * nickname : 不怕不怕
             * imgURL : uploads/HeaderImgs/2019-09-12/20190912135226894.png
             * total : 312.53
             * rank : 66
             * level : Lv5
             * total_temp : 312.53
             */

            private String PlayerUUID;
            private String nickname;
            private String imgURL;
            private String total;
            private int rank;
            private String level;
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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
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
             * PlayerUUID : db354c10-94d2-c9d0-aeeb-5e5790a997d3
             * nickname : 测试0412
             * imgURL : uploads/HeaderImgs/2019-11-07/20191107151313869.png
             * total : 9937.00
             * rank : 1
             * totle_temp : 9937.00
             * level : Lv10
             */

            private String PlayerUUID;
            private String nickname;
            private String imgURL;
            private String total;
            private int rank;
            private String totle_temp;
            private String level;

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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }

        public static class OtherRankInfoBean {
            /**
             * PlayerUUID : 3741f435-ffb4-d258-5e78-6115a4401952
             * nickname : 测试23
             * imgURL : uploads/HeaderImgs/2019-09-17/20190917104116128.png
             * total : 9138.00
             * rank : 4
             * totle_temp : 9138.00
             * level : Lv10
             */

            private String PlayerUUID;
            private String nickname;
            private String imgURL;
            private String total;
            private int rank;
            private String totle_temp;
            private String level;

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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
