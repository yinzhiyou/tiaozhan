package com.example.android.tiaozhan.Entity;

import java.util.List;

public class YonghuBQEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","LabelID":2,"LabelCount":70,"LabelName":"态度很好"},{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","LabelID":5,"LabelCount":57,"LabelName":"人没到"},{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","LabelID":6,"LabelCount":44,"LabelName":"球品不行"},{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","LabelID":1,"LabelCount":75,"LabelName":"技术很棒"},{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","LabelID":3,"LabelCount":80,"LabelName":"技术很差"},{"PlayerUUID":"2d04c573-4052-ea7d-05c6-08f379b02155","LabelID":7,"LabelCount":29,"LabelName":"态度恶劣"}]
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
         * PlayerUUID : 2d04c573-4052-ea7d-05c6-08f379b02155
         * LabelID : 2
         * LabelCount : 70
         * LabelName : 态度很好
         */

        private String PlayerUUID;
        private int LabelID;
        private int LabelCount;
        private String LabelName;

        public String getPlayerUUID() {
            return PlayerUUID;
        }

        public void setPlayerUUID(String PlayerUUID) {
            this.PlayerUUID = PlayerUUID;
        }

        public int getLabelID() {
            return LabelID;
        }

        public void setLabelID(int LabelID) {
            this.LabelID = LabelID;
        }

        public int getLabelCount() {
            return LabelCount;
        }

        public void setLabelCount(int LabelCount) {
            this.LabelCount = LabelCount;
        }

        public String getLabelName() {
            return LabelName;
        }

        public void setLabelName(String LabelName) {
            this.LabelName = LabelName;
        }
    }
}
