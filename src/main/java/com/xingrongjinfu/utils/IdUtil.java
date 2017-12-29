/**
 * Copyright (C), 2017
 * FileName: IdUtil
 * Author:   zxuser
 * Date:     2017/12/28 19:26
 * Description: 用户ID生成
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.utils;

import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户ID生成〉
 *
 * @author zxuser
 * @create 2017/12/28
 * @since 1.0.0
 */
public class IdUtil {

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}