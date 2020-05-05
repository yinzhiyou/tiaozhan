package com.example.android.tiaozhan.Toos.time.view;

import android.content.Context;


import com.example.android.tiaozhan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成wheel的各种选项
 * <p/>
 * Created by huangzj on 2015/12/25.
 */
public class WheelStyle {

    public static final int minYear = 1990;
    public static final int maxYear = 2030;

    /**
     * Wheel Style Hour
     */
    public static final int STYLE_HOUR = 1;
    /**
     * Wheel Style Minute
     */
    public static final int STYLE_MINUTE = 2;
    /**
     * Wheel Style Year
     */
    public static final int STYLE_YEAR = 3;
    /**
     * Wheel Style Month
     */
    public static final int STYLE_MONTH = 4;
    /**
     * Wheel Style Day
     */
    public static final int STYLE_DAY = 5;
    /**
     * Wheel Style Light Time
     */
    public static final int STYLE_LIGHT_TIME = 7;
    public static final int STYLE_LIGHT_LV = 8;
    public static final int STYLE_RESEREE_LV = 9;
    public static final int REFEREE_SPORT = 10;
    public static final int REFEREE_NUM = 11;
    public static final int GEF_LV=12;
    private WheelStyle() {}

    public static List<String> getItemList(Context context, int Style) {
        if (Style == STYLE_HOUR) {
            return createHourString();
        } else if (Style == STYLE_MINUTE) {
            return createMinuteString();
        } else if (Style == STYLE_YEAR) {
            return createYearString();
        } else if (Style == STYLE_MONTH) {
            return createMonthString();
        } else if (Style == STYLE_DAY) {
            return createDayString();
        } else if (Style == STYLE_LIGHT_TIME) {
            return createTimeString(context);
        } else if(Style == STYLE_LIGHT_LV){
            return createLVString(context);
        }else if(Style == STYLE_RESEREE_LV){
            return resereeLVString(context);
        }else if(Style == REFEREE_SPORT){
            return refereeNumString(context);
        }
        else if(Style == REFEREE_NUM){
            return createWeekString(context);
        }else if(Style == GEF_LV){
            return resereeGEFString(context);
        }else {

            throw new IllegalArgumentException("style is illegal");
        }
    }

    private static List<String> createHourString() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    private static List<String> createMinuteString() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    private static List<String> createYearString() {
        List<String> wheelString = new ArrayList<>();
        for (int i = minYear; i <= maxYear; i++) {
            wheelString.add(Integer.toString(i));
        }
        return wheelString;
    }

    private static List<String> createMonthString() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    private static List<String> createDayString() {
        List<String> wheelString = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    private static List<String> createTimeString(Context context) {
        List<String> wheelString = new ArrayList<>();
        String[] timeString = context.getResources().getStringArray(R.array.times);
        for (String reseree_num : timeString) {
            wheelString.add(reseree_num);
        }
        return wheelString;
    }


    private static List<String> endTimeString(Context context) {
        List<String> wheelString = new ArrayList<>();
        String[] timeString = context.getResources().getStringArray(R.array.times);
        for (String reseree_num : timeString) {
            wheelString.add(reseree_num);
        }
        return wheelString;
    }

    private static List<String> createWeekString(Context context) {
        List<String> wheelString = new ArrayList<>();
         String[] timeString = context.getResources().getStringArray(R.array.reseree_num);
        for (String reseree_num : timeString) {
            wheelString.add(reseree_num);
        }
        return wheelString;
    }

    private static List<String> createLVString(Context context) {
        List<String> wheelString = new ArrayList<>();
        String[] timeString = context.getResources().getStringArray(R.array.Lvle);
        for (String Lv : timeString) {
            wheelString.add(Lv);
        }
        return wheelString;
    }

    private static List<String> resereeLVString(Context context) {
        List<String> wheelString = new ArrayList<>();
        String[] timeString = context.getResources().getStringArray(R.array.reseree_lv);
        for (String reseree_lv : timeString) {
            wheelString.add(reseree_lv);
        }
        return wheelString;
    }

    private static List<String> resereeGEFString(Context context) {
        List<String> wheelString = new ArrayList<>();
        String[] timeString = context.getResources().getStringArray(R.array.gef_lv);
        for (String reseree_lv : timeString) {
            wheelString.add(reseree_lv);
        }
        return wheelString;
    }

    //裁判 球类

    private static List<String> refereeNumString(Context context) {
        List<String> wheelString = new ArrayList<>();
        String[] timeString = context.getResources().getStringArray(R.array.referee);
        for (String reseree_sport : timeString) {
            wheelString.add(reseree_sport);
        }
        return wheelString;
    }



    public static List<String> createDayString(int year, int month) {
        List<String> wheelString = new ArrayList<>();
        int size;
        if (isLeapMonth(month)) {
            size = 31;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                size = 29;
            } else {
                size = 28;
            }
        } else {
            size = 30;
        }

        for (int i = 1; i <= size; i++) {
            wheelString.add(String.format("%02d", i));
        }
        return wheelString;
    }

    /**
     * 计算闰月
     *
     * @param month
     * @return
     */
    private static boolean isLeapMonth(int month) {
        return month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12;
    }

    /**
     * 计算闰年
     *
     * @param year
     * @return
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
