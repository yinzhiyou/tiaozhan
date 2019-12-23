package com.example.android.promoter.Entity;

public class XiaoxiItemEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"uuid":"547fa987-c8d4-6165-5b42-0f8b11e0b292","title":"活动通知","playeruuid":"26d00c58-3004-c05c-bf31-996578f9f5d4","authoruuid":"系统管家","publicuuid":"b61e7774-c87a-4731-2302-62bd84cc5c87","content":"您好，2019-09-05 17:30:00~2019-09-05 18:00:00在2北京甲乙电子商务的乒乓球-双打活动已结束，请客观填写比赛结果。","isread":0,"addTime":"2019-09-05 18:00:02","msgCate":"systems","msgType":"sysmsg","handleResult":0}
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
         * uuid : 547fa987-c8d4-6165-5b42-0f8b11e0b292
         * title : 活动通知
         * playeruuid : 26d00c58-3004-c05c-bf31-996578f9f5d4
         * authoruuid : 系统管家
         * publicuuid : b61e7774-c87a-4731-2302-62bd84cc5c87
         * content : 您好，2019-09-05 17:30:00~2019-09-05 18:00:00在2北京甲乙电子商务的乒乓球-双打活动已结束，请客观填写比赛结果。
         * isread : 0
         * addTime : 2019-09-05 18:00:02
         * msgCate : systems
         * msgType : sysmsg
         * handleResult : 0
         */

        private String uuid;
        private String title;
        private String playeruuid;
        private String authoruuid;
        private String publicuuid;
        private String content;
        private int isread;
        private String addTime;
        private String msgCate;
        private String msgType;
        private int handleResult;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlayeruuid() {
            return playeruuid;
        }

        public void setPlayeruuid(String playeruuid) {
            this.playeruuid = playeruuid;
        }

        public String getAuthoruuid() {
            return authoruuid;
        }

        public void setAuthoruuid(String authoruuid) {
            this.authoruuid = authoruuid;
        }

        public String getPublicuuid() {
            return publicuuid;
        }

        public void setPublicuuid(String publicuuid) {
            this.publicuuid = publicuuid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsread() {
            return isread;
        }

        public void setIsread(int isread) {
            this.isread = isread;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getMsgCate() {
            return msgCate;
        }

        public void setMsgCate(String msgCate) {
            this.msgCate = msgCate;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public int getHandleResult() {
            return handleResult;
        }

        public void setHandleResult(int handleResult) {
            this.handleResult = handleResult;
        }
    }
}
