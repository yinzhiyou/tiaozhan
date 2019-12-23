package com.example.android.promoter.Entity;

import com.google.gson.annotations.SerializedName;

public class WeixinZhifuEntity {


    /**
     * code : 2000
     * msg : 获取预支付信息成功
     * data : {"uuid":"86120b45-acb5-9ce9-1beb-41b90cfcf84c","sign_data":{"appid":"wx60e2e2539670b0e5","partnerid":"1504068921","prepayid":"WX0212080490272650FEC54F131225324964","package":"Sign=WXPay","noncestr":"GIQSMMU0ZR7CAD2NFLB9P85AXF6BZJGE","timestamp":1543723684,"sign":"BDA34110803585DD83871373FDE83B26"},"secret_data":{"out_trade_no":"86120b45acb59ce91beb41b90cfcf84c","prepayid":"WX0212080490272650FEC54F131225324964"}}
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
         * uuid : 86120b45-acb5-9ce9-1beb-41b90cfcf84c
         * sign_data : {"appid":"wx60e2e2539670b0e5","partnerid":"1504068921","prepayid":"WX0212080490272650FEC54F131225324964","package":"Sign=WXPay","noncestr":"GIQSMMU0ZR7CAD2NFLB9P85AXF6BZJGE","timestamp":1543723684,"sign":"BDA34110803585DD83871373FDE83B26"}
         * secret_data : {"out_trade_no":"86120b45acb59ce91beb41b90cfcf84c","prepayid":"WX0212080490272650FEC54F131225324964"}
         */

        private String uuid;
        private SignDataBean sign_data;
        private SecretDataBean secret_data;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public SignDataBean getSign_data() {
            return sign_data;
        }

        public void setSign_data(SignDataBean sign_data) {
            this.sign_data = sign_data;
        }

        public SecretDataBean getSecret_data() {
            return secret_data;
        }

        public void setSecret_data(SecretDataBean secret_data) {
            this.secret_data = secret_data;
        }

        public static class SignDataBean {
            /**
             * appid : wx60e2e2539670b0e5
             * partnerid : 1504068921
             * prepayid : WX0212080490272650FEC54F131225324964
             * package : Sign=WXPay
             * noncestr : GIQSMMU0ZR7CAD2NFLB9P85AXF6BZJGE
             * timestamp : 1543723684
             * sign : BDA34110803585DD83871373FDE83B26
             */

            private String appid;
            private String partnerid;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String noncestr;
            private int timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }

        public static class SecretDataBean {
            /**
             * out_trade_no : 86120b45acb59ce91beb41b90cfcf84c
             * prepayid : WX0212080490272650FEC54F131225324964
             */

            private String out_trade_no;
            private String prepayid;

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }
        }
    }
}
