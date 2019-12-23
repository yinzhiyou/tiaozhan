package com.example.android.promoter.Entity;

import java.io.Serializable;
import java.util.List;

public class YundongEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":1,"name":"羽毛球","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1},{"id":2,"name":"乒乓球","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1},{"id":3,"name":"台球","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1},{"id":4,"name":"篮球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":0,"TrainingModeLevel":1},{"id":5,"name":"足球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":0,"TrainingModeLevel":1},{"id":6,"name":"排球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":0,"TrainingModeLevel":1},{"id":7,"name":"网球","AllowFunyMode":1,"AllowCompitationMode":0,"AllowTrainingMode":1,"TrainingModeLevel":1},{"id":8,"name":"高尔夫","AllowFunyMode":1,"AllowCompitationMode":1,"AllowTrainingMode":1,"TrainingModeLevel":1}]
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
         */

        private int id;
        private String name;
        private int AllowFunyMode;
        private int AllowCompitationMode;
        private int AllowTrainingMode;
        private int TrainingModeLevel;
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
    }
}
