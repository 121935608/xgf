package com.xingrongjinfu.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 数据处理工具类
 * 
 * @author y
 */
public class ObjectUtil
{

    /** 空字符串 */
    private static final String NULLSTR = "";

    /**
     * 判断一个Collection是否为空，包含List，Set，Queue
     * 
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 判断一个Collection是否非空，包含List，Set，Queue
     * 
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * 判断一个对象数组是否为空
     * 
     * @param objects 要判断的对象数组
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * 判断一个对象数组是否非空
     * 
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * 判断一个Map是否为空
     * 
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断一个Map是否为空
     * 
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * 判断一个CharSequence是否为空。 这包括了 CharBuffer，StringBuffer，StringBuilder类型，对String会自动匹配到 isEmpty(String str)
     * 
     * @param charseq CharSequence
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(CharSequence charseq)
    {
        return isNull(charseq) || charseq.length() == 0;
    }

    /**
     * 判断一个CharSequence是否非空。<br>
     * 这包括了 CharBuffer，StringBuffer，StringBuilder类型，对String会自动匹配到 isEmpty(String str)
     * 
     * @param charseq CharSequence
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(CharSequence charseq)
    {
        return !isEmpty(charseq);
    }

    /**
     * 判断一个字符串是否为空串
     * 
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * 判断一个字符串是否为非空串
     * 
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 判断一个对象是否为空
     * 
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * 判断一个对象是否非空
     * 
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * 判断一个对象是否非空 为空着设置空字符串
     */
    public static String isNullOrEmpty(Object object)
    {
        if (isNull(object))
        {
            return "";
        }
        return object.toString();
    }

    /**
     * 判断一个对象是否是数组类型（Java基本型别的数组）
     * 
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object)
    {
        return isNotNull(object) && object.getClass().isArray();
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms)
    {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0)
        {
            sb.append(day + "天");
        }
        if (hour > 0)
        {
            sb.append(hour + "小时");
        }
        if (minute > 0)
        {
            sb.append(minute + "分");
        }
        if (second > 0)
        {
            sb.append(second + "秒");
        }
        if (milliSecond > 0)
        {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }
}
