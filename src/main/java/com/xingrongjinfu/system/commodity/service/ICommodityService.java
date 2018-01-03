/**
 * Copyright (C), 2018
 * FileName: ICommodityService
 * Author:   zxuser
 * Date:     2018/1/3 14:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.service;

import com.xingrongjinfu.system.commodity.model.Commodity;
import org.framework.base.util.PageUtilEntity;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public interface ICommodityService {

    List<Commodity>pageInfoQuery(PageUtilEntity pageUtilEntity);
}