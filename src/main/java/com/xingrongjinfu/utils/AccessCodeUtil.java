/**
 * Copyright (C), 2017
 * FileName: AccessCodeUtil
 * Author:   zxuser
 * Date:     2017/12/5 17:19
 * Description: 生成6位随机访问码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成6位随机访问码〉
 *
 * @author zxuser
 * @create 2017/12/5
 * @since 1.0.0
 */
public class AccessCodeUtil {

    public static String accessCode(){
        String result = "";
        //下面的6改成8就是8位随机数字
        while (result.length() < 6) {
            String str = String.valueOf((int)(Math.random()*10));
            if(result.indexOf(str) == -1){
                result += str;
            }
        }
        return result;
    }
    public static void main(String[] args){
        for (int i=0;i<20;i++) {
            System.out.println("6位随机码:" + AccessCodeUtil.accessCode());
        }
    }
}