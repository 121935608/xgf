/**
 * Copyright (C), 2018
 * FileName: CommodityService
 * Author:   zxuser
 * Date:     2018/1/3 14:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.service;

import com.xingrongjinfu.system.commodity.dao.ICommodityDao;
import com.xingrongjinfu.system.commodity.model.Commodity;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Service("commodityService")
public class CommodityService implements ICommodityService {

    @Autowired
    private ICommodityDao commodityDao;

    @Override
    public List<Commodity> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return commodityDao.pageInfoQuery(pageUtilEntity);
    }
}