/**
 * Copyright (C), 2018
 * FileName: ICommodityDao
 * Author:   zxuser
 * Date:     2018/1/3 14:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.dao;

import com.xingrongjinfu.system.commodity.model.Commodity;
import org.framework.base.util.PageUtilEntity;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public interface ICommodityDao {

    /**
     * 查询商品销售统计的信息
     */
    List<Commodity> pageInfoQuery(PageUtilEntity pageUtilEntity);

    List<Commodity> infoQuery(Map<String, String> param);
}