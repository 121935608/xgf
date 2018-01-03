/**
 * Copyright (C), 2018
 * FileName: SaleDao
 * Author:   zxuser
 * Date:     2018/1/3 19:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.sale.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Repository
public class SaleDao extends DynamicObjectBaseDao implements ISaleDao{


    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> tableDataInfo=null;
        try {
            tableDataInfo=(List<TableDataInfo>)this.findForList("SaleMapper.pageInfoQuery",pageUtilEntity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return tableDataInfo;
    }
}