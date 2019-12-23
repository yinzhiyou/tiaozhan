package com.example.android.promoter.Entity;

import java.util.List;

public class PingjiaListEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"siteId":"3476c8dbffbfd728cc06a407","Label":[{"id":1,"LabelName":"技术很棒"},{"id":2,"LabelName":"态度很好"},{"id":3,"LabelName":"技术很差"},{"id":4,"LabelName":"人品不行"},{"id":5,"LabelName":"人没到"},{"id":6,"LabelName":"球品不行"},{"id":7,"LabelName":"态度恶劣"}],"usersInfo":[{"playerUUID":"421739b9-3d0e-8d9b-877c-5f26d192ea96","Team":"A队","nickname":"滴滴滴","imgURL":"uploads/2018-12-18/HeaderImgs/20181218161542870.png"}]}
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
         * siteId : 3476c8dbffbfd728cc06a407
         * Label : [{"id":1,"LabelName":"技术很棒"},{"id":2,"LabelName":"态度很好"},{"id":3,"LabelName":"技术很差"},{"id":4,"LabelName":"人品不行"},{"id":5,"LabelName":"人没到"},{"id":6,"LabelName":"球品不行"},{"id":7,"LabelName":"态度恶劣"}]
         * usersInfo : [{"playerUUID":"421739b9-3d0e-8d9b-877c-5f26d192ea96","Team":"A队","nickname":"滴滴滴","imgURL":"uploads/2018-12-18/HeaderImgs/20181218161542870.png"}]
         */

        private String siteId;
        private List<LabelBean> Label;
        private List<UsersInfoBean> usersInfo;

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public List<LabelBean> getLabel() {
            return Label;
        }

        public void setLabel(List<LabelBean> Label) {
            this.Label = Label;
        }

        public List<UsersInfoBean> getUsersInfo() {
            return usersInfo;
        }

        public void setUsersInfo(List<UsersInfoBean> usersInfo) {
            this.usersInfo = usersInfo;
        }

        public static class LabelBean {
            /**
             * id : 1
             * LabelName : 技术很棒
             */

            private int id;
            private String LabelName;
            private boolean select;
            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabelName() {
                return LabelName;
            }

            public void setLabelName(String LabelName) {
                this.LabelName = LabelName;
            }
        }

        public static class UsersInfoBean {
            /**
             * playerUUID : 421739b9-3d0e-8d9b-877c-5f26d192ea96
             * Team : A队
             * nickname : 滴滴滴
             * imgURL : uploads/2018-12-18/HeaderImgs/20181218161542870.png
             */

            private String playerUUID;
            private String Team;
            private String nickname;
            private String imgURL;

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
        }
    }
}
