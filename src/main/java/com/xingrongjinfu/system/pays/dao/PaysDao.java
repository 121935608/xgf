/**
 * Copyright (C), 2018
 * FileName: PayDao
 * Author:   zxuser
 * Date:     2018/1/3 10:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.pays.dao;

import com.xingrongjinfu.system.pays.model.Pays;
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
@Repository("paysDao")
public class PaysDao extends DynamicObjectBaseDao implements IPaysDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<TableDataInfo>)this.findForList("PaysMapper.PaypageInfoQuery",pageUtilEntity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }

    @Override
    public List<Pays> firstPageInfoQuery() {
        List<Pays> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<Pays>)this.findForList("StatisticsPaysMapper.PayPageInfoQuery",null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }
}