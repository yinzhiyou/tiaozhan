package com.example.android.tiaozhan.Home.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDao  {
    @Id
    private Long id;
    private String city;
    private String name;
    private String address;
    private double lat;
    private double lng;
    @Generated(hash = 227999365)
    public GreenDao(Long id, String city, String name, String address, double lat,
            double lng) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public double getLat() {
        return this.lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return this.lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
   
    
}
