package com.example.android.promoter.Entity;

import java.util.List;

public class FQTZXiangmuEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":1,"name":"羽毛球","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1,"sportType":[{"id":4,"sportid":1,"name":"单打","playerNumber":2,"PrepareMinite":30},{"id":5,"sportid":1,"name":"双打","playerNumber":4,"PrepareMinite":20}]},{"id":2,"name":"乒乓球","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1,"sportType":[{"id":6,"sportid":2,"name":"单打","playerNumber":2,"PrepareMinite":30},{"id":7,"sportid":2,"name":"双打","playerNumber":4,"PrepareMinite":60}]},{"id":3,"name":"台球","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1,"sportType":[{"id":1,"sportid":3,"name":"中式黑八","playerNumber":2,"PrepareMinite":30},{"id":2,"sportid":3,"name":"美式9球","playerNumber":2,"PrepareMinite":60},{"id":3,"sportid":3,"name":"斯诺克","playerNumber":2,"PrepareMinite":30}]},{"id":4,"name":"篮球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":0,"TrainingModeLevel":1,"sportType":[{"id":10,"sportid":4,"name":"5v5","playerNumber":10,"PrepareMinite":90},{"id":11,"sportid":4,"name":"3v3","playerNumber":6,"PrepareMinite":90}]},{"id":5,"name":"足球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":0,"TrainingModeLevel":1,"sportType":[{"id":13,"sportid":5,"name":"11人制","playerNumber":22,"PrepareMinite":120},{"id":14,"sportid":5,"name":"8人制","playerNumber":16,"PrepareMinite":120},{"id":15,"sportid":5,"name":"7人制","playerNumber":14,"PrepareMinite":120},{"id":16,"sportid":5,"name":"5人制","playerNumber":10,"PrepareMinite":120}]},{"id":6,"name":"排球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":0,"TrainingModeLevel":1,"sportType":[{"id":12,"sportid":6,"name":"6v6","playerNumber":12,"PrepareMinite":90}]},{"id":7,"name":"网球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":1,"TrainingModeLevel":1,"sportType":[{"id":8,"sportid":7,"name":"单打","playerNumber":2,"PrepareMinite":60},{"id":9,"sportid":7,"name":"双打","playerNumber":4,"PrepareMinite":60}]},{"id":8,"name":"高尔夫","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1,"sportType":[{"id":17,"sportid":8,"name":"比杆赛","playerNumber":2,"PrepareMinite":120},{"id":18,"sportid":8,"name":"比洞赛","playerNumber":2,"PrepareMinite":120}]}]
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 羽毛球
         * AllowFunyMode : 1
         * AllowCompitationMode : 1
         * AllowTrainingMode : 1
         * TrainingModeLevel : 1
         * sportType : [{"id":4,"sportid":1,"name":"单打","playerNumber":2,"PrepareMinite":30},{"id":5,"sportid":1,"name":"双打","playerNumber":4,"PrepareMinite":20}]
         */

        private int id;
        private String name;
        private int AllowFunyMode;
        private int AllowCompitationMode;
        private int AllowTrainingMode;
        private int TrainingModeLevel;
        private List<SportTypeBean> sportType;

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

        public int getAllowFunyMode() {
            return AllowFunyMode;
        }

        public void setAllowFunyMode(int AllowFunyMode) {
            this.AllowFunyMode = AllowFunyMode;
        }

        public int getAllowCompitationMode() {
            return AllowCompitationMode;
        }

        public void setAllowCompitationMode(int AllowCompitationMode) {
            this.AllowCompitationMode = AllowCompitationMode;
        }

        public int getAllowTrainingMode() {
            return AllowTrainingMode;
        }

        public void setAllowTrainingMode(int AllowTrainingMode) {
            this.AllowTrainingMode = AllowTrainingMode;
        }

        public int getTrainingModeLevel() {
            return TrainingModeLevel;
        }

        public void setTrainingModeLevel(int TrainingModeLevel) {
            this.TrainingModeLevel = TrainingModeLevel;
        }

        public List<SportTypeBean> getSportType() {
            return sportType;
        }

        public void setSportType(List<SportTypeBean> sportType) {
            this.sportType = sportType;
        }

        public static class SportTypeBean {
            /**
             * id : 4
             * sportid : 1
             * name : 单打
             * playerNumber : 2
             * PrepareMinite : 30
             */

            private int id;
            private int sportid;
            private String name;
            private int playerNumber;
            private int PrepareMinite;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSportid() {
                return sportid;
            }

            public void setSportid(int sportid) {
                this.sportid = sportid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPlayerNumber() {
                return playerNumber;
            }

            public void setPlayerNumber(int playerNumber) {
                this.playerNumber = playerNumber;
            }

            public int getPrepareMinite() {
                return PrepareMinite;
            }

            public void setPrepareMinite(int PrepareMinite) {
                this.PrepareMinite = PrepareMinite;
            }
        }
    }
}
