/**
 * Copyright (C), 2018
 * FileName: SaleService
 * Author:   zxuser
 * Date:     2018/1/3 19:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.sale.service;

import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.sale.dao.ISaleDao;
import com.xingrongjinfu.system.sale.model.Sale;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("saleService")
public class SaleService implements ISaleService{

    @Autowired
    private ISaleDao saleDao;

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return saleDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public List<Sale> infoQuery(Map<String, String> param) {
        return saleDao.infoQuery(param);
    }
}