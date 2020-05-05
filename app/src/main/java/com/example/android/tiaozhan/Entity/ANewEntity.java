package com.example.android.tiaozhan.Entity;

import java.util.List;

public class ANewEntity {

    /**
     * code : 2000
     * msg : 获取数据成功
     * data : {"uuid":"ed97fa52-f77d-d1d1-e97b-7a03bceab280","orderId":"012001071023487617","SportMode":2,"SportId":1,"SportType":4,"StartTime":"2020-01-07 11:00:00","PlayTime":1,"siteUid":"94da6c9c-8ced-d0e2-d54f-ad690d247134","venuenumber":0,"venueid":"3,3","TeamMateSex":2,"OpponentsSex":2,"TeamMateLevelMin":1,"TeamMateLevelMax":10,"OpponentsLevelMin":1,"OpponentsLevelMax":10,"SiteMoney":80,"PaySiteMoneyType":1,"OtherPlayerNumber":"","MoneyPerhour":0,"Tips":"+0","RoyaltyMoney":1.6,"Comments":"","SuspendReason":"","PublicStatus":3,"GroupChartUUID":"0","CreateTime":"2020-01-07 10:23:48","FinishedTime":"2020-01-07 12:00:00","cancelTime":"","JoinEndTime":"2020-01-07 10:30:00","finalresult":0,"paied":1,"synced":0,"cancelType":1,"reserve":0,"referee":1,"refereeFee":600,"RefereeNumber":2,"Refereegrade":"一级","SigninYes":1,"sportName":"羽毛球","sportTypeName":"单打","needNumber":2,"startWeek":"星期二","startDays":"2020-01-07","startTimes":"11:00:00","endWeek":"星期二","endDays":"2020-01-07","endTimes":"12:00:00","siteName":"山西省体育健身馆","siteAddress":"吕梁市汾阳市英雄中路17号","siteLat":"39.8770090","siteLng":"116.7014060","joinedCount":4,"lackNumber":0,"publishPlayerName":"测试30","publishPlayerImg":"uploads/HeaderImgs/2020-01-03/20200103135318465.png","heightLevelName":"台球","heightLevel":"Lv10","isCooper":1,"getUserComplaint":3,"getRefereeComplaint":2,"isConfirmResult":0,"isPublisher":0,"isSignIn":1,"isConfirmOver":0,"isComment":0,"isQuitInGame":1,"isQuit":1,"oldTips":0,"GetCommonCoins":"","GetCoins":"","getTips":"","getSiteMoney":"","getWalletMoney":"","getMoneyPerhour":"","Comment":1,"groupId":"","teamA":[{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":1,"isSeat":0,"isSignIn":1,"isPublisher":1,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"4fc781ef-72cf-91c3-9b23-398647bbe7cc","nickname":"测试30","imgURL":"uploads/HeaderImgs/2020-01-03/20200103135318465.png","heightLevelName":"羽毛球","heightLevel":"Lv5","isConfirmResult":0}],"teamB":[{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":0,"isSeat":0,"isSignIn":1,"isPublisher":0,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"5058b7b5-f89a-9eff-f260-2941a2c9f638","nickname":"测试31","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105309934.png","heightLevelName":"羽毛球","heightLevel":"Lv7","isConfirmResult":0}],"teamC":[{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":0,"isSeat":0,"isSignIn":0,"isPublisher":0,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"658cec96-a558-c019-a190-bba51f57d3ec","nickname":"笨小孩","imgURL":"uploads/HeaderImgs/2019-12-31/20191231172029575.png","heightLevelName":"羽毛球","heightLevel":"Lv1","isConfirmResult":0},{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":0,"isSeat":0,"isSignIn":0,"isPublisher":0,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2020-01-02/20200102112103655.png","heightLevelName":"羽毛球","heightLevel":"Lv9","isConfirmResult":0}],"getexplain":"","resultReason":"","SignUserInfo":[],"notSignUserInfo":[],"AwinBcount":0,"AwinBuserInfo":[],"AloseBcount":0,"AloseBuserInfo":[],"AdrawBcount":0,"AdrawBuserInfo":[],"getwaiver":0,"getwaiverInfo":[],"group_name":"羽毛球单打20200107 11:00","getUsersComplainEnd":"无","getUsersComplainIssystem":0,"getUserscaddTime":"1970-01-01 08:00:00","getUsersnickname":"","getUserscomplaint":"投诉场馆未预留场地","getUsersimgURL":"","Complaint":4,"isHandle":"","complaintistrue":"","getUsersrecording":"","detailed":"推广员/平台:认定场馆预留了场地,活动按照正常活动认定。","Unreserved":"投诉场馆未预留场地","getRefereecaddTime":"2020-01-07 10:57:07","getRefereeimgURL":"uploads/HeaderImgs/2020-01-03/20200103135318465.png","getRefereenickname":"测试30","getReferee":[{"uuid":"658cec96-a558-c019-a190-bba51f57d3ec","nickname":"笨小孩","imgURL":"uploads/HeaderImgs/2019-12-31/20191231172029575.png"},{"uuid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2020-01-02/20200102112103655.png"}],"getRefereeComplainEnd":"无","getRefereerecording":"","getRefereeComplainIssystem":0,"RefereeisHandle":0,"RefereeComplaint":0,"Refereecomplaintistrue":"","Refereedetailed":"推广员/平台:认定裁判到场了,活动按照正常活动认定。","getRefereecomplaint":"投诉裁判未到场","Rfereeeerved":"投诉裁判未到场"}
     * other :
     */

    private int code;
    private String msg;
    private DataBean data;
    private String other;

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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * uuid : ed97fa52-f77d-d1d1-e97b-7a03bceab280
         * orderId : 012001071023487617
         * SportMode : 2
         * SportId : 1
         * SportType : 4
         * StartTime : 2020-01-07 11:00:00
         * PlayTime : 1
         * siteUid : 94da6c9c-8ced-d0e2-d54f-ad690d247134
         * venuenumber : 0
         * venueid : 3,3
         * TeamMateSex : 2
         * OpponentsSex : 2
         * TeamMateLevelMin : 1
         * TeamMateLevelMax : 10
         * OpponentsLevelMin : 1
         * OpponentsLevelMax : 10
         * SiteMoney : 80
         * PaySiteMoneyType : 1
         * OtherPlayerNumber :
         * MoneyPerhour : 0
         * Tips : +0
         * RoyaltyMoney : 1.6
         * Comments :
         * SuspendReason :
         * PublicStatus : 3
         * GroupChartUUID : 0
         * CreateTime : 2020-01-07 10:23:48
         * FinishedTime : 2020-01-07 12:00:00
         * cancelTime :
         * JoinEndTime : 2020-01-07 10:30:00
         * finalresult : 0
         * paied : 1
         * synced : 0
         * cancelType : 1
         * reserve : 0
         * referee : 1
         * refereeFee : 600
         * RefereeNumber : 2
         * Refereegrade : 一级
         * SigninYes : 1
         * sportName : 羽毛球
         * sportTypeName : 单打
         * needNumber : 2
         * startWeek : 星期二
         * startDays : 2020-01-07
         * startTimes : 11:00:00
         * endWeek : 星期二
         * endDays : 2020-01-07
         * endTimes : 12:00:00
         * siteName : 山西省体育健身馆
         * siteAddress : 吕梁市汾阳市英雄中路17号
         * siteLat : 39.8770090
         * siteLng : 116.7014060
         * joinedCount : 4
         * lackNumber : 0
         * publishPlayerName : 测试30
         * publishPlayerImg : uploads/HeaderImgs/2020-01-03/20200103135318465.png
         * heightLevelName : 台球
         * heightLevel : Lv10
         * isCooper : 1
         * getUserComplaint : 3
         * getRefereeComplaint : 2
         * isConfirmResult : 0
         * isPublisher : 0
         * isSignIn : 1
         * isConfirmOver : 0
         * isComment : 0
         * isQuitInGame : 1
         * isQuit : 1
         * oldTips : 0
         * GetCommonCoins :
         * GetCoins :
         * getTips :
         * getSiteMoney :
         * getWalletMoney :
         * getMoneyPerhour :
         * Comment : 1
         * groupId :
         * teamA : [{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":1,"isSeat":0,"isSignIn":1,"isPublisher":1,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"4fc781ef-72cf-91c3-9b23-398647bbe7cc","nickname":"测试30","imgURL":"uploads/HeaderImgs/2020-01-03/20200103135318465.png","heightLevelName":"羽毛球","heightLevel":"Lv5","isConfirmResult":0}]
         * teamB : [{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":0,"isSeat":0,"isSignIn":1,"isPublisher":0,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"5058b7b5-f89a-9eff-f260-2941a2c9f638","nickname":"测试31","imgURL":"uploads/HeaderImgs/2019-09-17/20190917105309934.png","heightLevelName":"羽毛球","heightLevel":"Lv7","isConfirmResult":0}]
         * teamC : [{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":0,"isSeat":0,"isSignIn":0,"isPublisher":0,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"658cec96-a558-c019-a190-bba51f57d3ec","nickname":"笨小孩","imgURL":"uploads/HeaderImgs/2019-12-31/20191231172029575.png","heightLevelName":"羽毛球","heightLevel":"Lv1","isConfirmResult":0},{"InvitedByPlayerUUID":"0","invitedByUserName":"","Result":0,"isSeat":0,"isSignIn":0,"isPublisher":0,"isComment":0,"isCancel":0,"isQuit":1,"isQuitInGame":1,"isConfirmOver":0,"uuid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2020-01-02/20200102112103655.png","heightLevelName":"羽毛球","heightLevel":"Lv9","isConfirmResult":0}]
         * getexplain :
         * resultReason :
         * SignUserInfo : []
         * notSignUserInfo : []
         * AwinBcount : 0
         * AwinBuserInfo : []
         * AloseBcount : 0
         * AloseBuserInfo : []
         * AdrawBcount : 0
         * AdrawBuserInfo : []
         * getwaiver : 0
         * getwaiverInfo : []
         * group_name : 羽毛球单打20200107 11:00
         * getUsersComplainEnd : 无
         * getUsersComplainIssystem : 0
         * getUserscaddTime : 1970-01-01 08:00:00
         * getUsersnickname :
         * getUserscomplaint : 投诉场馆未预留场地
         * getUsersimgURL :
         * Complaint : 4
         * isHandle :
         * complaintistrue :
         * getUsersrecording :
         * detailed : 推广员/平台:认定场馆预留了场地,活动按照正常活动认定。
         * Unreserved : 投诉场馆未预留场地
         * getRefereecaddTime : 2020-01-07 10:57:07
         * getRefereeimgURL : uploads/HeaderImgs/2020-01-03/20200103135318465.png
         * getRefereenickname : 测试30
         * getReferee : [{"uuid":"658cec96-a558-c019-a190-bba51f57d3ec","nickname":"笨小孩","imgURL":"uploads/HeaderImgs/2019-12-31/20191231172029575.png"},{"uuid":"b9e420dc-b95b-8d59-8e24-836f0d8667ac","nickname":"xxoo","imgURL":"uploads/HeaderImgs/2020-01-02/20200102112103655.png"}]
         * getRefereeComplainEnd : 无
         * getRefereerecording :
         * getRefereeComplainIssystem : 0
         * RefereeisHandle : 0
         * RefereeComplaint : 0
         * Refereecomplaintistrue :
         * Refereedetailed : 推广员/平台:认定裁判到场了,活动按照正常活动认定。
         * getRefereecomplaint : 投诉裁判未到场
         * Rfereeeerved : 投诉裁判未到场
         */

        private String uuid;
        private String orderId;
        private int SportMode;
        private int SportId;
        private int SportType;
        private String StartTime;
        private int PlayTime;
        private String siteUid;
        private int venuenumber;
        private String venueid;
        private int TeamMateSex;
        private int OpponentsSex;
        private int TeamMateLevelMin;
        private int TeamMateLevelMax;
        private int OpponentsLevelMin;
        private int OpponentsLevelMax;
        private int SiteMoney;
        private int PaySiteMoneyType;
        private String OtherPlayerNumber;
        private int MoneyPerhour;
        private String Tips;
        private double RoyaltyMoney;
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
        private int synced;
        private int cancelType;
        private int reserve;
        private int referee;
        private int refereeFee;
        private int RefereeNumber;
        private String Refereegrade;
        private int SigninYes;
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
        private int getUserComplaint;
        private int getRefereeComplaint;
        private int isConfirmResult;
        private int isPublisher;
        private int isSignIn;
        private int isConfirmOver;
        private int isComment;
        private int isQuitInGame;
        private int isQuit;
        private int oldTips;
        private String GetCommonCoins;
        private String GetCoins;
        private String getTips;
        private String getSiteMoney;
        private String getWalletMoney;
        private String getMoneyPerhour;
        private int Comment;
        private String groupId;
        private String getexplain;
        private String resultReason;
        private int AwinBcount;
        private int AloseBcount;
        private int AdrawBcount;
        private int getwaiver;
        private String group_name;
        private String getUsersComplainEnd;
        private int getUsersComplainIssystem;
        private String getUserscaddTime;
        private String getUsersnickname;
        private String getUserscomplaint;
        private String getUsersimgURL;
        private int Complaint;
        private String isHandle;
        private String complaintistrue;
        private String getUsersrecording;
        private String detailed;
        private String Unreserved;
        private String getRefereecaddTime;
        private String getRefereeimgURL;
        private String getRefereenickname;
        private String getRefereeComplainEnd;
        private String getRefereerecording;
        private int getRefereeComplainIssystem;
        private int RefereeisHandle;
        private int RefereeComplaint;
        private String Refereecomplaintistrue;
        private String Refereedetailed;
        private String getRefereecomplaint;
        private String Rfereeeerved;
        private List<TeamABean> teamA;
        private List<TeamBBean> teamB;
        private List<TeamCBean> teamC;
        private List<?> SignUserInfo;
        private List<?> notSignUserInfo;
        private List<?> AwinBuserInfo;
        private List<?> AloseBuserInfo;
        private List<?> AdrawBuserInfo;
        private List<?> getwaiverInfo;
        private List<GetRefereeBean> getReferee;

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

        public int getPlayTime() {
            return PlayTime;
        }

        public void setPlayTime(int PlayTime) {
            this.PlayTime = PlayTime;
        }

        public String getSiteUid() {
            return siteUid;
        }

        public void setSiteUid(String siteUid) {
            this.siteUid = siteUid;
        }

        public int getVenuenumber() {
            return venuenumber;
        }

        public void setVenuenumber(int venuenumber) {
            this.venuenumber = venuenumber;
        }

        public String getVenueid() {
            return venueid;
        }

        public void setVenueid(String venueid) {
            this.venueid = venueid;
        }

        public int getTeamMateSex() {
            return TeamMateSex;
        }

        public void setTeamMateSex(int TeamMateSex) {
            this.TeamMateSex = TeamMateSex;
        }

        public int getOpponentsSex() {
            return OpponentsSex;
        }

        public void setOpponentsSex(int OpponentsSex) {
            this.OpponentsSex = OpponentsSex;
        }

        public int getTeamMateLevelMin() {
            return TeamMateLevelMin;
        }

        public void setTeamMateLevelMin(int TeamMateLevelMin) {
            this.TeamMateLevelMin = TeamMateLevelMin;
        }

        public int getTeamMateLevelMax() {
            return TeamMateLevelMax;
        }

        public void setTeamMateLevelMax(int TeamMateLevelMax) {
            this.TeamMateLevelMax = TeamMateLevelMax;
        }

        public int getOpponentsLevelMin() {
            return OpponentsLevelMin;
        }

        public void setOpponentsLevelMin(int OpponentsLevelMin) {
            this.OpponentsLevelMin = OpponentsLevelMin;
        }

        public int getOpponentsLevelMax() {
            return OpponentsLevelMax;
        }

        public void setOpponentsLevelMax(int OpponentsLevelMax) {
            this.OpponentsLevelMax = OpponentsLevelMax;
        }

        public int getSiteMoney() {
            return SiteMoney;
        }

        public void setSiteMoney(int SiteMoney) {
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

        public int getMoneyPerhour() {
            return MoneyPerhour;
        }

        public void setMoneyPerhour(int MoneyPerhour) {
            this.MoneyPerhour = MoneyPerhour;
        }

        public String getTips() {
            return Tips;
        }

        public void setTips(String Tips) {
            this.Tips = Tips;
        }

        public double getRoyaltyMoney() {
            return RoyaltyMoney;
        }

        public void setRoyaltyMoney(double RoyaltyMoney) {
            this.RoyaltyMoney = RoyaltyMoney;
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

        public int getSynced() {
            return synced;
        }

        public void setSynced(int synced) {
            this.synced = synced;
        }

        public int getCancelType() {
            return cancelType;
        }

        public void setCancelType(int cancelType) {
            this.cancelType = cancelType;
        }

        public int getReserve() {
            return reserve;
        }

        public void setReserve(int reserve) {
            this.reserve = reserve;
        }

        public int getReferee() {
            return referee;
        }

        public void setReferee(int referee) {
            this.referee = referee;
        }

        public int getRefereeFee() {
            return refereeFee;
        }

        public void setRefereeFee(int refereeFee) {
            this.refereeFee = refereeFee;
        }

        public int getRefereeNumber() {
            return RefereeNumber;
        }

        public void setRefereeNumber(int RefereeNumber) {
            this.RefereeNumber = RefereeNumber;
        }

        public String getRefereegrade() {
            return Refereegrade;
        }

        public void setRefereegrade(String Refereegrade) {
            this.Refereegrade = Refereegrade;
        }

        public int getSigninYes() {
            return SigninYes;
        }

        public void setSigninYes(int SigninYes) {
            this.SigninYes = SigninYes;
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

        public void setIsCooper(int isCooper) {
            this.isCooper = isCooper;
        }

        public int getGetUserComplaint() {
            return getUserComplaint;
        }

        public void setGetUserComplaint(int getUserComplaint) {
            this.getUserComplaint = getUserComplaint;
        }

        public int getGetRefereeComplaint() {
            return getRefereeComplaint;
        }

        public void setGetRefereeComplaint(int getRefereeComplaint) {
            this.getRefereeComplaint = getRefereeComplaint;
        }

        public int getIsConfirmResult() {
            return isConfirmResult;
        }

        public void setIsConfirmResult(int isConfirmResult) {
            this.isConfirmResult = isConfirmResult;
        }

        public int getIsPublisher() {
            return isPublisher;
        }

        public void setIsPublisher(int isPublisher) {
            this.isPublisher = isPublisher;
        }

        public int getIsSignIn() {
            return isSignIn;
        }

        public void setIsSignIn(int isSignIn) {
            this.isSignIn = isSignIn;
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

        public int getIsQuitInGame() {
            return isQuitInGame;
        }

        public void setIsQuitInGame(int isQuitInGame) {
            this.isQuitInGame = isQuitInGame;
        }

        public int getIsQuit() {
            return isQuit;
        }

        public void setIsQuit(int isQuit) {
            this.isQuit = isQuit;
        }

        public int getOldTips() {
            return oldTips;
        }

        public void setOldTips(int oldTips) {
            this.oldTips = oldTips;
        }

        public String getGetCommonCoins() {
            return GetCommonCoins;
        }

        public void setGetCommonCoins(String GetCommonCoins) {
            this.GetCommonCoins = GetCommonCoins;
        }

        public String getGetCoins() {
            return GetCoins;
        }

        public void setGetCoins(String GetCoins) {
            this.GetCoins = GetCoins;
        }

        public String getGetTips() {
            return getTips;
        }

        public void setGetTips(String getTips) {
            this.getTips = getTips;
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

        public String getGetMoneyPerhour() {
            return getMoneyPerhour;
        }

        public void setGetMoneyPerhour(String getMoneyPerhour) {
            this.getMoneyPerhour = getMoneyPerhour;
        }

        public int getComment() {
            return Comment;
        }

        public void setComment(int Comment) {
            this.Comment = Comment;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGetexplain() {
            return getexplain;
        }

        public void setGetexplain(String getexplain) {
            this.getexplain = getexplain;
        }

        public String getResultReason() {
            return resultReason;
        }

        public void setResultReason(String resultReason) {
            this.resultReason = resultReason;
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

        public int getGetwaiver() {
            return getwaiver;
        }

        public void setGetwaiver(int getwaiver) {
            this.getwaiver = getwaiver;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
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

        public int getComplaint() {
            return Complaint;
        }

        public void setComplaint(int Complaint) {
            this.Complaint = Complaint;
        }

        public String getIsHandle() {
            return isHandle;
        }

        public void setIsHandle(String isHandle) {
            this.isHandle = isHandle;
        }

        public String getComplaintistrue() {
            return complaintistrue;
        }

        public void setComplaintistrue(String complaintistrue) {
            this.complaintistrue = complaintistrue;
        }

        public String getGetUsersrecording() {
            return getUsersrecording;
        }

        public void setGetUsersrecording(String getUsersrecording) {
            this.getUsersrecording = getUsersrecording;
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

        public void setUnreserved(String Unreserved) {
            this.Unreserved = Unreserved;
        }

        public String getGetRefereecaddTime() {
            return getRefereecaddTime;
        }

        public void setGetRefereecaddTime(String getRefereecaddTime) {
            this.getRefereecaddTime = getRefereecaddTime;
        }

        public String getGetRefereeimgURL() {
            return getRefereeimgURL;
        }

        public void setGetRefereeimgURL(String getRefereeimgURL) {
            this.getRefereeimgURL = getRefereeimgURL;
        }

        public String getGetRefereenickname() {
            return getRefereenickname;
        }

        public void setGetRefereenickname(String getRefereenickname) {
            this.getRefereenickname = getRefereenickname;
        }

        public String getGetRefereeComplainEnd() {
            return getRefereeComplainEnd;
        }

        public void setGetRefereeComplainEnd(String getRefereeComplainEnd) {
            this.getRefereeComplainEnd = getRefereeComplainEnd;
        }

        public String getGetRefereerecording() {
            return getRefereerecording;
        }

        public void setGetRefereerecording(String getRefereerecording) {
            this.getRefereerecording = getRefereerecording;
        }

        public int getGetRefereeComplainIssystem() {
            return getRefereeComplainIssystem;
        }

        public void setGetRefereeComplainIssystem(int getRefereeComplainIssystem) {
            this.getRefereeComplainIssystem = getRefereeComplainIssystem;
        }

        public int getRefereeisHandle() {
            return RefereeisHandle;
        }

        public void setRefereeisHandle(int RefereeisHandle) {
            this.RefereeisHandle = RefereeisHandle;
        }

        public int getRefereeComplaint() {
            return RefereeComplaint;
        }

        public void setRefereeComplaint(int RefereeComplaint) {
            this.RefereeComplaint = RefereeComplaint;
        }

        public String getRefereecomplaintistrue() {
            return Refereecomplaintistrue;
        }

        public void setRefereecomplaintistrue(String Refereecomplaintistrue) {
            this.Refereecomplaintistrue = Refereecomplaintistrue;
        }

        public String getRefereedetailed() {
            return Refereedetailed;
        }

        public void setRefereedetailed(String Refereedetailed) {
            this.Refereedetailed = Refereedetailed;
        }

        public String getGetRefereecomplaint() {
            return getRefereecomplaint;
        }

        public void setGetRefereecomplaint(String getRefereecomplaint) {
            this.getRefereecomplaint = getRefereecomplaint;
        }

        public String getRfereeeerved() {
            return Rfereeeerved;
        }

        public void setRfereeeerved(String Rfereeeerved) {
            this.Rfereeeerved = Rfereeeerved;
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

        public List<TeamCBean> getTeamC() {
            return teamC;
        }

        public void setTeamC(List<TeamCBean> teamC) {
            this.teamC = teamC;
        }

        public List<?> getSignUserInfo() {
            return SignUserInfo;
        }

        public void setSignUserInfo(List<?> SignUserInfo) {
            this.SignUserInfo = SignUserInfo;
        }

        public List<?> getNotSignUserInfo() {
            return notSignUserInfo;
        }

        public void setNotSignUserInfo(List<?> notSignUserInfo) {
            this.notSignUserInfo = notSignUserInfo;
        }

        public List<?> getAwinBuserInfo() {
            return AwinBuserInfo;
        }

        public void setAwinBuserInfo(List<?> AwinBuserInfo) {
            this.AwinBuserInfo = AwinBuserInfo;
        }

        public List<?> getAloseBuserInfo() {
            return AloseBuserInfo;
        }

        public void setAloseBuserInfo(List<?> AloseBuserInfo) {
            this.AloseBuserInfo = AloseBuserInfo;
        }

        public List<?> getAdrawBuserInfo() {
            return AdrawBuserInfo;
        }

        public void setAdrawBuserInfo(List<?> AdrawBuserInfo) {
            this.AdrawBuserInfo = AdrawBuserInfo;
        }

        public List<?> getGetwaiverInfo() {
            return getwaiverInfo;
        }

        public void setGetwaiverInfo(List<?> getwaiverInfo) {
            this.getwaiverInfo = getwaiverInfo;
        }

        public List<GetRefereeBean> getGetReferee() {
            return getReferee;
        }

        public void setGetReferee(List<GetRefereeBean> getReferee) {
            this.getReferee = getReferee;
        }

        public static class TeamABean {
            /**
             * InvitedByPlayerUUID : 0
             * invitedByUserName :
             * Result : 1
             * isSeat : 0
             * isSignIn : 1
             * isPublisher : 1
             * isComment : 0
             * isCancel : 0
             * isQuit : 1
             * isQuitInGame : 1
             * isConfirmOver : 0
             * uuid : 4fc781ef-72cf-91c3-9b23-398647bbe7cc
             * nickname : 测试30
             * imgURL : uploads/HeaderImgs/2020-01-03/20200103135318465.png
             * heightLevelName : 羽毛球
             * heightLevel : Lv5
             * isConfirmResult : 0
             */

            private String InvitedByPlayerUUID;
            private String invitedByUserName;
            private int Result;
            private int isSeat;
            private int isSignIn;
            private int isPublisher;
            private int isComment;
            private int isCancel;
            private int isQuit;
            private int isQuitInGame;
            private int isConfirmOver;
            private String uuid;
            private String nickname;
            private String imgURL;
            private String heightLevelName;
            private String heightLevel;
            private int isConfirmResult;

            public String getInvitedByPlayerUUID() {
                return InvitedByPlayerUUID;
            }

            public void setInvitedByPlayerUUID(String InvitedByPlayerUUID) {
                this.InvitedByPlayerUUID = InvitedByPlayerUUID;
            }

            public String getInvitedByUserName() {
                return invitedByUserName;
            }

            public void setInvitedByUserName(String invitedByUserName) {
                this.invitedByUserName = invitedByUserName;
            }

            public int getResult() {
                return Result;
            }

            public void setResult(int Result) {
                this.Result = Result;
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

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public int getIsCancel() {
                return isCancel;
            }

            public void setIsCancel(int isCancel) {
                this.isCancel = isCancel;
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

            public int getIsConfirmOver() {
                return isConfirmOver;
            }

            public void setIsConfirmOver(int isConfirmOver) {
                this.isConfirmOver = isConfirmOver;
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

            public int getIsConfirmResult() {
                return isConfirmResult;
            }

            public void setIsConfirmResult(int isConfirmResult) {
                this.isConfirmResult = isConfirmResult;
            }
        }

        public static class TeamBBean {
            /**
             * InvitedByPlayerUUID : 0
             * invitedByUserName :
             * Result : 0
             * isSeat : 0
             * isSignIn : 1
             * isPublisher : 0
             * isComment : 0
             * isCancel : 0
             * isQuit : 1
             * isQuitInGame : 1
             * isConfirmOver : 0
             * uuid : 5058b7b5-f89a-9eff-f260-2941a2c9f638
             * nickname : 测试31
             * imgURL : uploads/HeaderImgs/2019-09-17/20190917105309934.png
             * heightLevelName : 羽毛球
             * heightLevel : Lv7
             * isConfirmResult : 0
             */

            private String InvitedByPlayerUUID;
            private String invitedByUserName;
            private int Result;
            private int isSeat;
            private int isSignIn;
            private int isPublisher;
            private int isComment;
            private int isCancel;
            private int isQuit;
            private int isQuitInGame;
            private int isConfirmOver;
            private String uuid;
            private String nickname;
            private String imgURL;
            private String heightLevelName;
            private String heightLevel;
            private int isConfirmResult;

            public String getInvitedByPlayerUUID() {
                return InvitedByPlayerUUID;
            }

            public void setInvitedByPlayerUUID(String InvitedByPlayerUUID) {
                this.InvitedByPlayerUUID = InvitedByPlayerUUID;
            }

            public String getInvitedByUserName() {
                return invitedByUserName;
            }

            public void setInvitedByUserName(String invitedByUserName) {
                this.invitedByUserName = invitedByUserName;
            }

            public int getResult() {
                return Result;
            }

            public void setResult(int Result) {
                this.Result = Result;
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

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public int getIsCancel() {
                return isCancel;
            }

            public void setIsCancel(int isCancel) {
                this.isCancel = isCancel;
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

            public int getIsConfirmOver() {
                return isConfirmOver;
            }

            public void setIsConfirmOver(int isConfirmOver) {
                this.isConfirmOver = isConfirmOver;
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

            public int getIsConfirmResult() {
                return isConfirmResult;
            }

            public void setIsConfirmResult(int isConfirmResult) {
                this.isConfirmResult = isConfirmResult;
            }
        }

        public static class TeamCBean {
            /**
             * InvitedByPlayerUUID : 0
             * invitedByUserName :
             * Result : 0
             * isSeat : 0
             * isSignIn : 0
             * isPublisher : 0
             * isComment : 0
             * isCancel : 0
             * isQuit : 1
             * isQuitInGame : 1
             * isConfirmOver : 0
             * uuid : 658cec96-a558-c019-a190-bba51f57d3ec
             * nickname : 笨小孩
             * imgURL : uploads/HeaderImgs/2019-12-31/20191231172029575.png
             * heightLevelName : 羽毛球
             * heightLevel : Lv1
             * isConfirmResult : 0
             */

            private String InvitedByPlayerUUID;
            private String invitedByUserName;
            private int Result;
            private int isSeat;
            private int isSignIn;
            private int isPublisher;
            private int isComment;
            private int isCancel;
            private int isQuit;
            private int isQuitInGame;
            private int isConfirmOver;
            private String uuid;
            private String nickname;
            private String imgURL;
            private String heightLevelName;
            private String heightLevel;
            private int isConfirmResult;

            public String getInvitedByPlayerUUID() {
                return InvitedByPlayerUUID;
            }

            public void setInvitedByPlayerUUID(String InvitedByPlayerUUID) {
                this.InvitedByPlayerUUID = InvitedByPlayerUUID;
            }

            public String getInvitedByUserName() {
                return invitedByUserName;
            }

            public void setInvitedByUserName(String invitedByUserName) {
                this.invitedByUserName = invitedByUserName;
            }

            public int getResult() {
                return Result;
            }

            public void setResult(int Result) {
                this.Result = Result;
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

            public int getIsComment() {
                return isComment;
            }

            public void setIsComment(int isComment) {
                this.isComment = isComment;
            }

            public int getIsCancel() {
                return isCancel;
            }

            public void setIsCancel(int isCancel) {
                this.isCancel = isCancel;
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

            public int getIsConfirmOver() {
                return isConfirmOver;
            }

            public void setIsConfirmOver(int isConfirmOver) {
                this.isConfirmOver = isConfirmOver;
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

            public int getIsConfirmResult() {
                return isConfirmResult;
            }

            public void setIsConfirmResult(int isConfirmResult) {
                this.isConfirmResult = isConfirmResult;
            }
        }

        public static class GetRefereeBean {
            /**
             * uuid : 658cec96-a558-c019-a190-bba51f57d3ec
             * nickname : 笨小孩
             * imgURL : uploads/HeaderImgs/2019-12-31/20191231172029575.png
             */

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
