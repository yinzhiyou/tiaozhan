package com.example.android.promoter.Toos;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.promoter.Home.XiaoxiActivity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "1608";
    private static String id;
    private static String tab;
    private static String tag;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        LogU.Ld(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            LogU.Ld(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogU.Ld(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LogU.Ld(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            LogU.Ld(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogU.Ld("1608", "[MyReceiver] 用户点击打开了通知");
//
//          //打开自定义的Activity
//          Intent i = new Intent(context, TestActivity.class);
//          i.putExtras(bundle);
//          //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//          context.startActivity(i);

            //打开自定义的Activity
            Bundle bundle1 = new Bundle();
                Intent i = new Intent(context, XiaoxiActivity.class);
//                i.putExtras(bundle);
//                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                bundle1.putString("id",id);
//                bundle1.putString("tag",tag);
//                i.putExtras(bundle1);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            LogU.Ld(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            LogU.Ld(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            LogU.Ld(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            LogU.Ld(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }


//            try {
//
//                Bundle bundle = intent.getExtras();
//
//                Log.e(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//                    Log.e(TAG, "8.0处理了");
//
//                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//                    int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);//定义通知id
//
//                    String channelId = context.getPackageName();//通知渠道id
//
//                    String channelName = "消息通知";//"PUSH_NOTIFY_NAME";//通知渠道名
//
//                    int importance = NotificationManager.IMPORTANCE_HIGH;//通知级别
//
//                    NotificationChannel channel = new NotificationChannel(channelId , channelName,  Integer.parseInt(channelId));
//
////                channel.enableLights(true);//设置闪光灯
//
//                    channel.setLightColor(Color.RED);
//
//                    channel.enableVibration(true);//设置通知出现震动
//
//                    channel.setShowBadge(true);
//
//                    channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//
//                    notificationManager.createNotificationChannel(channel);
//
//
//
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
////                    Intent notificationIntent = new Intent(context, NameSeachActivity.class);
////
////                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//                    String message = bundle.getString(JPushInterface.EXTRA_ALERT);
//
//                    String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//
//                    String s = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//                    Intent intent0= new Intent(context, MainActivity.class);
//
//                    PendingIntent pi = PendingIntent.getActivity(context, 0, intent0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                    builder.setContentTitle(title)//设置通知栏标题
//
//                            .setContentText(message)
//
//                            .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
//
//                            .setSmallIcon(R.mipmap.logo)//设置通知小ICON
//
//                            .setChannelId(channelId)
//
//                            .setDefaults(Notification.DEFAULT_ALL)
//
//                            .setContentIntent(pi);
//
//                    Notification notification = builder.build();
//
//                    notification.flags |= Notification.FLAG_AUTO_CANCEL;
//
//                    if (notificationManager != null) {
//
//                        notificationManager.notify(notificationId , notification);
//
//                    }
//
//                } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//
//                    //跳转
//
//                }
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//                Log.e(TAG, "极光推送出错:" + e.getMessage());
//
//            }

        }

            // 打印所有的 intent extra 数据
            private static String printBundle (Bundle bundle){
                StringBuilder sb = new StringBuilder();
                for (String key : bundle.keySet()) {
                    if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                        sb.append("\nkey1:" + key + ", value:" + bundle.getInt(key));
                    } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                        sb.append("\nkey2:" + key + ", value:" + bundle.getBoolean(key));
                    } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                        if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                            Log.i(TAG, "This message has no Extra data");
                            continue;
                        }

                        try {
                            JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                            Iterator<String> it = json.keys();

                            while (it.hasNext()) {
                                String myKey = it.next();
                                sb.append(myKey + " - " + json.optString(myKey));
                                if (myKey.equals("id")) {
                                    id = json.optString(myKey);
                                } else if (myKey.equals("type")) {
                                    tab = json.optString(myKey);
                                }
                                Log.d("1608", "我是爸爸" + id + tab);
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "Get message extra JSON error!");

                        }
                    } else {
                        sb.append("\nkey3:" + key + ", value:" + bundle.getString(key));
                        if (key.equals("cn.jpush.android.ALERT")) {
                            tag = bundle.getString(key);
                            Log.d("1608", "我是爸爸22" + tag);
                        }
                    }
                }
                return sb.toString();
            }

            //send msg to MainActivity
            private void processCustomMessage (Context context, Bundle bundle){
                //省略了
            }
        }
