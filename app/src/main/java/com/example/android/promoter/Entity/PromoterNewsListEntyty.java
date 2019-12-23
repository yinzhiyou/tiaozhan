package com.example.android.promoter.Entity;

import java.util.List;

public class PromoterNewsListEntyty {

    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"uuid":"d10cfff2-83e4-647f-7819-411496931733","content":"您好，您推广的\u201c山西省体育健身馆\u201d有参与人员撤回了投诉，请及时查看！","isred":1,"intime":"2019-11-25 11:57:27"},{"uuid":"209cd934-8c2d-9297-369b-d351415ca6d2","content":"您好，您推广的\u201c天天羽毛球馆\u201d有参与人员发起新的投诉，请及时处理！","isred":0,"intime":"2019-11-25 09:35:14"}]
     * other :
     */

    private int code;
    private String msg;
    private String other;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uuid : d10cfff2-83e4-647f-7819-411496931733
         * content : 您好，您推广的“山西省体育健身馆”有参与人员撤回了投诉，请及时查看！
         * isred : 1
         * intime : 2019-11-25 11:57:27
         */

        private String uuid;
        private String content;
        private int isred;
        private String intime;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsred() {
            return isred;
        }

        public void setIsred(int isred) {
            this.isred = isred;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }
    }
}
