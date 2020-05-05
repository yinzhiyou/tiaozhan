package com.example.android.tiaozhan.Entity;

import java.util.List;

public class PingjiaListEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"siteId":"94da6c9c-8ced-d0e2-d54f-ad690d247134","Label":[{"id":1,"labelID":1,"starsID":1},{"id":2,"labelID":2,"starsID":1},{"id":3,"labelID":2,"starsID":2},{"id":4,"labelID":3,"starsID":2},{"id":5,"labelID":3,"starsID":1},{"id":6,"labelID":4,"starsID":1},{"id":7,"labelID":4,"starsID":2},{"id":8,"labelID":5,"starsID":1},{"id":9,"labelID":5,"starsID":2},{"id":12,"labelID":7,"starsID":3},{"id":13,"labelID":8,"starsID":3},{"id":14,"labelID":9,"starsID":3},{"id":15,"labelID":9,"starsID":4},{"id":19,"labelID":11,"starsID":3},{"id":20,"labelID":11,"starsID":4},{"id":21,"labelID":12,"starsID":4},{"id":22,"labelID":12,"starsID":5},{"id":23,"labelID":13,"starsID":5},{"id":24,"labelID":14,"starsID":5},{"id":25,"labelID":15,"starsID":1},{"id":26,"labelID":15,"starsID":2}],"usersInfo":[{"playerUUID":"5b727cac-581f-2e76-d955-bc902e863b22","Team":"A队","PublicUUID":"16984edf-5a8c-9c4e-68cf-27152a325570","isQuit":1,"nickname":"测试35","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105700974.png","result":"弃权","res":[{"id":12,"labelName":"神准时","labelSort":1,"isShow":1},{"id":13,"labelName":"很热情","labelSort":2,"isShow":1},{"id":14,"labelName":"很大度","labelSort":3,"isShow":1},{"id":10,"labelName":"客观填写比赛结果","isShow":1}]}]}
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
         * siteId : 94da6c9c-8ced-d0e2-d54f-ad690d247134
         * Label : [{"id":1,"labelID":1,"starsID":1},{"id":2,"labelID":2,"starsID":1},{"id":3,"labelID":2,"starsID":2},{"id":4,"labelID":3,"starsID":2},{"id":5,"labelID":3,"starsID":1},{"id":6,"labelID":4,"starsID":1},{"id":7,"labelID":4,"starsID":2},{"id":8,"labelID":5,"starsID":1},{"id":9,"labelID":5,"starsID":2},{"id":12,"labelID":7,"starsID":3},{"id":13,"labelID":8,"starsID":3},{"id":14,"labelID":9,"starsID":3},{"id":15,"labelID":9,"starsID":4},{"id":19,"labelID":11,"starsID":3},{"id":20,"labelID":11,"starsID":4},{"id":21,"labelID":12,"starsID":4},{"id":22,"labelID":12,"starsID":5},{"id":23,"labelID":13,"starsID":5},{"id":24,"labelID":14,"starsID":5},{"id":25,"labelID":15,"starsID":1},{"id":26,"labelID":15,"starsID":2}]
         * usersInfo : [{"playerUUID":"5b727cac-581f-2e76-d955-bc902e863b22","Team":"A队","PublicUUID":"16984edf-5a8c-9c4e-68cf-27152a325570","isQuit":1,"nickname":"测试35","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105700974.png","result":"弃权","res":[{"id":12,"labelName":"神准时","labelSort":1,"isShow":1},{"id":13,"labelName":"很热情","labelSort":2,"isShow":1},{"id":14,"labelName":"很大度","labelSort":3,"isShow":1},{"id":10,"labelName":"客观填写比赛结果","isShow":1}]}]
         */

        private String siteId;
        private List<LabelBean> Label;
        private List<UsersInfoBean> usersInfo;
        private String SportMode;

        public String getSportMode() {
            return SportMode;
        }

        public void setSportMode(String sportMode) {
            SportMode = sportMode;
        }
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
             * labelID : 1
             * starsID : 1
             */

            private int id;
            private int labelID;
            private int starsID;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLabelID() {
                return labelID;
            }

            public void setLabelID(int labelID) {
                this.labelID = labelID;
            }

            public int getStarsID() {
                return starsID;
            }

            public void setStarsID(int starsID) {
                this.starsID = starsID;
            }
        }

        public static class UsersInfoBean {
            /**
             * playerUUID : 5b727cac-581f-2e76-d955-bc902e863b22
             * Team : A队
             * PublicUUID : 16984edf-5a8c-9c4e-68cf-27152a325570
             * isQuit : 1
             * nickname : 测试35
             * imgURL : uploads/HeaderImgs/2019-09-17/20190917105700974.png
             * result : 弃权
             * res : [{"id":12,"labelName":"神准时","labelSort":1,"isShow":1},{"id":13,"labelName":"很热情","labelSort":2,"isShow":1},{"id":14,"labelName":"很大度","labelSort":3,"isShow":1},{"id":10,"labelName":"客观填写比赛结果","isShow":1}]
             */

            private String playerUUID;
            private String Team;
            private String PublicUUID;
            private int isQuit;
            private String nickname;
            private String imgURL;
            private String result;
            private List<ResBean> res;


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

            public String getPublicUUID() {
                return PublicUUID;
            }

            public void setPublicUUID(String PublicUUID) {
                this.PublicUUID = PublicUUID;
            }

            public int getIsQuit() {
                return isQuit;
            }

            public void setIsQuit(int isQuit) {
                this.isQuit = isQuit;
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

            public List<ResBean> getRes() {
                return res;
            }

            public void setRes(List<ResBean> res) {
                this.res = res;
            }

            public static class ResBean {
                /**
                 * id : 12
                 * labelName : 神准时
                 * labelSort : 1
                 * isShow : 1
                 */

                private int id;
                private String labelName;
                private int labelSort;
                private int isShow;
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
                    return labelName;
                }

                public void setLabelName(String labelName) {
                    this.labelName = labelName;
                }

                public int getLabelSort() {
                    return labelSort;
                }

                public void setLabelSort(int labelSort) {
                    this.labelSort = labelSort;
                }

                public int getIsShow() {
                    return isShow;
                }

                public void setIsShow(int isShow) {
                    this.isShow = isShow;
                }
            }
        }
    }
}
