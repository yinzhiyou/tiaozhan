package com.example.android.promoter.Entity;

import java.util.List;

public class XiaoxiXTEntity  {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":12,"Lst":[{"uuid":"ce154e9a-5483-9387-7de5-07edf2577053","title":"好友请求","playeruuid":"b08e78a9-7bf4-c5f9-16c4-7474290ea2a5","authoruuid":"743e0cd4-af41-7fec-dd5c-4ef0f29b696a","publicuuid":null,"content":"你瞅啥请求添加您为他的好友","isread":0,"addTime":"2018-12-20 10:08:13","msgCate":"systems","msgType":"addfriendsmsg","handleResult":0,"authornickname":"你瞅啥","authorimgURL":"uploads/2018-12-17/HeaderImgs/20181217115235972.png"}]}
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
         * nowPage : 1
         * total : 12
         * Lst : [{"uuid":"ce154e9a-5483-9387-7de5-07edf2577053","title":"好友请求","playeruuid":"b08e78a9-7bf4-c5f9-16c4-7474290ea2a5","authoruuid":"743e0cd4-af41-7fec-dd5c-4ef0f29b696a","publicuuid":null,"content":"你瞅啥请求添加您为他的好友","isread":0,"addTime":"2018-12-20 10:08:13","msgCate":"systems","msgType":"addfriendsmsg","handleResult":0,"authornickname":"你瞅啥","authorimgURL":"uploads/2018-12-17/HeaderImgs/20181217115235972.png"}]
         */

        private int nowPage;
        private int total;
        private List<LstBean> Lst;

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<LstBean> getLst() {
            return Lst;
        }

        public void setLst(List<LstBean> Lst) {
            this.Lst = Lst;
        }

        public static class LstBean {
            /**
             * uuid : ce154e9a-5483-9387-7de5-07edf2577053
             * title : 好友请求
             * playeruuid : b08e78a9-7bf4-c5f9-16c4-7474290ea2a5
             * authoruuid : 743e0cd4-af41-7fec-dd5c-4ef0f29b696a
             * publicuuid : null
             * content : 你瞅啥请求添加您为他的好友
             * isread : 0
             * addTime : 2018-12-20 10:08:13
             * msgCate : systems
             * msgType : addfriendsmsg
             * handleResult : 0
             * authornickname : 你瞅啥
             * authorimgURL : uploads/2018-12-17/HeaderImgs/20181217115235972.png
             */

            private String uuid;
            private String title;
            private String playeruuid;
            private String authoruuid;
            private Object publicuuid;
            private String content;
            private int isread;
            private String addTime;
            private String msgCate;
            private String msgType;
            private int handleResult;
            private String authornickname;
            private String authorimgURL;

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

            public Object getPublicuuid() {
                return publicuuid;
            }

            public void setPublicuuid(Object publicuuid) {
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

            public String getAuthornickname() {
                return authornickname;
            }

            public void setAuthornickname(String authornickname) {
                this.authornickname = authornickname;
            }

            public String getAuthorimgURL() {
                return authorimgURL;
            }

            public void setAuthorimgURL(String authorimgURL) {
                this.authorimgURL = authorimgURL;
            }
        }
    }
}
