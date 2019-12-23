package com.example.android.promoter.Entity;

import java.util.List;

public class CGTimeEntity {


    /**
     * code : 2000
     * msg : 获取场馆时间和场地数信息成功
     * data : [{"opendayname":"周一至周日","timeid":1,"sportname":"台球","costperhour":"0.00","notUsedCount":99999,"maxtablecount":99999,"appointmenttime":60,"stratDateTime":"00:00","endDateTime":"00:30"},{"opendayname":"周一至周日","timeid":2,"sportname":"台球","costperhour":"0.00","notUsedCount":99999,"maxtablecount":99999,"appointmenttime":60,"stratDateTime":"00:30","endDateTime":"01:00"}]
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
         * opendayname : 周一至周日
         * timeid : 1
         * sportname : 台球
         * costperhour : 0.00
         * notUsedCount : 99999
         * maxtablecount : 99999
         * appointmenttime : 60
         * stratDateTime : 00:00
         * endDateTime : 00:30
         */

        private String opendayname;
        private int timeid;
        private String sportname;
        private String costperhour;
        private int notUsedCount;
        private int maxtablecount;
        private int appointmenttime;
        private String startDateTime;
        private String endDateTime;

        public String getOpendayname() {
            return opendayname;
        }

        public void setOpendayname(String opendayname) {
            this.opendayname = opendayname;
        }

        public int getTimeid() {
            return timeid;
        }

        public void setTimeid(int timeid) {
            this.timeid = timeid;
        }

        public String getSportname() {
            return sportname;
        }

        public void setSportname(String sportname) {
            this.sportname = sportname;
        }

        public String getCostperhour() {
            return costperhour;
        }

        public void setCostperhour(String costperhour) {
            this.costperhour = costperhour;
        }

        public int getNotUsedCount() {
            return notUsedCount;
        }

        public void setNotUsedCount(int notUsedCount) {
            this.notUsedCount = notUsedCount;
        }

        public int getMaxtablecount() {
            return maxtablecount;
        }

        public void setMaxtablecount(int maxtablecount) {
            this.maxtablecount = maxtablecount;
        }

        public int getAppointmenttime() {
            return appointmenttime;
        }

        public void setAppointmenttime(int appointmenttime) {
            this.appointmenttime = appointmenttime;
        }

        public String getStratDateTime() {
            return startDateTime;
        }

        public void setStratDateTime(String stratDateTime) {
            this.startDateTime = stratDateTime;
        }

        public String getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(String endDateTime) {
            this.endDateTime = endDateTime;
        }
    }
}
