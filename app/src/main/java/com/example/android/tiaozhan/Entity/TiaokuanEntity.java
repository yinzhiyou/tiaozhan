package com.example.android.tiaozhan.Entity;

public class TiaokuanEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"id":1,"Comment":"这这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款这里是\u201c挑战约球\u201d服务条款里是\u201c挑战约球\u201d服务条款","Version":1,"CreateDate":"2018-09-06 17:24:08","EditDate":"2018-09-06 17:24:12","Author":"北京甲乙电子商务有限公司","active":1}
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
         * id : 1
         * Comment : 这这里是“挑战约球”服务条款这里是“挑战约球”服务条款这里是“挑战约球”服务条款这里是“挑战约球”服务条款这里是“挑战约球”服务条款这里是“挑战约球”服务条款这里是“挑战约球”服务条款这里是“挑战约球”服务条款里是“挑战约球”服务条款
         * Version : 1
         * CreateDate : 2018-09-06 17:24:08
         * EditDate : 2018-09-06 17:24:12
         * Author : 北京甲乙电子商务有限公司
         * active : 1
         */

        private int id;
        private String Comment;
        private int Version;
        private String CreateDate;
        private String EditDate;
        private String Author;
        private int active;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String Comment) {
            this.Comment = Comment;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getEditDate() {
            return EditDate;
        }

        public void setEditDate(String EditDate) {
            this.EditDate = EditDate;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String Author) {
            this.Author = Author;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }
    }
}
