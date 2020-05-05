package com.example.android.tiaozhan.Entity;

public class MyPromoterEntity {


    /**
     * code : 2000
     * msg : 查询成功
     * data : {"PromotId":666666,"data1":"10194.06","data2":100,"data3":1251,"data4":0,"data5":0,"data6":7,"data7":"10194.06"}
     * other :
     */

    private int code;
    private String msg;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * PromotId : 666666
         * data1 : 10194.06
         * data2 : 100
         * data3 : 1251
         * data4 : 0
         * data5 : 0
         * data6 : 7
         * data7 : 10194.06
         * data8: 3
         */

        private int PromotId;
        private String data1;
        private int data2;
        private int data3;
        private int data4;
        private int data5;
        private int data6;
        private String data7;
        private int data8;

        public int getData9() {
            return data9;
        }

        public void setData9(int data9) {
            this.data9 = data9;
        }

        private int data9;
        public int getData8() {
            return data8;
        }

        public void setData8(int data8) {
            this.data8 = data8;
        }

        public int getPromotId() {
            return PromotId;
        }

        public void setPromotId(int PromotId) {
            this.PromotId = PromotId;
        }

        public String getData1() {
            return data1;
        }

        public void setData1(String data1) {
            this.data1 = data1;
        }

        public int getData2() {
            return data2;
        }

        public void setData2(int data2) {
            this.data2 = data2;
        }

        public int getData3() {
            return data3;
        }

        public void setData3(int data3) {
            this.data3 = data3;
        }

        public int getData4() {
            return data4;
        }

        public void setData4(int data4) {
            this.data4 = data4;
        }

        public int getData5() {
            return data5;
        }

        public void setData5(int data5) {
            this.data5 = data5;
        }

        public int getData6() {
            return data6;
        }

        public void setData6(int data6) {
            this.data6 = data6;
        }

        public String getData7() {
            return data7;
        }

        public void setData7(String data7) {
            this.data7 = data7;
        }
    }
}
