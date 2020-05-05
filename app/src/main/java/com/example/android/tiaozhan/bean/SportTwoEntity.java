package com.example.android.tiaozhan.bean;

import java.util.List;

public class SportTwoEntity {
    private List<ParentBean> parentBean;

    public List<ParentBean> getParentBean() {
        return parentBean;
    }

    public void setParentBean(List<ParentBean> parentBean) {
        this.parentBean = parentBean;
    }

    public static class ParentBean {
        public ParentBean(String parentName, int parentId, List<ChildBean> data) {
            this.parentName = parentName;
            this.parentId = parentId;
            this.data = data;
        }

        public ParentBean() {
        }

        private String parentName;
        private int parentId;

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        private List<ChildBean> data;


        public List<ChildBean> getData() {
            return data;
        }

        public void setData(List<ChildBean> data) {
            this.data = data;
        }

        public static class ChildBean {
            public ChildBean() {
            }

            public ChildBean(int id, int sportid, String name, int playerNumber, int prepareMinite) {
                this.id = id;
                this.sportid = sportid;
                this.name = name;
                this.playerNumber = playerNumber;
                PrepareMinite = prepareMinite;
            }

            /**
             * id : 6
             * sportid : 2
             * name : 单打
             * playerNumber : 2
             * PrepareMinite : 30
             */
            private int id;
            private int sportid;
            private String name;
            private int playerNumber;
            private int PrepareMinite;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSportid() {
                return sportid;
            }

            public void setSportid(int sportid) {
                this.sportid = sportid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPlayerNumber() {
                return playerNumber;
            }

            public void setPlayerNumber(int playerNumber) {
                this.playerNumber = playerNumber;
            }

            public int getPrepareMinite() {
                return PrepareMinite;
            }

            public void setPrepareMinite(int PrepareMinite) {
                this.PrepareMinite = PrepareMinite;
            }
        }
    }
}
