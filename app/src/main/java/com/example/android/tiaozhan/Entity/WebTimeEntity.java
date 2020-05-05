package com.example.android.tiaozhan.Entity;

public class WebTimeEntity {

    /**
     * placeNun : 3,3,
     * placeTime : 14:30-15:00
     * placeDate : 2019-11-29
     * placeMoney : 80
     * placeTimeLen : 1小时
     */

    private String placeNun;
    private String placeTime;
    private String placeDate;
    private Double placeMoney;
    private String placeTimeLen;

    public String getPlaceNun() {
        return placeNun;
    }

    public void setPlaceNun(String placeNun) {
        this.placeNun = placeNun;
    }

    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }

    public String getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(String placeDate) {
        this.placeDate = placeDate;
    }

    public Double getPlaceMoney() {
        return placeMoney;
    }

    public void setPlaceMoney(Double placeMoney) {
        this.placeMoney = placeMoney;
    }

    public String getPlaceTimeLen() {
        return placeTimeLen;
    }

    public void setPlaceTimeLen(String placeTimeLen) {
        this.placeTimeLen = placeTimeLen;
    }
}
