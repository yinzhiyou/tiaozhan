package com.example.android.promoter.Entity;

import java.util.List;

public class HDXQEntity {


    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"uuid":"07a30e9c-171d-c128-dbae-b260350bf6d6","orderId":"011901031463","SportMode":2,"SportId":1,"SportType":5,"StartTime":"2019-01-03 16:30:00","PlayTime":"0.50","siteUid":"00052e666d12f06cafee7ad7","TeamMateSex":2,"OpponentsSex":2,"TeamMateLevelMin":1,"TeamMateLevelMax":10,"OpponentsLevelMin":1,"OpponentsLevelMax":10,"SiteMoney":0,"PaySiteMoneyType":1,"OtherPlayerNumber":"","MoneyPerhour":0,"Tips":"0.33","Comments":"","SuspendReason":"","PublicStatus":6,"GroupChartUUID":"978177d5-7566-0730-f4c0-8a2de5194886","CreateTime":"2019-01-03 14:19:10","FinishedTime":"2019-01-03 17:00:00","cancelTime":"","JoinEndTime":"2019-01-03 16:10:00","finalresult":4,"paied":1,"sportName":"羽毛球","sportTypeName":"双打","needNumber":4,"startWeek":"星期四","startDays":"2019-01-03","startTimes":"16:30:00","endWeek":"星期四","endDays":"2019-01-03","endTimes":"17:00:00","siteName":"2北京甲乙电子商务(华远3层店)","siteAddress":"国贸","siteLat":"39.8771270","siteLng":"116.7011950","joinedCount":4,"lackNumber":0,"publishPlayerName":"haoxiafa","publishPlayerImg":"uploads/HeaderImgs/2019-01-03/20190103141603697.png","heightLevelName":"羽毛球","heightLevel":"Lv1","isCooper":1,"groupId":"70357300084737","teamA":[{"isSeat":0,"isSignIn":1,"isPublisher":1,"uuid":"67004120-aa15-3ebb-d159-bda39816fe8a","nickname":"haoxiafa","imgURL":"uploads/HeaderImgs/2019-01-03/20190103141603697.png","heightLevelName":"羽毛球","heightLevel":"Lv1"},{"isSeat":0,"isSignIn":1,"isPublisher":0,"uuid":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","nickname":"你怕不怕","imgURL":"uploads/HeaderImgs/2019-01-03/20190103142521418.png","heightLevelName":"羽毛球","heightLevel":"Lv1"}],"teamB":[{"isSeat":0,"isSignIn":0,"isPublisher":0,"uuid":"e9ec5878-4c05-df3b-40d3-04fee16e767c","nickname":"tz_25172019Jan03","imgURL":"uploads/HeaderImgs/2019-01-03/20190103141632806.png","heightLevelName":"羽毛球","heightLevel":"Lv1"},{"isSeat":0,"isSignIn":1,"isPublisher":0,"uuid":"24c74571-9659-8bf9-b0c5-78b2e35acae7","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png","heightLevelName":"羽毛球","heightLevel":"Lv1"}],"AwinBcount":2,"AwinBuserInfo":[{"nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png"},{"nickname":"你怕不怕","imgURL":"uploads/HeaderImgs/2019-01-03/20190103142521418.png"}],"AloseBcount":2,"AloseBuserInfo":[{"nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png"},{"nickname":"你怕不怕","imgURL":"uploads/HeaderImgs/2019-01-03/20190103142521418.png"}],"AdrawBcount":0}
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
         * uuid : 07a30e9c-171d-c128-dbae-b260350bf6d6
         * orderId : 011901031463
         * SportMode : 2
         * SportId : 1
         * SportType : 5
         * StartTime : 2019-01-03 16:30:00
         * PlayTime : 0.50
         * siteUid : 00052e666d12f06cafee7ad7
         * TeamMateSex : 2
         * OpponentsSex : 2
         * TeamMateLevelMin : 1
         * TeamMateLevelMax : 10
         * OpponentsLevelMin : 1
         * OpponentsLevelMax : 10
         * SiteMoney : 0
         * PaySiteMoneyType : 1
         * OtherPlayerNumber :
         * MoneyPerhour : 0
         * Tips : 0.33
         * Comments :
         * SuspendReason :
         * PublicStatus : 6
         * GroupChartUUID : 978177d5-7566-0730-f4c0-8a2de5194886
         * CreateTime : 2019-01-03 14:19:10
         * FinishedTime : 2019-01-03 17:00:00
         * cancelTime :
         * JoinEndTime : 2019-01-03 16:10:00
         * finalresult : 4
         * paied : 1
         * sportName : 羽毛球
         * sportTypeName : 双打
         * needNumber : 4
         * startWeek : 星期四
         * startDays : 2019-01-03
         * startTimes : 16:30:00
         * endWeek : 星期四
         * endDays : 2019-01-03
         * endTimes : 17:00:00
         * siteName : 2北京甲乙电子商务(华远3层店)
         * siteAddress : 国贸
         * siteLat : 39.8771270
         * siteLng : 116.7011950
         * joinedCount : 4
         * lackNumber : 0
         * publishPlayerName : haoxiafa
         * publishPlayerImg : uploads/HeaderImgs/2019-01-03/20190103141603697.png
         * heightLevelName : 羽毛球
         * heightLevel : Lv1
         * isCooper : 1
         * groupId : 70357300084737
         * teamA : [{"isSeat":0,"isSignIn":1,"isPublisher":1,"uuid":"67004120-aa15-3ebb-d159-bda39816fe8a","nickname":"haoxiafa","imgURL":"uploads/HeaderImgs/2019-01-03/20190103141603697.png","heightLevelName":"羽毛球","heightLevel":"Lv1"},{"isSeat":0,"isSignIn":1,"isPublisher":0,"uuid":"e8d6d88c-5fbf-6a9b-fd9f-3b49c43bf9c1","nickname":"你怕不怕","imgURL":"uploads/HeaderImgs/2019-01-03/20190103142521418.png","heightLevelName":"羽毛球","heightLevel":"Lv1"}]
         * teamB : [{"isSeat":0,"isSignIn":0,"isPublisher":0,"uuid":"e9ec5878-4c05-df3b-40d3-04fee16e767c","nickname":"tz_25172019Jan03","imgURL":"uploads/HeaderImgs/2019-01-03/20190103141632806.png","heightLevelName":"羽毛球","heightLevel":"Lv1"},{"isSeat":0,"isSignIn":1,"isPublisher":0,"uuid":"24c74571-9659-8bf9-b0c5-78b2e35acae7","nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png","heightLevelName":"羽毛球","heightLevel":"Lv1"}]
         * AwinBcount : 2
         * AwinBuserInfo : [{"nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png"},{"nickname":"你怕不怕","imgURL":"uploads/HeaderImgs/2019-01-03/20190103142521418.png"}]
         * AloseBcount : 2
         * AloseBuserInfo : [{"nickname":"王二二","imgURL":"uploads/HeaderImgs/2019-01-03/20190103180853517.png"},{"nickname":"你怕不怕","imgURL":"uploads/HeaderImgs/2019-01-03/20190103142521418.png"}]
         * AdrawBcount : 0
         */
        private int isSignIn;
        private int isConfirmResult;
        private int isPublisher;
        private int isConfirmOver;
        private int isComment;

        private int isQuitInGame;

        private int isQuit;
        private String uuid;
        private String orderId;
        private int SportMode;
        private int SportId;
        private int SportType;
        private String StartTime;
        private String PlayTime;
        private String siteUid;
        private String TeamMateSex;
        private String OpponentsSex;
        private String TeamMateLevelMin;
        private String TeamMateLevelMax;
        private String OpponentsLevelMin;
        private String OpponentsLevelMax;
        private double SiteMoney;
        private int PaySiteMoneyType;
        private String OtherPlayerNumber;
        private double MoneyPerhour;
        private String Tips;
        private String Comments;
        private String SuspendReason;
        private int PublicStatus;
        private String GroupChartUUID;
        private String CreateTime;
        private String FinishedTime;
        private String cancelTime;
        private String JoinEndTime;
        private int finalresult;
        private int paied;
        private String sportName;
        private String sportTypeName;
        private int needNumber;
        private String startWeek;
        private String startDays;
        private String startTimes;
        private String endWeek;
        private String endDays;
        private String endTimes;
        private String siteName;
        private String siteAddress;
        private String siteLat;
        private String siteLng;
        private int joinedCount;
        private int lackNumber;
        private String publishPlayerName;
        private String publishPlayerImg;
        private String heightLevelName;
        private String heightLevel;
        private int isCooper;


        private String GetCommonCoins;
        private String GetCoins;
        private String getSiteMoney;
        private String getWalletMoney;
        private String getOutMoney;
        private String getTips;
        private String getMoneyPerhour;


        private String getexplain;
        private String Default;
        private String Statementofsitefee;
        private String ExplanationofRewardFee;
        private String GeneralGoldNotes;
        private String ProfessionalGoldNotes;


        private String getUsersComplainEnd;
        private int getUsersComplainIssystem;


        private String getUserscaddTime;
        private String getUsersnickname;
        private String getUserscomplaint;
        private String getUsersimgURL;

        private String getUserComplaint;

        private String Complaint;
        private String isHandle;

        private String getUsersrecording;
        private String groupId;

        private String group_name;
        private String resultReason;
        private int AwinBcount;
        private int AloseBcount;
        private int AdrawBcount;
        private String Unreserved;

        private String detailed;
        private int Comment;

        private String getRemarks;
        private int getwaiver;

        private String venuenumber;
        private String venueid;

        public String getVenuenumber() {
            return venuenumber;
        }

        public void setVenuenumber(String venuenumber) {
            this.venuenumber = venuenumber;
        }

        public String getVenueid() {
            return venueid;
        }

        public void setVenueid(String venueid) {
            this.venueid = venueid;
        }

        public String getGetRemarks() {
            return getRemarks;
        }

        public void setGetRemarks(String getRemarks) {
            this.getRemarks = getRemarks;
        }

        public int getGetwaiver() {
            return getwaiver;
        }

        public void setGetwaiver(int getwaiver) {
            this.getwaiver = getwaiver;
        }

        private String complaintistrue;
        private List<TeamABean> teamA;
        private List<TeamBBean> teamB;
        private List<AwinBuserInfoBean> AwinBuserInfo;
        private List<AloseBuserInfoBean> AloseBuserInfo;
        private List<AdrawBuserInfoBean> AdrawBuserInfo;

        private List<SignUserInfoBean> SignUserInfo;
        private List<NotSignUserInfoBean> notSignUserInfo;
        private List<GetwaiverInfoBean> getwaiverInfo;



        public List<GetwaiverInfoBean> getGetwaiverInfo() {
            return getwaiverInfo;
        }

        public void setGetwaiverInfo(List<GetwaiverInfoBean> getwaiverInfo) {
            this.getwaiverInfo = getwaiverInfo;
        }

        public String getComplaintistrue() {
            return complaintistrue;
        }

        public void setComplaintistrue(String complaintistrue) {
            this.complaintistrue = complaintistrue;
        }


        public int getComment() {
            return Comment;
        }

        public void setComment(int comment) {
            Comment = comment;
        }


        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getDetailed() {
            return detailed;
        }

        public void setDetailed(String detailed) {
            this.detailed = detailed;
        }


        public String getUnreserved() {
            return Unreserved;
        }

        public void setUnreserved(String unreserved) {
            Unreserved = unreserved;
        }


        public String getComplaint() {
            return Complaint;
        }

        public void setComplaint(String complaint) {
            Complaint = complaint;
        }

        public String getGetUsersrecording() {
            return getUsersrecording;
        }

        public void setGetUsersrecording(String getUsersrecording) {
            this.getUsersrecording = getUsersrecording;
        }

        public String getIsHandle() {
            return isHandle;
        }

        public void setIsHandle(String isHandle) {
            this.isHandle = isHandle;
        }

        public String getGetUserComplaint() {
            return getUserComplaint;
        }

        public void setGetUserComplaint(String getUserComplaint) {
            this.getUserComplaint = getUserComplaint;
        }


        public String getGetUserscaddTime() {
            return getUserscaddTime;
        }

        public void setGetUserscaddTime(String getUserscaddTime) {
            this.getUserscaddTime = getUserscaddTime;
        }

        public String getGetUsersnickname() {
            return getUsersnickname;
        }

        public void setGetUsersnickname(String getUsersnickname) {
            this.getUsersnickname = getUsersnickname;
        }

        public String getGetUserscomplaint() {
            return getUserscomplaint;
        }

        public void setGetUserscomplaint(String getUserscomplaint) {
            this.getUserscomplaint = getUserscomplaint;
        }

        public String getGetUsersimgURL() {
            return getUsersimgURL;
        }

        public void setGetUsersimgURL(String getUsersimgURL) {
            this.getUsersimgURL = getUsersimgURL;
        }


        public String getGetUsersComplainEnd() {
            return getUsersComplainEnd;
        }

        public void setGetUsersComplainEnd(String getUsersComplainEnd) {
            this.getUsersComplainEnd = getUsersComplainEnd;
        }


        public int getGetUsersComplainIssystem() {
            return getUsersComplainIssystem;
        }

        public void setGetUsersComplainIssystem(int getUsersComplainIssystem) {
            this.getUsersComplainIssystem = getUsersComplainIssystem;
        }

        public String getGetexplain() {
            return getexplain;
        }

        public void setGetexplain(String getexplain) {
            this.getexplain = getexplain;
        }

        public String getDefault() {
            return Default;
        }

        public void setDefault(String aDefault) {
            Default = aDefault;
        }

        public String getStatementofsitefee() {
            return Statementofsitefee;
        }

        public void setStatementofsitefee(String statementofsitefee) {
            Statementofsitefee = statementofsitefee;
        }

        public String getExplanationofRewardFee() {
            return ExplanationofRewardFee;
        }

        public void setExplanationofRewardFee(String explanationofRewardFee) {
            ExplanationofRewardFee = explanationofRewardFee;
        }

        public String getGeneralGoldNotes() {
            return GeneralGoldNotes;
        }

        public void setGeneralGoldNotes(String generalGoldNotes) {
            GeneralGoldNotes = generalGoldNotes;
        }

        public String getProfessionalGoldNotes() {
            return ProfessionalGoldNotes;
        }

        public void setProfessionalGoldNotes(String professionalGoldNotes) {
            ProfessionalGoldNotes = professionalGoldNotes;
        }

        public int getIsQuit() {
            return isQuit;
        }

        public void setIsQuit(int isQuit) {
            this.isQuit = isQuit;
        }

        public int getIsQuitInGame() {
            return isQuitInGame;
        }

        public void setIsQuitInGame(int isQuitInGame) {
            this.isQuitInGame = isQuitInGame;
        }


        public String getResultReason() {
            return resultReason;
        }

        public void setResultReason(String resultReason) {
            this.resultReason = resultReason;
        }

        public int getIsPublisher() {
            return isPublisher;
        }

        public void setIsPublisher(int isPublisher) {
            this.isPublisher = isPublisher;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getSportMode() {
            return SportMode;
        }

        public void setSportMode(int SportMode) {
            this.SportMode = SportMode;
        }

        public int getSportId() {
            return SportId;
        }

        public void setSportId(int SportId) {
            this.SportId = SportId;
        }

        public int getSportType() {
            return SportType;
        }

        public void setSportType(int SportType) {
            this.SportType = SportType;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getPlayTime() {
            return PlayTime;
        }

        public void setPlayTime(String PlayTime) {
            this.PlayTime = PlayTime;
        }

        public String getSiteUid() {
            return siteUid;
        }

        public void setSiteUid(String siteUid) {
            this.siteUid = siteUid;
        }

        public String getTeamMateSex() {
            return TeamMateSex;
        }

        public void setTeamMateSex(String TeamMateSex) {
            this.TeamMateSex = TeamMateSex;
        }

        public String getOpponentsSex() {
            return OpponentsSex;
        }

        public void setOpponentsSex(String OpponentsSex) {
            this.OpponentsSex = OpponentsSex;
        }

        public String getTeamMateLevelMin() {
            return TeamMateLevelMin;
        }

        public void setTeamMateLevelMin(String TeamMateLevelMin) {
            this.TeamMateLevelMin = TeamMateLevelMin;
        }

        public String getTeamMateLevelMax() {
            return TeamMateLevelMax;
        }

        public void setTeamMateLevelMax(String TeamMateLevelMax) {
            this.TeamMateLevelMax = TeamMateLevelMax;
        }

        public String getOpponentsLevelMin() {
            return OpponentsLevelMin;
        }

        public void setOpponentsLevelMin(String OpponentsLevelMin) {
            this.OpponentsLevelMin = OpponentsLevelMin;
        }

        public String getOpponentsLevelMax() {
            return OpponentsLevelMax;
        }

        public void setOpponentsLevelMax(String OpponentsLevelMax) {
            this.OpponentsLevelMax = OpponentsLevelMax;
        }

        public double getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(double SiteMoney) {
            this.SiteMoney = SiteMoney;
        }

        public int getPaySiteMoneyType() {
            return PaySiteMoneyType;
        }

        public void setPaySiteMoneyType(int PaySiteMoneyType) {
            this.PaySiteMoneyType = PaySiteMoneyType;
        }

        public String getOtherPlayerNumber() {
            return OtherPlayerNumber;
        }

        public void setOtherPlayerNumber(String OtherPlayerNumber) {
            this.OtherPlayerNumber = OtherPlayerNumber;
        }

        public double getMoneyPerhour() {
            return MoneyPerhour;
        }

        public void setMoneyPerhour(double MoneyPerhour) {
            this.MoneyPerhour = MoneyPerhour;
        }

        public String getTips() {
            return Tips;
        }

        public void setTips(String Tips) {
            this.Tips = Tips;
        }

        public String getComments() {
            return Comments;
        }

        public void setComments(String Comments) {
            this.Comments = Comments;
        }

        public String getSuspendReason() {
            return SuspendReason;
        }

        public void setSuspendReason(String SuspendReason) {
            this.SuspendReason = SuspendReason;
        }

        public int getPublicStatus() {
            return PublicStatus;
        }

        public void setPublicStatus(int PublicStatus) {
            this.PublicStatus = PublicStatus;
        }

        public String getGroupChartUUID() {
            return GroupChartUUID;
        }

        public void setGroupChartUUID(String GroupChartUUID) {
            this.GroupChartUUID = GroupChartUUID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getFinishedTime() {
            return FinishedTime;
        }

        public void setFinishedTime(String FinishedTime) {
            this.FinishedTime = FinishedTime;
        }

        public String getCancelTime() {
            return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public String getJoinEndTime() {
            return JoinEndTime;
        }

        public void setJoinEndTime(String JoinEndTime) {
            this.JoinEndTime = JoinEndTime;
        }

        public int getFinalresult() {
            return finalresult;
        }

        public void setFinalresult(int finalresult) {
            this.finalresult = finalresult;
        }

        public int getPaied() {
            return paied;
        }

        public void setPaied(int paied) {
            this.paied = paied;
        }

        public String getSportName() {
            return sportName;
        }

        public void setSportName(String sportName) {
            this.sportName = sportName;
        }

        public String getSportTypeName() {
            return sportTypeName;
        }

        public void setSportTypeName(String sportTypeName) {
            this.sportTypeName = sportTypeName;
        }

        public int getNeedNumber() {
            return needNumber;
        }

        public void setNeedNumber(int needNumber) {
            this.needNumber = needNumber;
        }

        public String getStartWeek() {
            return startWeek;
        }

        public void setStartWeek(String startWeek) {
            this.startWeek = startWeek;
        }

        public String getStartDays() {
            return startDays;
        }

        public void setStartDays(String startDays) {
            this.startDays = startDays;
        }

        public String getStartTimes() {
            return startTimes;
        }

        public void setStartTimes(String startTimes) {
            this.startTimes = startTimes;
        }

        public String getEndWeek() {
            return endWeek;
        }

        public void setEndWeek(String endWeek) {
            this.endWeek = endWeek;
        }

        public String getEndDays() {
            return endDays;
        }

        public void setEndDays(String endDays) {
            this.endDays = endDays;
        }

        public String getEndTimes() {
            return endTimes;
        }

        public void setEndTimes(String endTimes) {
            this.endTimes = endTimes;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getSiteAddress() {
            return siteAddress;
        }

        public void setSiteAddress(String siteAddress) {
            this.siteAddress = siteAddress;
        }

        public String getSiteLat() {
            return siteLat;
        }

        public void setSiteLat(String siteLat) {
            this.siteLat = siteLat;
        }

        public String getSiteLng() {
            return siteLng;
        }

        public void setSiteLng(String siteLng) {
            this.siteLng = siteLng;
        }

        public int getJoinedCount() {
            return joinedCount;
        }

        public void setJoinedCount(int joinedCount) {
            this.joinedCount = joinedCount;
        }

        public int getLackNumber() {
            return lackNumber;
        }

        public void setLackNumber(int lackNumber) {
            this.lackNumber = lackNumber;
        }

        public String getPublishPlayerName() {
            return publishPlayerName;
        }

        public void setPublishPlayerName(String publishPlayerName) {
            this.publishPlayerName = publishPlayerName;
        }

        public String getPublishPlayerImg() {
            return publishPlayerImg;
        }

        public void setPublishPlayerImg(String publishPlayerImg) {
            this.publishPlayerImg = publishPlayerImg;
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

        public int getIsCooper() {
            return isCooper;
        }

        public String getGetCommonCoins() {
            return GetCommonCoins;
        }

        public void setGetCommonCoins(String getCommonCoins) {
            GetCommonCoins = getCommonCoins;
        }

        public String getGetCoins() {
            return GetCoins;
        }

        public void setGetCoins(String getCoins) {
            GetCoins = getCoins;
        }

        public String getGetSiteMoney() {
            return getSiteMoney;
        }

        public void setGetSiteMoney(String getSiteMoney) {
            this.getSiteMoney = getSiteMoney;
        }

        public String getGetWalletMoney() {
            return getWalletMoney;
        }

        public void setGetWalletMoney(String getWalletMoney) {
            this.getWalletMoney = getWalletMoney;
        }

        public String getGetOutMoney() {
            return getOutMoney;
        }

        public void setGetOutMoney(String getOutMoney) {
            this.getOutMoney = getOutMoney;
        }

        public String getGetTips() {
            return getTips;
        }

        public void setGetTips(String getTips) {
            this.getTips = getTips;
        }

        public String getGetMoneyPerhour() {
            return getMoneyPerhour;
        }

        public void setGetMoneyPerhour(String getMoneyPerhour) {
            this.getMoneyPerhour = getMoneyPerhour;
        }

        public void setIsCooper(int isCooper) {
            this.isCooper = isCooper;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public int getAwinBcount() {
            return AwinBcount;
        }

        public void setAwinBcount(int AwinBcount) {
            this.AwinBcount = AwinBcount;
        }

        public int getAloseBcount() {
            return AloseBcount;
        }

        public void setAloseBcount(int AloseBcount) {
            this.AloseBcount = AloseBcount;
        }

        public int getAdrawBcount() {
            return AdrawBcount;
        }

        public void setAdrawBcount(int AdrawBcount) {
            this.AdrawBcount = AdrawBcount;
        }

        public List<TeamABean> getTeamA() {
            return teamA;
        }

        public void setTeamA(List<TeamABean> teamA) {
            this.teamA = teamA;
        }

        public List<TeamBBean> getTeamB() {
            return teamB;
        }

        public void setTeamB(List<TeamBBean> teamB) {
            this.teamB = teamB;
        }

        public List<AwinBuserInfoBean> getAwinBuserInfo() {
            return AwinBuserInfo;
        }

        public void setAwinBuserInfo(List<AwinBuserInfoBean> AwinBuserInfo) {
            this.AwinBuserInfo = AwinBuserInfo;
        }

        public List<AloseBuserInfoBean> getAloseBuserInfo() {
            return AloseBuserInfo;
        }

        public void setAloseBuserInfo(List<AloseBuserInfoBean> AloseBuserInfo) {
            this.AloseBuserInfo = AloseBuserInfo;
        }

        public List<AdrawBuserInfoBean> getAdrawBcountInfo() {
            return AdrawBuserInfo;
        }

        public void setAdrawBcountInfo(List<AdrawBuserInfoBean> adrawBcountInfo) {
            AdrawBuserInfo = adrawBcountInfo;
        }

        public int getIsSignIn() {
            return isSignIn;
        }

        public void setIsSignIn(int isSignIn) {
            this.isSignIn = isSignIn;
        }


        public int getIsConfirmResult() {
            return isConfirmResult;
        }

        public void setIsConfirmResult(int isConfirmResult) {
            this.isConfirmResult = isConfirmResult;
        }

        public int getIsConfirmOver() {
            return isConfirmOver;
        }

        public void setIsConfirmOver(int isConfirmOver) {
            this.isConfirmOver = isConfirmOver;
        }

        public int getIsComment() {
            return isComment;
        }

        public void setIsComment(int isComment) {
            this.isComment = isComment;
        }

        public static class TeamABean {
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

            private int isSeat;
            private int isSignIn;
            private int isPublisher;
            private int isConfirmResult;
            private String invitedByUserName;
            private String InvitedByPlayerUUID;
            private int Result;
            private int isConfirmOver;
            private int isQuit;
            private int isQuitInGame;
            private int isComment;
            private String uuid;
            private String nickname;
            private String imgURL;
            private String heightLevelName;
            private String heightLevel;

            public int getResult() {
                return Result;
            }

            public void setResult(int result) {
                Result = result;
            }


            public String getInvitedByUserName() {
                return invitedByUserName;
            }

            public void setInvitedByUserName(String invitedByUserName) {
                this.invitedByUserName = invitedByUserName;
            }

            public String getInvitedByPlayerUUID() {
                return InvitedByPlayerUUID;
            }

            public void setInvitedByPlayerUUID(String invitedByPlayerUUID) {
                InvitedByPlayerUUID = invitedByPlayerUUID;
            }


            public int getIsConfirmOver() {
                return isConfirmOver;
            }

            public void setIsConfirmOver(int isConfirmOver) {
                this.isConfirmOver = isConfirmOver;
            }


            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public int getIsConfirmResult() {
                return isConfirmResult;
            }

            public void setIsConfirmResult(int isConfirmResult) {
                this.isConfirmResult = isConfirmResult;
            }

            public int getIsSeat() {
                return isSeat;
            }

            public void setIsSeat(int isSeat) {
                this.isSeat = isSeat;
            }

            public int getIsSignIn() {
                return isSignIn;
            }

            public void setIsSignIn(int isSignIn) {
                this.isSignIn = isSignIn;
            }

            public int getIsPublisher() {
                return isPublisher;
            }

            public void setIsPublisher(int isPublisher) {
                this.isPublisher = isPublisher;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

            public int getIsQuit() {
                return isQuit;
            }

            public void setIsQuit(int isQuit) {
                this.isQuit = isQuit;
            }

            public int getIsQuitInGame() {
                return isQuitInGame;
            }

            public void setIsQuitInGame(int isQuitInGame) {
                this.isQuitInGame = isQuitInGame;
            }


        }

        public static class TeamBBean {
            /**
             * isSeat : 0
             * isSignIn : 0
             * isPublisher : 0
             * uuid : e9ec5878-4c05-df3b-40d3-04fee16e767c
             * nickname : tz_25172019Jan03
             * imgURL : uploads/HeaderImgs/2019-01-03/20190103141632806.png
             * heightLevelName : 羽毛球
             * heightLevel : Lv1
             */

            private int isSeat;
            private int isSignIn;
            private int isPublisher;
            private int isQuit;
            private int isQuitInGame;
            private int isConfirmResult;
            private String invitedByUserName;
            private String InvitedByPlayerUUID;
            private int Result;
            private int isConfirmOver;
            private int isComment;
            private String uuid;
            private String nickname;
            private String imgURL;
            private String heightLevelName;
            private String heightLevel;

            public int getResult() {
                return Result;
            }

            public void setResult(int result) {
                Result = result;
            }


            public String getInvitedByUserName() {
                return invitedByUserName;
            }

            public void setInvitedByUserName(String invitedByUserName) {
                this.invitedByUserName = invitedByUserName;
            }

            public String getInvitedByPlayerUUID() {
                return InvitedByPlayerUUID;
            }

            public void setInvitedByPlayerUUID(String invitedByPlayerUUID) {
                InvitedByPlayerUUID = invitedByPlayerUUID;
            }


            public int getIsConfirmOver() {
                return isConfirmOver;
            }

            public void setIsConfirmOver(int isConfirmOver) {
                this.isConfirmOver = isConfirmOver;
            }


            public int getIsConfirmResult() {
                return isConfirmResult;
            }

            public void setIsConfirmResult(int isConfirmResult) {
                this.isConfirmResult = isConfirmResult;
            }

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public int getIsSeat() {
                return isSeat;
            }

            public void setIsSeat(int isSeat) {
                this.isSeat = isSeat;
            }

            public int getIsSignIn() {
                return isSignIn;
            }

            public void setIsSignIn(int isSignIn) {
                this.isSignIn = isSignIn;
            }

            public int getIsPublisher() {
                return isPublisher;
            }

            public void setIsPublisher(int isPublisher) {
                this.isPublisher = isPublisher;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

            public int getIsQuit() {
                return isQuit;
            }

            public void setIsQuit(int isQuit) {
                this.isQuit = isQuit;
            }

            public int getIsQuitInGame() {
                return isQuitInGame;
            }

            public void setIsQuitInGame(int isQuitInGame) {
                this.isQuitInGame = isQuitInGame;
            }


        }

        public static class AwinBuserInfoBean {
            /**
             * nickname : 王二二
             * imgURL : uploads/HeaderImgs/2019-01-03/20190103180853517.png
             */

            private String nickname;
            private String imgURL;
            private String uuid;
            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }
            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }
        }

        public static class AloseBuserInfoBean {
            /**
             * nickname : 王二二
             * imgURL : uploads/HeaderImgs/2019-01-03/20190103180853517.png
             */

            private String nickname;
            private String imgURL;
            private String uuid;
            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }
            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }
        }

        public static class AdrawBuserInfoBean {
            /**
             * nickname : 王二二
             * imgURL : uploads/HeaderImgs/2019-01-03/20190103180853517.png
             */

            private String nickname;
            private String imgURL;
            private String uuid;
            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }
            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }
        }

        public static class SignUserInfoBean {

        }

        public static class NotSignUserInfoBean {

        }

        public static class GetwaiverInfoBean {

            private String uuid;
            private String nickname;
            private String imgURL;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }
        }

    }
}
