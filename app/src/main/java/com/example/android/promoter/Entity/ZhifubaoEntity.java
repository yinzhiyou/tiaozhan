package com.example.android.promoter.Entity;

/**
 * Created by 72909 on 2018/5/3.
 */

public class ZhifubaoEntity {


    /**
     * code : 2000
     * msg : 获取预支付信息成功
     * data : {"uuid":"60f07a36-eb7b-2707-b7d8-2d93254bbd30","sign_data":"app_id=2018050960086163&method=alipay.trade.app.pay&format=json&sign_type=RSA2&timestamp=2018-12-01+18%3A27%3A11&alipay_sdk=alipay-sdk-php-20180705&charset=UTF-8&version=1.0&notify_url=http%3A%2F%2Fapp.tiaozhanmeiyitian.com%2Fapi%2FalipayNotify&biz_content=%7B%22body%22%3A%22%E6%B4%BB%E5%8A%A8%E9%87%91%E9%A2%9D%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A+%22%E6%B4%BB%E5%8A%A8%E9%87%91%E9%A2%9D%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A+%2260f07a36eb7b2707b7d82d93254bbd30%22%2C%22timeout_express%22%3A+%221m%22%2C%22total_amount%22%3A+%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&sign=GqX0%2F%2BF9LVTPbcjSOPZnRoBpP0EmWjnsqHIzHj3Lm6jckRQ3br5dQpksztd%2FfsZSTUeZdYE5gBAvq0CmY%2B5V%2F4tOjVFPagbeft1JB7GxlczjUmvEUMrx9S8NdxPJjP1XlecSRMAy61TdDTmHWqDiKo%2BIHIa7qg5zjW1kUFGrgYsIajXM4VA1lL3s0jPvaZedIEtBhpLfU6X12U6Re26THXUTIHFkzpQuTT2nnYpOaPsEIewQ%2BZsagAhXSogMQ%2FO1P982k1bD6CS%2FGrBJPXQRNqm2K4wnxsAev6hTLBP5eCnMjt55KN2OB3mVFxizGxkrObyHP4FX10aAkkFaR78Myw%3D%3D"}
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
         * uuid : 60f07a36-eb7b-2707-b7d8-2d93254bbd30
         * sign_data : app_id=2018050960086163&method=alipay.trade.app.pay&format=json&sign_type=RSA2&timestamp=2018-12-01+18%3A27%3A11&alipay_sdk=alipay-sdk-php-20180705&charset=UTF-8&version=1.0&notify_url=http%3A%2F%2Fapp.tiaozhanmeiyitian.com%2Fapi%2FalipayNotify&biz_content=%7B%22body%22%3A%22%E6%B4%BB%E5%8A%A8%E9%87%91%E9%A2%9D%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A+%22%E6%B4%BB%E5%8A%A8%E9%87%91%E9%A2%9D%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A+%2260f07a36eb7b2707b7d82d93254bbd30%22%2C%22timeout_express%22%3A+%221m%22%2C%22total_amount%22%3A+%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&sign=GqX0%2F%2BF9LVTPbcjSOPZnRoBpP0EmWjnsqHIzHj3Lm6jckRQ3br5dQpksztd%2FfsZSTUeZdYE5gBAvq0CmY%2B5V%2F4tOjVFPagbeft1JB7GxlczjUmvEUMrx9S8NdxPJjP1XlecSRMAy61TdDTmHWqDiKo%2BIHIa7qg5zjW1kUFGrgYsIajXM4VA1lL3s0jPvaZedIEtBhpLfU6X12U6Re26THXUTIHFkzpQuTT2nnYpOaPsEIewQ%2BZsagAhXSogMQ%2FO1P982k1bD6CS%2FGrBJPXQRNqm2K4wnxsAev6hTLBP5eCnMjt55KN2OB3mVFxizGxkrObyHP4FX10aAkkFaR78Myw%3D%3D
         */

        private String uuid;
        private String sign_data;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getSign_data() {
            return sign_data;
        }

        public void setSign_data(String sign_data) {
            this.sign_data = sign_data;
        }
    }
}
