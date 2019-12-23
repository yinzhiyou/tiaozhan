package com.example.android.promoter.Entity;

import java.util.List;

public class MyWentiEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : [{"id":1,"Question":"您父亲的名字"},{"id":4,"Question":"您印象最深的人的名字"},{"id":7,"Question":"您的出生日期"}]
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * Question : 您父亲的名字
         */

        private int id;
        private String Question;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return Question;
        }

        public void setQuestion(String Question) {
            this.Question = Question;
        }
    }
}
