package com.example.android.tiaozhan;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import android.util.Log;

import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.NetUtil;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyApplication extends MultiDexApplication {
    private static Context mContext;
    private static MyApplication myApp;


    // 记录是否已经初始化
    private boolean isInit = false;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initGreenDao();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                //其他配置
                .build();

        Request.Builder builder = new Request.Builder().url(getResources().getString(R.string.http_xutils_zpf_al_cs));

        Call call = okHttpClient.newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(e instanceof SocketTimeoutException){//判断超时异常
                LogU.Ld("1608","网络超时");
                }
                if(e instanceof ConnectException){//判断连接异常，我这里是报Failed to connect to 10.7.5.144
                    LogU.Ld("1608","网络超时=====");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogU.Ld("1608","网络超时=====+++++++");
            }
        });

        OkHttpUtils.initClient(okHttpClient);
        UMShareAPI.get(this);//初始化sdk
        //友盟相关平台配置。注意友盟官方新文档中没有这项配置，但是如果不配置会吊不起来相关平台的授权界面

//        PlatformConfig.setWeixin("wx60e2e2539670b0e5", "178ed01215c2edde5e8995bfa264bc58");//微信APPID和AppSecret

//        PlatformConfig.setQQZone("你的QQAPPID", "你的QQAppSecret");//QQAPPID和AppSecret

//        PlatformConfig.setSinaWeibo("你的微博APPID", "你的微博APPSecret","微博的后台配置回调地址");//微博

        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
//        Set<String> set = new HashSet<>();
//        set.add("18201395884");//名字任意，可多添加几个
//        JPushInterface.setTags(this, set, null);//设置标签
//        JPushInterface.setAlias(this,"18201395884",null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder1 = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder1.build());
        }
        //配置环信sdk
        EMOptions options = new EMOptions();
     // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
    // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
    //        options.setAutoTransferMessageAttachments(true);
    // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
    //        options.setAutoDownloadThumbnail(true);

//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
//
        mContext = getApplicationContext();


        myApp = this;

        // 初始化环信SDK
        initEasemob();

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    //各个平台的配置
    {
        //微信
        PlatformConfig.setWeixin("wx60e2e2539670b0e5", "178ed01215c2edde5e8995bfa264bc58");//微信APPID和AppSecret
//        //新浪微博(第三个参数为回调地址)
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com/sina2/callback");
//        //QQ
//        PlatformConfig.setQQZone("101450787", "1feebde12bfbe2979723e85041169fc9");
    }


    public static Context getContext(){

        return mContext;
    }

    private void initEasemob(){
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(myApp.getPackageName())) {
            Log.e("1608", "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        if(isInit){
            return ;
        }

        /**
         * SDK初始化的一些配置
         * 关于 EMOptions 可以参考官方的 API 文档
         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
         */
        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("lzan13#hxsdkdemo");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，TODO 这个暂时有bug，上层收不到发送回执
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
        options.setAutoTransferMessageAttachments(true);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        // 调用初始化方法初始化sdk
        EaseUI.getInstance().init(this, options);

        // 设置开启debug模式
        EMClient.getInstance().setDebugMode(true);

        // 设置初始化已经完成
        isInit = true;
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return null;
    }

    public static MyApplication getInstance() {
        return myApp;
    }


    EMMessageListener messageListener=new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //接收到新的消息
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Ringtone r = RingtoneManager.getRingtone(getContext(), notification);

            r.play();

//            refreshUIWithMessage();
            for (EMMessage message : list) {
                String userName = message.getStringAttribute("nickname", "");
                String userPic = message.getStringAttribute("avatar", "");
                LogU.Ld("1608","nickname---------"+userName+"avatar"+userPic);
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {
            LogU.Ld("1608","我是穿透---------");
        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };
//    @Override
//    public void onResume() {
//
//        super.onResume();
//        EMClient.getInstance().chatManager().addMessageListener(messageListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
//    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Blcs2.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    private static DaoSession daoSession;
    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
