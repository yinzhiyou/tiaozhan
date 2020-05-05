package com.example.android.tiaozhan.Entity;

import java.util.List;

public class DuihuanjiluEntity {


    /**
     * code : 2000
     * msg : 获取商品兑换记录数据成功
     * data : {"nowPage":1,"total":2,"Lst":[{"uuid":"d602d2ff-5a26-d150-89bd-b5b075124b6c","playeruuid":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","productuuid":"0cb7f808-1965-11e9-b43e-7cd30ac4e884","amount":"3","address":"北京市 北京市 通州区华远好天地A座","playername":"飞","playerphone":"18514253569","postcompany":"","postnumber":"","status":1,"exchagetime":"2019-08-26 16:15:53","dealtime":"","posttime":"","recivetime":"","comment":"","RejectComment":"","goodsName":"世达（star）VB4025 软式 中考 比赛训练 标准5号 耐磨PU 排球","goodsImg":"uploads/GoodsImgs/2019-01-16/20190116160219718.jpg","outCommonCoins":"11,400.00"},{"uuid":"aa7dead3-02f0-cfe7-2050-93a583b3d339","playeruuid":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","productuuid":"0be51e12-1979-11e9-b43e-7cd30ac4e884","amount":"1","address":"北京市 北京市 通州区华远好天地A座1107","playername":"飞","playerphone":"18514253569","postcompany":"","postnumber":"","status":1,"exchagetime":"2019-08-26 14:38:03","dealtime":"","posttime":"","recivetime":"","comment":"留下自己喜欢做","RejectComment":"","goodsName":"SPALDING 斯伯丁篮球 经典NBA儿童成人篮球 室内外比赛7号PU蓝球 76-167Y","goodsImg":"uploads/GoodsImgs/2019-01-16/20190116182528704.jpg","outCommonCoins":"6,950.00"}]}
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
         * nowPage : 1
         * total : 2
         * Lst : [{"uuid":"d602d2ff-5a26-d150-89bd-b5b075124b6c","playeruuid":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","productuuid":"0cb7f808-1965-11e9-b43e-7cd30ac4e884","amount":"3","address":"北京市 北京市 通州区华远好天地A座","playername":"飞","playerphone":"18514253569","postcompany":"","postnumber":"","status":1,"exchagetime":"2019-08-26 16:15:53","dealtime":"","posttime":"","recivetime":"","comment":"","RejectComment":"","goodsName":"世达（star）VB4025 软式 中考 比赛训练 标准5号 耐磨PU 排球","goodsImg":"uploads/GoodsImgs/2019-01-16/20190116160219718.jpg","outCommonCoins":"11,400.00"},{"uuid":"aa7dead3-02f0-cfe7-2050-93a583b3d339","playeruuid":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","productuuid":"0be51e12-1979-11e9-b43e-7cd30ac4e884","amount":"1","address":"北京市 北京市 通州区华远好天地A座1107","playername":"飞","playerphone":"18514253569","postcompany":"","postnumber":"","status":1,"exchagetime":"2019-08-26 14:38:03","dealtime":"","posttime":"","recivetime":"","comment":"留下自己喜欢做","RejectComment":"","goodsName":"SPALDING 斯伯丁篮球 经典NBA儿童成人篮球 室内外比赛7号PU蓝球 76-167Y","goodsImg":"uploads/GoodsImgs/2019-01-16/20190116182528704.jpg","outCommonCoins":"6,950.00"}]
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
             * uuid : d602d2ff-5a26-d150-89bd-b5b075124b6c
             * playeruuid : e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1
             * productuuid : 0cb7f808-1965-11e9-b43e-7cd30ac4e884
             * amount : 3
             * address : 北京市 北京市 通州区华远好天地A座
             * playername : 飞
             * playerphone : 18514253569
             * postcompany :
             * postnumber :
             * status : 1
             * exchagetime : 2019-08-26 16:15:53
             * dealtime :
             * posttime :
             * recivetime :
             * comment :
             * RejectComment :
             * goodsName : 世达（star）VB4025 软式 中考 比赛训练 标准5号 耐磨PU 排球
             * goodsImg : uploads/GoodsImgs/2019-01-16/20190116160219718.jpg
             * outCommonCoins : 11,400.00
             */

            private String uuid;
            private String playeruuid;
            private String productuuid;
            private String amount;
            private String address;
            private String playername;
            private String playerphone;
            private String postcompany;
            private String postnumber;
            private int status;
            private String exchagetime;
            private String dealtime;
            private String posttime;
            private String recivetime;
            private String comment;
            private String RejectComment;
            private String goodsName;
            private String goodsImg;
            private String outCommonCoins;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getPlayeruuid() {
                return playeruuid;
            }

            public void setPlayeruuid(String playeruuid) {
                this.playeruuid = playeruuid;
            }

            public String getProductuuid() {
                return productuuid;
            }

            public void setProductuuid(String productuuid) {
                this.productuuid = productuuid;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPlayername() {
                return playername;
            }

            public void setPlayername(String playername) {
                this.playername = playername;
            }

            public String getPlayerphone() {
                return playerphone;
            }

            public void setPlayerphone(String playerphone) {
                this.playerphone = playerphone;
            }

            public String getPostcompany() {
                return postcompany;
            }

            public void setPostcompany(String postcompany) {
                this.postcompany = postcompany;
            }

            public String getPostnumber() {
                return postnumber;
            }

            public void setPostnumber(String postnumber) {
                this.postnumber = postnumber;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getExchagetime() {
                return exchagetime;
            }

            public void setExchagetime(String exchagetime) {
                this.exchagetime = exchagetime;
            }

            public String getDealtime() {
                return dealtime;
            }

            public void setDealtime(String dealtime) {
                this.dealtime = dealtime;
            }

            public String getPosttime() {
                return posttime;
            }

            public void setPosttime(String posttime) {
                this.posttime = posttime;
            }

            public String getRecivetime() {
                return recivetime;
            }

            public void setRecivetime(String recivetime) {
                this.recivetime = recivetime;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getRejectComment() {
                return RejectComment;
            }

            public void setRejectComment(String RejectComment) {
                this.RejectComment = RejectComment;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public String getOutCommonCoins() {
                return outCommonCoins;
            }

            public void setOutCommonCoins(String outCommonCoins) {
                this.outCommonCoins = outCommonCoins;
            }
        }
    }
}
