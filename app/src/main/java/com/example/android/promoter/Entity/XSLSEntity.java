package com.example.android.promoter.Entity;

import java.util.List;

public class XSLSEntity {

    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-20 14:43:07","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-20 11:32:15","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-20 11:32:07","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-19 15:21:55","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-19 15:20:31","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-19 15:18:31","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待。","baseURL":null,"filesURL":"","intime":"2019-07-19 15:13:19","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"平台系统已经受理，正在核实，请耐心等待1。","baseURL":null,"filesURL":"","intime":"2019-07-13 15:06:52","type":2,"playerName":"平台系统","playerimgurl":null},{"title":"合作场馆未预留场地","baseURL":null,"filesURL":"","intime":"2019-07-13 15:05:12","type":0,"playerName":"hello kittiy","playerimgurl":"uploads/HeaderImgs/2019-05-14/20190514143048459.png"}]
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
         * title : 平台系统已经受理，正在核实，请耐心等待。
         * baseURL : null
         * filesURL :
         * intime : 2019-07-20 14:43:07
         * type : 2
         * playerName : 平台系统
         * playerimgurl : null
         */

        private String title;
        private Object baseURL;
        private String filesURL;
        private String intime;
        private int type;
        private String playerName;
        private Object playerimgurl;

        private String comment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getBaseURL() {
            return baseURL;
        }

        public void setBaseURL(Object baseURL) {
            this.baseURL = baseURL;
        }

        public String getFilesURL() {
            return filesURL;
        }

        public void setFilesURL(String filesURL) {
            this.filesURL = filesURL;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public Object getPlayerimgurl() {
            return playerimgurl;
        }

        public void setPlayerimgurl(Object playerimgurl) {
            this.playerimgurl = playerimgurl;
        }
    }
}
