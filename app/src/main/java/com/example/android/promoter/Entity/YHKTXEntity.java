package com.example.android.promoter.Entity;

public class YHKTXEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"Amount":"1.00","putType":"bankCard","bankCardNum":"9831","status":1,"RequestDate":"2019-05-24 16:28:58","bankName":"招商银行","notice":"资金将在2个工作日之内到账"}
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
         * Amount : 1.00
         * putType : bankCard
         * bankCardNum : 9831
         * status : 1
         * RequestDate : 2019-05-24 16:28:58
         * bankName : 招商银行
         * notice : 资金将在2个工作日之内到账
         */

        private String Amount;
        private String putType;
        private String bankCardNum;
        private int status;
        private String RequestDate;
        private String bankName;
        private String notice;

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }

        public String getPutType() {
            return putType;
        }

        public void setPutType(String putType) {
            this.putType = putType;
        }

        public String getBankCardNum() {
            return bankCardNum;
        }

        public void setBankCardNum(String bankCardNum) {
            this.bankCardNum = bankCardNum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRequestDate() {
            return RequestDate;
        }

        public void setRequestDate(String RequestDate) {
            this.RequestDate = RequestDate;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
    }
}
