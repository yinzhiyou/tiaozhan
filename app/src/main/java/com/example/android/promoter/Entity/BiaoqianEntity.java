package com.example.android.promoter.Entity;

import java.util.List;

public class BiaoqianEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":2,"labelName":"严重迟到","labelLevel":1,"isShow":1},{"id":3,"labelName":"态度恶劣","labelLevel":1,"isShow":1},{"id":4,"labelName":"不承认自己犯规","labelLevel":1,"isShow":1},{"id":5,"labelName":"错认对手犯规","labelLevel":1,"isShow":1},{"id":6,"labelName":"填写结果不客观","labelLevel":1,"isShow":1}]
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
         * id : 2
         * labelName : 严重迟到
         * labelLevel : 1
         * isShow : 1
         */

        private int id;
        private String labelName;
        private int labelLevel;
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

        public int getLabelLevel() {
            return labelLevel;
        }

        public void setLabelLevel(int labelLevel) {
            this.labelLevel = labelLevel;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }
    }
}
