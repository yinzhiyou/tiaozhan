package com.example.android.tiaozhan.Entity;

import java.io.Serializable;

public class FQTZEntity  implements Serializable {



        /**
         * isSeat : 0
         * isSignIn : 1
         * isPublisher : 1
         * uuid : 67004120-aa15-3ebb-d159-bda39816fe8a
         * nickname : haoxiafa
         * imgURL : uploads/HeaderImgs/2019-01-03/20190103141603697.png
         * heightLevelName : 羽毛球
         * heightLevel : Lv1
         */


        private String uuid;

        private String imgURL;
        private String heightLevelName;
        private String heightLevel;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getHeightLevelName() {
        return heightLevelName;
    }

    public void setHeightLevelName(String heightLevelName) {
        this.heightLevelName = heightLevelName;
    }

    public String getHeightLevel() {
        return heightLevel;
    }

    public void setHeightLevel(String heightLevel) {
        this.heightLevel = heightLevel;
    }
}
