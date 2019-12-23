package com.example.android.promoter.Entity;

public class JCXIEntity  {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"NotReadCount":49}
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
         * NotReadCount : 49
         */

        private int NotReadCount;

        public int getNotReadCount() {
            return NotReadCount;
        }

        public void setNotReadCount(int NotReadCount) {
            this.NotReadCount = NotReadCount;
        }
    }
}
