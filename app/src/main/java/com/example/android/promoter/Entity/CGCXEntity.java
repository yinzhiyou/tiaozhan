package com.example.android.promoter.Entity;

import java.util.List;

public class CGCXEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"nowPage":1,"total":31,"Lst":[{"uid":"060f109fd102679646572680","name":"回龙镇乒乓球协会","province":"广东省","city":"韶关市","area":"新丰县","address":"韶关市新丰县347县道北150米"},{"uid":"0dc3d82ddf0c4c7575e5b61d","name":"上海市回民中学-羽毛球馆","province":"上海市","city":"上海市","area":"普陀区","address":"上海市普陀区沪太路1000号(近宜川路)"},{"uid":"0eb0a9f37e5be395f6eafc96","name":"隆回县羽毛球协会","province":"湖南省","city":"邵阳市","area":"隆回县","address":"朝阳路244号"},{"uid":"1109dc6dd7ddbe19bda2400e","name":"鹿回头国宾馆-乒乓球","province":"海南省","city":"三亚市","area":"吉阳区","address":"鹿岭路6号"},{"uid":"24f36246b7c7d9476f4760ac","name":"兰博文羽毛球培训(回民中学点)","province":"上海市","city":"上海市","area":"普陀区","address":"上海市普陀区沪太路1000号"},{"uid":"290a015bc34fa9129e95de64","name":"双星足球鞋回力篮球鞋","province":"江西省","city":"宜春市","area":"宜丰县","address":"盐步北路19号"},{"uid":"2fd280f092e547628d7727a3","name":"悠悠台球俱乐部回龙观店","province":"北京市","city":"北京市","area":"昌平区","address":"昌平区回龙观龙泽苑综合楼北配楼悠悠客栈内"},{"uid":"3a0fbba83c3bcf409ee3212d","name":"昌吉回族自治州篮球协会(金陵社区卫生服务站东北)","province":"新疆维吾尔自治区","city":"昌吉回族自治州","area":"昌吉市","address":"文化东路5号附近"},{"uid":"3b74cdfbeffe7184b7f42f34","name":"回味至尊台球俱乐部","province":"山东省","city":"聊城市","area":"莘县","address":"大安街鸿福楼二楼"},{"uid":"47170f2c8df83c41f1193705","name":"成都医学院天回校区篮球场(思学路)","province":"四川省","city":"成都市","area":"金牛区","address":"勤学路与博学路交叉口西北150米"}]}
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
         * total : 31
         * Lst : [{"uid":"060f109fd102679646572680","name":"回龙镇乒乓球协会","province":"广东省","city":"韶关市","area":"新丰县","address":"韶关市新丰县347县道北150米"},{"uid":"0dc3d82ddf0c4c7575e5b61d","name":"上海市回民中学-羽毛球馆","province":"上海市","city":"上海市","area":"普陀区","address":"上海市普陀区沪太路1000号(近宜川路)"},{"uid":"0eb0a9f37e5be395f6eafc96","name":"隆回县羽毛球协会","province":"湖南省","city":"邵阳市","area":"隆回县","address":"朝阳路244号"},{"uid":"1109dc6dd7ddbe19bda2400e","name":"鹿回头国宾馆-乒乓球","province":"海南省","city":"三亚市","area":"吉阳区","address":"鹿岭路6号"},{"uid":"24f36246b7c7d9476f4760ac","name":"兰博文羽毛球培训(回民中学点)","province":"上海市","city":"上海市","area":"普陀区","address":"上海市普陀区沪太路1000号"},{"uid":"290a015bc34fa9129e95de64","name":"双星足球鞋回力篮球鞋","province":"江西省","city":"宜春市","area":"宜丰县","address":"盐步北路19号"},{"uid":"2fd280f092e547628d7727a3","name":"悠悠台球俱乐部回龙观店","province":"北京市","city":"北京市","area":"昌平区","address":"昌平区回龙观龙泽苑综合楼北配楼悠悠客栈内"},{"uid":"3a0fbba83c3bcf409ee3212d","name":"昌吉回族自治州篮球协会(金陵社区卫生服务站东北)","province":"新疆维吾尔自治区","city":"昌吉回族自治州","area":"昌吉市","address":"文化东路5号附近"},{"uid":"3b74cdfbeffe7184b7f42f34","name":"回味至尊台球俱乐部","province":"山东省","city":"聊城市","area":"莘县","address":"大安街鸿福楼二楼"},{"uid":"47170f2c8df83c41f1193705","name":"成都医学院天回校区篮球场(思学路)","province":"四川省","city":"成都市","area":"金牛区","address":"勤学路与博学路交叉口西北150米"}]
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
             * uid : 060f109fd102679646572680
             * name : 回龙镇乒乓球协会
             * province : 广东省
             * city : 韶关市
             * area : 新丰县
             * address : 韶关市新丰县347县道北150米
             */

            private String uid;
            private String name;
            private String province;
            private String city;
            private String area;
            private String address;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
