package com.example.android.tiaozhan.Entity;

import java.util.List;

public class ShopLunboEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":1,"name":"精品banner图","picurl":"uploads/AdvImgs/2019-03-07/20190307172810275.png","desc":"精品大图","city":"","area":"","places":"商城banner图","isShow":1,"jumpURL":""},{"id":5,"name":"精品大图5","picurl":"uploads/AdvImgs/2019-03-07/20190307172810273.png","desc":"精品大图5","city":"","area":"","places":"商城banner图","isShow":1,"jumpURL":"0"}]
     * other :
     */

    private int code;
    private String msg;
    private String other;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
         * name : 精品banner图
         * picurl : uploads/AdvImgs/2019-03-07/20190307172810275.png
         * desc : 精品大图
         * city :
         * area :
         * places : 商城banner图
         * isShow : 1
         * jumpURL :
         */

        private int id;
        private String name;
        private String picurl;
        private String desc;
        private String city;
        private String area;
        private String places;
        private int isShow;
        private String jumpURL;

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

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPlaces() {
            return places;
        }

        public void setPlaces(String places) {
            this.places = places;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getJumpURL() {
            return jumpURL;
        }

        public void setJumpURL(String jumpURL) {
            this.jumpURL = jumpURL;
        }
    }
}
