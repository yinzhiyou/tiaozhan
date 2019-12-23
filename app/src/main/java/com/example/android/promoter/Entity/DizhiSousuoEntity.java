package com.example.android.promoter.Entity;

import java.util.List;

public class DizhiSousuoEntity {


    /**
     * status : 0
     * message : ok
     * total : 22
     * results : [{"name":"国贸","location":{"lat":39.914435,"lng":116.467523},"address":"地铁10号线;地铁1号线","province":"北京市","city":"北京市","area":"朝阳区","detail":1,"uid":"c688801de17d0472a21de9b4"},{"name":"国贸中心","location":{"lat":39.916676,"lng":116.465988},"address":"北京朝阳区建国门外大街1号3","province":"北京市","city":"北京市","area":"朝阳区","street_id":"d82d66975ed94ea6d418b798","telephone":"(010)65052288","detail":1,"uid":"07f3c8b579707d7a6240a919"}]
     */

    private int status;
    private String message;
    private int total;
    private List<ResultsBean> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * name : 国贸
         * location : {"lat":39.914435,"lng":116.467523}
         * address : 地铁10号线;地铁1号线
         * province : 北京市
         * city : 北京市
         * area : 朝阳区
         * detail : 1
         * uid : c688801de17d0472a21de9b4
         * street_id : d82d66975ed94ea6d418b798
         * telephone : (010)65052288
         */

        private String name;
        private LocationBean location;
        private String address;
        private String province;
        private String city;
        private String area;
        private int detail;
        private String uid;
        private String street_id;
        private String telephone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
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

        public int getDetail() {
            return detail;
        }

        public void setDetail(int detail) {
            this.detail = detail;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getStreet_id() {
            return street_id;
        }

        public void setStreet_id(String street_id) {
            this.street_id = street_id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public static class LocationBean {
            /**
             * lat : 39.914435
             * lng : 116.467523
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }
}
