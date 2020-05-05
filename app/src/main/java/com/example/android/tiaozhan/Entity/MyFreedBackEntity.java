package com.example.android.tiaozhan.Entity;

import java.util.List;

public class MyFreedBackEntity {

    /**
     * code : 2000
     * msg : 反馈成功
     * data : []
     * other :
     */

    private int code;
    private String msg;
    private String other;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
