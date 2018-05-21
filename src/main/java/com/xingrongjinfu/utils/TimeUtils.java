package com.xingrongjinfu.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangchao
 * @version V1.0
 * @Description: 时间相关工具类
 * @date 2018/1/2 11:04
 */
public class TimeUtils
{
    public static final int MILLIS_IN_DAY = 60 * 60 * 24 * 1000;

    public static final int MILLIS_IN_MOUTH = 60 * 60 * 24 * 30;

    /**
     * 获取十位时间戳
     * @return date
     */
    public static int getNow()
    {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前日期
     * @param str 分隔符
     * @return date
     */
    public static String fileDate(String str)
    {
        str = str == null ? "" : str;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + str + "MM" + str + "dd");
        return sdf.format(new Date());
    }
    
    /**
     * 获取制定日期
     * @param str 分隔符
     * @return date
     */
    public static String fileDate(String str,Integer date)
    {
        str = str == null ? "" : str;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + str + "MM" + str + "dd");
        return sdf.format(new Date(date*1000L));
    }

    /**
     * 时间戳格式化
     */
    public static String strToDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(Long.parseLong(date)*1000));
    }

    /**
     * 获取时间map
     * @param str 分隔符
     * @return map
     */
    public static Map<String, String> getFormatDate(String str)
    {
        str = str == null ? "" : str;
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + str + "MM" + str + "dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        map.put("today", sdf.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        map.put("yesterday", sdf.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 2);
        map.put("tomorrow", sdf.format(calendar.getTime()));
        return map;
    }

    /**
     * 获取时间map
     * @param str 时间戳
     * @return map
     */
    public static Map<String, Integer> getDateInfo(String str)
    {
        Map<String, Integer> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(Long.parseLong(str)*1000));
        map.put("year", calendar.get(Calendar.YEAR));
        map.put("month", calendar.get(Calendar.MONTH)+1);
        map.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        return map;
    }


    //获取当天00:00时间戳
    public static int getStartTimeOfDay()
    {
        long now = new Date().getTime();
        String tz = "GMT+8";
        TimeZone curTimeZone = TimeZone.getTimeZone(tz);
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return Math.round(calendar.getTimeInMillis() / 1000);
    }

    //获取昨天00:00时间戳
    public static int getStartTimeOfPerDay()
    {
        long per = new Date().getTime() - MILLIS_IN_DAY;
        String tz = "GMT+8";
        TimeZone curTimeZone = TimeZone.getTimeZone(tz);
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(per);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return Math.round(calendar.getTimeInMillis() / 1000);
    }

    //获取当前日期
    public static String getNowDayStr()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HH_mm");
        String format = sdf.format(new Date());
        return format;
    }


    public static Object getStr(Integer loanCompleteTime)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String format = sdf.format(new Date(loanCompleteTime * 1000L));
        return format;
    }

	
		

}
