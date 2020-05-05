package com.example.android.tiaozhan.Entity;

import java.util.List;

public class ShopItemEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"id":14,"gooduid":"04d71ca9-1955-11e9-b43e-7cd30ac4e884","gooddesc":"品牌     尤尼克斯\n型号     As-9\n规格     12只装\n材质     鹅毛，复合软木\n球速     NO1 NO2 NO3随机发货\n适用人群 团体俱乐部","imagecount":8,"imgurl":["uploads/GoodsImgs/2019-01-17/20190117105354841.jpg","uploads/GoodsImgs/2019-01-17/20190117105354717.jpg","uploads/GoodsImgs/2019-01-17/20190117105354960.jpg","uploads/GoodsImgs/2019-01-17/20190117105354307.jpg","uploads/GoodsImgs/2019-01-17/20190117105354130.jpg","uploads/GoodsImgs/2019-01-17/20190117105354624.jpg","uploads/GoodsImgs/2019-01-17/20190117105355738.jpg","uploads/GoodsImgs/2019-01-17/20190117105355351.jpg"],"propertys":["\"\" = \"\"","\"型号\" = \"AS-9\"","\"规格\" = \"12只\"","\"材质\" = \"鹅毛，复合软木\""],"isExchange":0}
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
         * id : 14
         * gooduid : 04d71ca9-1955-11e9-b43e-7cd30ac4e884
         * gooddesc : 品牌     尤尼克斯
         型号     As-9
         规格     12只装
         材质     鹅毛，复合软木
         球速     NO1 NO2 NO3随机发货
         适用人群 团体俱乐部
         * imagecount : 8
         * imgurl : ["uploads/GoodsImgs/2019-01-17/20190117105354841.jpg","uploads/GoodsImgs/2019-01-17/20190117105354717.jpg","uploads/GoodsImgs/2019-01-17/20190117105354960.jpg","uploads/GoodsImgs/2019-01-17/20190117105354307.jpg","uploads/GoodsImgs/2019-01-17/20190117105354130.jpg","uploads/GoodsImgs/2019-01-17/20190117105354624.jpg","uploads/GoodsImgs/2019-01-17/20190117105355738.jpg","uploads/GoodsImgs/2019-01-17/20190117105355351.jpg"]
         * propertys : ["\"\" = \"\"","\"型号\" = \"AS-9\"","\"规格\" = \"12只\"","\"材质\" = \"鹅毛，复合软木\""]
         * isExchange : 0
         */

        private int id;
        private String gooduid;
        private String gooddesc;
        private int imagecount;
        private int isExchange;
        private List<String> imgurl;
        private List<String> propertys;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGooduid() {
            return gooduid;
        }

        public void setGooduid(String gooduid) {
            this.gooduid = gooduid;
        }

        public String getGooddesc() {
            return gooddesc;
        }

        public void setGooddesc(String gooddesc) {
            this.gooddesc = gooddesc;
        }

        public int getImagecount() {
            return imagecount;
        }

        public void setImagecount(int imagecount) {
            this.imagecount = imagecount;
        }

        public int getIsExchange() {
            return isExchange;
        }

        public void setIsExchange(int isExchange) {
            this.isExchange = isExchange;
        }

        public List<String> getImgurl() {
            return imgurl;
        }

        public void setImgurl(List<String> imgurl) {
            this.imgurl = imgurl;
        }

        public List<String> getPropertys() {
            return propertys;
        }

        public void setPropertys(List<String> propertys) {
            this.propertys = propertys;
        }
    }
}
