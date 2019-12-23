package com.example.android.promoter.Entity;

import java.util.List;

public class AQWentiEnditu {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"oneQuestion":[{"id":1,"Question":"您父亲的名字"},{"id":2,"Question":"您母亲的名字"},{"id":3,"Question":"您第一所学校的名字"}],"twoQuestion":[{"id":4,"Question":"您印象最深的人的名字"},{"id":5,"Question":"您最敬爱的老师的名字"},{"id":6,"Question":"您爱人的名字"}],"threeQuestion":[{"id":7,"Question":"您的出生日期"},{"id":8,"Question":"您第一辆车是什么车"},{"id":9,"Question":"您喜爱的明星的名字"}]}
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
        private List<OneQuestionBean> oneQuestion;
        private List<TwoQuestionBean> twoQuestion;
        private List<ThreeQuestionBean> threeQuestion;

        public List<OneQuestionBean> getOneQuestion() {
            return oneQuestion;
        }

        public void setOneQuestion(List<OneQuestionBean> oneQuestion) {
            this.oneQuestion = oneQuestion;
        }

        public List<TwoQuestionBean> getTwoQuestion() {
            return twoQuestion;
        }

        public void setTwoQuestion(List<TwoQuestionBean> twoQuestion) {
            this.twoQuestion = twoQuestion;
        }

        public List<ThreeQuestionBean> getThreeQuestion() {
            return threeQuestion;
        }

        public void setThreeQuestion(List<ThreeQuestionBean> threeQuestion) {
            this.threeQuestion = threeQuestion;
        }

        public static class OneQuestionBean {
            /**
             * id : 1
             * Question : 您父亲的名字
             */

            private int id;
            private String Question;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuestion() {
                return Question;
            }

            public void setQuestion(String Question) {
                this.Question = Question;
            }
        }

        public static class TwoQuestionBean {
            /**
             * id : 4
             * Question : 您印象最深的人的名字
             */

            private int id;
            private String Question;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuestion() {
                return Question;
            }

            public void setQuestion(String Question) {
                this.Question = Question;
            }
        }

        public static class ThreeQuestionBean {
            /**
             * id : 7
             * Question : 您的出生日期
             */

            private int id;
            private String Question;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuestion() {
                return Question;
            }

            public void setQuestion(String Question) {
                this.Question = Question;
            }
        }
    }
}
