package com.example.android.tiaozhan.Entity;

import java.util.List;

public class PromoterHelpCenterEntity {

    /**
     * code : 2000
     * msg : 查询成功
     * data : [{"title":"如何查看自己的推广提成","content":"进入到推广员端首页，点击我的钱包，点击右上角的明细，即可看到自己所有的推广提成"},{"title":"如何查看自己已经推广的运营场馆","content":"进入到推广员端首页，点击已推广运营的场馆，即可看到自己所推广的所有的运营场馆"},{"title":"如何提现","content":"进入到推广员首页，点击我的钱包，点击提现，选择号银行卡，即可提现。注：提现金额大于10"}]
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
         * title : 如何查看自己的推广提成
         * content : 进入到推广员端首页，点击我的钱包，点击右上角的明细，即可看到自己所有的推广提成
         */

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
