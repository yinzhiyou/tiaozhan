package com.example.android.tiaozhan.bean;

import com.example.android.tiaozhan.Entity.DizhiSousuoEntity;

public class addressBean {
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

    private String address;

    private String city;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




}
