package com.example.android.tiaozhan.Entity;

import java.util.List;

public class PingjiaTowEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"playerUUID":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","Team":"A队","nickname":"不怕不怕","imgURL":"uploads/HeaderImgs/2019-06-30/20190630105734560.png","Label":[{"id":10,"name":"客观填写比赛结果"},{"id":6,"name":"填写比赛结果不客观"}],"result":"A赢B"}]
     * other :
     */

    private int code;
    private String msg;
    private String other;
    private List<DataBean> data;

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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * playerUUID : e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1
         * Team : A队
         * nickname : 不怕不怕
         * imgURL : uploads/HeaderImgs/2019-06-30/20190630105734560.png
         * Label : [{"id":10,"name":"客观填写比赛结果"},{"id":6,"name":"填写比赛结果不客观"}]
         * result : A赢B
         */

        private String playerUUID;
        private String Team;
        private String nickname;
        private String imgURL;
        private String result;
        private List<LabelBean> Label;

        public String getPlayerUUID() {
            return playerUUID;
        }

        public void setPlayerUUID(String playerUUID) {
            this.playerUUID = playerUUID;
        }

        public String getTeam() {
            return Team;
        }

        public void setTeam(String Team) {
            this.Team = Team;
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

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public List<LabelBean> getLabel() {
            return Label;
        }

        public void setLabel(List<LabelBean> Label) {
            this.Label = Label;
        }

        public static class LabelBean {
            /**
             * id : 10
             * name : 客观填写比赛结果
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
