package com.xingrongjinfu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: fengqian
 * @Date: 2018/5/7 15:36
 * @Description:
 */
public class DateUtil {



    public static String toSimpleDateString() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static String toDateString(Date date,String pattern) {
        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.toDateString(new Date(),"yyyy_MM"));
    }
}
