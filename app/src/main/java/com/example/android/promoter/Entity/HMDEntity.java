package com.example.android.promoter.Entity;

import java.util.List;

public class HMDEntity  {


    /**
     * code : 2000
     * msg : 获取黑名单列表成功
     * data : {"nowPage":1,"total":1,"Lst":[{"ShieldPlayerUUID":"49ea9c11-8ac1-ce1c-a7f9-37a0e4ce5bb4","nickname":"玛法达","headerImg":""}]}
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
         * nowPage : 1
         * total : 1
         * Lst : [{"ShieldPlayerUUID":"49ea9c11-8ac1-ce1c-a7f9-37a0e4ce5bb4","nickname":"玛法达","headerImg":""}]
         */

        private int nowPage;
        private int total;
        private List<LstBean> Lst;

        public int getNowPage() {
            return nowPage;
        }

        public void setNowPage(int nowPage) {
            this.nowPage = nowPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<LstBean> getLst() {
            return Lst;
        }

        public void setLst(List<LstBean> Lst) {
            this.Lst = Lst;
        }

        public static class LstBean {
            /**
             * ShieldPlayerUUID : 49ea9c11-8ac1-ce1c-a7f9-37a0e4ce5bb4
             * nickname : 玛法达
             * headerImg :
             */

            private String ShieldPlayerUUID;
            private String nickname;
            private String headerImg;

            public String getShieldPlayerUUID() {
                return ShieldPlayerUUID;
            }

            public void setShieldPlayerUUID(String ShieldPlayerUUID) {
                this.ShieldPlayerUUID = ShieldPlayerUUID;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeaderImg() {
                return headerImg;
            }

            public void setHeaderImg(String headerImg) {
                this.headerImg = headerImg;
            }
        }
    }
}
