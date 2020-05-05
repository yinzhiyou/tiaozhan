package com.example.android.tiaozhan.Toos;

import android.util.Log;

public class LogU {

    /*m默认不打印Log，如果要打印，置为true*/
    private static boolean enableLog = true;

    public static void Le(String tag,String msg){
        if(enableLog) {
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;

            int segmentSize = 3 * 1024;
            long length = msg.length();
            // 长度小于等于限制直接打印
            if (length <= segmentSize) {
                Log.e(tag, msg);
            } else {
                // 循环分段打印日志
                while (msg.length() > segmentSize) {
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.e(tag, logContent);
                }
                // 打印剩余日志
                Log.e(tag, msg);
            }
        }

    }
    public static void Ld(String tag,String msg){
        if(enableLog){
            int max_str_length = 2001 - tag.length();
            //大于4000时
            while (msg.length() > max_str_length) {
                Log.i(tag, msg.substring(0, max_str_length));
                msg = msg.substring(max_str_length);
            }
            //剩余部分
            Log.d(tag, msg);

        }


    }

    public static void Li(String tag,String msg){
        if(enableLog)
            Log.i(tag,msg);

    }
    public static void Lv(String tag,String msg){
        if(enableLog)
            Log.v(tag,msg);

    }
    public static void Lw(String tag,String msg){
        if(enableLog)
            Log.w(tag,msg);

    }
    /**
     * 截断输出日志
     * @param msg
     */
    public static void longlog(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        // 长度小于等于限制直接打印
        if (length <= segmentSize ) {
            Log.e(tag, msg);
        }else {
            // 循环分段打印日志
            while (msg.length() > segmentSize ) {
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            // 打印剩余日志
            Log.e(tag, msg);
        }
    }

}
