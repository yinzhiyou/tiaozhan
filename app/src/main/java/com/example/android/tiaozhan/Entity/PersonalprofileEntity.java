package com.example.android.tiaozhan.Entity;

public class PersonalprofileEntity {


    /**
     * code : 2000
     * msg : 上传成功
     * data : uploads/Personalprofile/2020-04-21/20200421163737592.png
     * other :
     */

    private int code;
    private String msg;
    private String data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
