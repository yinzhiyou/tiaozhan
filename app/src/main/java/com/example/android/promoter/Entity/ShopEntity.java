package com.example.android.promoter.Entity;

import java.util.List;

public class ShopEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":56,"Lst":[{"UUID":"011201bc-1972-11e9-b43e-7cd30ac4e884","name":"HEAD海德网球拍 Spark MX Tour碳素拍 男女通用","price":358,"cost":17900,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116173503748.jpg","sportid":7,"isAvailueble":1,"sportName":"网球","isExchange":0},{"UUID":"04d71ca9-1955-11e9-b43e-7cd30ac4e884","name":"尤尼克斯 YONEX羽毛球AS-9耐打王yy训练比赛鹅毛 12只装","price":109,"cost":5450,"cutoff":0,"Amount":10,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116140734282.jpg","sportid":1,"isAvailueble":1,"sportName":"羽毛球","isExchange":0},{"UUID":"0be51e12-1979-11e9-b43e-7cd30ac4e884","name":"SPALDING 斯伯丁篮球 经典NBA儿童成人篮球 室内外比赛7号PU蓝球 76-167Y","price":139,"cost":6950,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116182528704.jpg","sportid":4,"isAvailueble":1,"sportName":"篮球","isExchange":0},{"UUID":"0cb7f808-1965-11e9-b43e-7cd30ac4e884","name":"世达（star）VB4025 软式 中考 比赛训练 标准5号 耐磨PU 排球","price":76,"cost":3800,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116160219718.jpg","sportid":6,"isAvailueble":1,"sportName":"排球","isExchange":0},{"UUID":"138b91cf-1b04-11e9-b43e-7cd30ac4e884","name":"耐克(NIKE)包 运动包 双肩包 Brasilia Training背包 BA5329-020 黑","price":189,"cost":9450,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-18/20190118173312393.jpg","sportid":9,"isAvailueble":1,"sportName":"其它","isExchange":0},{"UUID":"175ccd69-1e1b-11e9-b43e-7cd30ac4e884","name":"PGM 室内高尔夫 高尔夫练习器 高尔夫练习毯套装 办公室练习器套装 3米练习器+推杆+10个球","price":278,"cost":13900,"cutoff":0,"Amount":10,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-22/20190122155530475.jpg","sportid":8,"isAvailueble":1,"sportName":"高尔夫","isExchange":0},{"UUID":"19e0e822-196e-11e9-b43e-7cd30ac4e884","name":"威尔胜Wilson WRT106200 美网比赛训练网球 塑罐3粒装","price":60,"cost":3000,"cutoff":0,"Amount":29,"maxexchangeamount":10,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116170707721.jpg","sportid":7,"isAvailueble":1,"sportName":"网球","isExchange":0},{"UUID":"1b40113d-1b07-11e9-b43e-7cd30ac4e884","name":"耐克(NIKE)包 运动包 学生书包 运动背包 男女双肩包 电脑背包 BA5217-010","price":229,"cost":11450,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-18/20190118175454902.jpg","sportid":9,"isAvailueble":1,"sportName":"其它","isExchange":0},{"UUID":"1d58a5db-1973-11e9-b43e-7cd30ac4e884","name":"ENPEX乐士 A98 休闲娱乐 男女初学者网拍 铝合金网球拍 已穿线","price":59,"cost":2950,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116174300705.jpg","sportid":7,"isAvailueble":1,"sportName":"网球","isExchange":0},{"UUID":"227eecec-195d-11e9-b43e-7cd30ac4e884","name":"红双喜DHS乒乓球拍 横拍双面反胶弧圈结合快攻4星R4002（单块装）","price":99.9,"cost":4995,"cutoff":0,"Amount":29,"maxexchangeamount":10,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116150537252.jpg","sportid":2,"isAvailueble":1,"sportName":"乒乓球","isExchange":0}]}
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
         * total : 56
         * Lst : [{"UUID":"011201bc-1972-11e9-b43e-7cd30ac4e884","name":"HEAD海德网球拍 Spark MX Tour碳素拍 男女通用","price":358,"cost":17900,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116173503748.jpg","sportid":7,"isAvailueble":1,"sportName":"网球","isExchange":0},{"UUID":"04d71ca9-1955-11e9-b43e-7cd30ac4e884","name":"尤尼克斯 YONEX羽毛球AS-9耐打王yy训练比赛鹅毛 12只装","price":109,"cost":5450,"cutoff":0,"Amount":10,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116140734282.jpg","sportid":1,"isAvailueble":1,"sportName":"羽毛球","isExchange":0},{"UUID":"0be51e12-1979-11e9-b43e-7cd30ac4e884","name":"SPALDING 斯伯丁篮球 经典NBA儿童成人篮球 室内外比赛7号PU蓝球 76-167Y","price":139,"cost":6950,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116182528704.jpg","sportid":4,"isAvailueble":1,"sportName":"篮球","isExchange":0},{"UUID":"0cb7f808-1965-11e9-b43e-7cd30ac4e884","name":"世达（star）VB4025 软式 中考 比赛训练 标准5号 耐磨PU 排球","price":76,"cost":3800,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116160219718.jpg","sportid":6,"isAvailueble":1,"sportName":"排球","isExchange":0},{"UUID":"138b91cf-1b04-11e9-b43e-7cd30ac4e884","name":"耐克(NIKE)包 运动包 双肩包 Brasilia Training背包 BA5329-020 黑","price":189,"cost":9450,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-18/20190118173312393.jpg","sportid":9,"isAvailueble":1,"sportName":"其它","isExchange":0},{"UUID":"175ccd69-1e1b-11e9-b43e-7cd30ac4e884","name":"PGM 室内高尔夫 高尔夫练习器 高尔夫练习毯套装 办公室练习器套装 3米练习器+推杆+10个球","price":278,"cost":13900,"cutoff":0,"Amount":10,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-22/20190122155530475.jpg","sportid":8,"isAvailueble":1,"sportName":"高尔夫","isExchange":0},{"UUID":"19e0e822-196e-11e9-b43e-7cd30ac4e884","name":"威尔胜Wilson WRT106200 美网比赛训练网球 塑罐3粒装","price":60,"cost":3000,"cutoff":0,"Amount":29,"maxexchangeamount":10,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116170707721.jpg","sportid":7,"isAvailueble":1,"sportName":"网球","isExchange":0},{"UUID":"1b40113d-1b07-11e9-b43e-7cd30ac4e884","name":"耐克(NIKE)包 运动包 学生书包 运动背包 男女双肩包 电脑背包 BA5217-010","price":229,"cost":11450,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-18/20190118175454902.jpg","sportid":9,"isAvailueble":1,"sportName":"其它","isExchange":0},{"UUID":"1d58a5db-1973-11e9-b43e-7cd30ac4e884","name":"ENPEX乐士 A98 休闲娱乐 男女初学者网拍 铝合金网球拍 已穿线","price":59,"cost":2950,"cutoff":0,"Amount":29,"maxexchangeamount":5,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116174300705.jpg","sportid":7,"isAvailueble":1,"sportName":"网球","isExchange":0},{"UUID":"227eecec-195d-11e9-b43e-7cd30ac4e884","name":"红双喜DHS乒乓球拍 横拍双面反胶弧圈结合快攻4星R4002（单块装）","price":99.9,"cost":4995,"cutoff":0,"Amount":29,"maxexchangeamount":10,"imgpath":"uploads/GoodsImgs/2019-01-16/20190116150537252.jpg","sportid":2,"isAvailueble":1,"sportName":"乒乓球","isExchange":0}]
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
             * UUID : 011201bc-1972-11e9-b43e-7cd30ac4e884
             * name : HEAD海德网球拍 Spark MX Tour碳素拍 男女通用
             * price : 358
             * cost : 17900
             * cutoff : 0
             * Amount : 29
             * maxexchangeamount : 5
             * imgpath : uploads/GoodsImgs/2019-01-16/20190116173503748.jpg
             * sportid : 7
             * isAvailueble : 1
             * sportName : 网球
             * isExchange : 0
             */

            private String UUID;
            private String name;
            private Double price;
            private int cost;
            private int cutoff;
            private int Amount;
            private int maxexchangeamount;
            private String imgpath;
            private int sportid;
            private int isAvailueble;
            private String sportName;
            private int isExchange;

            public String getUUID() {
                return UUID;
            }

            public void setUUID(String UUID) {
                this.UUID = UUID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public int getCutoff() {
                return cutoff;
            }

            public void setCutoff(int cutoff) {
                this.cutoff = cutoff;
            }

            public int getAmount() {
                return Amount;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public int getMaxexchangeamount() {
                return maxexchangeamount;
            }

            public void setMaxexchangeamount(int maxexchangeamount) {
                this.maxexchangeamount = maxexchangeamount;
            }

            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
            }

            public int getSportid() {
                return sportid;
            }

            public void setSportid(int sportid) {
                this.sportid = sportid;
            }

            public int getIsAvailueble() {
                return isAvailueble;
            }

            public void setIsAvailueble(int isAvailueble) {
                this.isAvailueble = isAvailueble;
            }

            public String getSportName() {
                return sportName;
            }

            public void setSportName(String sportName) {
                this.sportName = sportName;
            }

            public int getIsExchange() {
                return isExchange;
            }

            public void setIsExchange(int isExchange) {
                this.isExchange = isExchange;
            }
        }
    }
}
