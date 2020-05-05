package com.example.android.tiaozhan.Entity;

public class HDjianceEntity  {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"TotalCount":13,"matching":0,"waiting":0,"activiting":0,"confirming":1,"assessing":0,"finished":10}
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
         * TotalCount : 13
         * matching : 0
         * waiting : 0
         * activiting : 0
         * confirming : 1
         * assessing : 0
         * finished : 10
         */

        private int TotalCount;
        private int matching;
        private int waiting;
        private int activiting;
        private int confirming;
        private int assessing;
        private int finished;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getMatching() {
            return matching;
        }

        public void setMatching(int matching) {
            this.matching = matching;
        }

        public int getWaiting() {
            return waiting;
        }

        public void setWaiting(int waiting) {
            this.waiting = waiting;
        }

        public int getActiviting() {
            return activiting;
        }

        public void setActiviting(int activiting) {
            this.activiting = activiting;
        }

        public int getConfirming() {
            return confirming;
        }

        public void setConfirming(int confirming) {
            this.confirming = confirming;
        }

        public int getAssessing() {
            return assessing;
        }

        public void setAssessing(int assessing) {
            this.assessing = assessing;
        }

        public int getFinished() {
            return finished;
        }

        public void setFinished(int finished) {
            this.finished = finished;
        }
    }
}
