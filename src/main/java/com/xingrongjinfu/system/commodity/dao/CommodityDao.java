/**
 * Copyright (C), 2018
 * FileName: CommodityDao
 * Author:   zxuser
 * Date:     2018/1/3 14:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.commodity.dao;

import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.utils.StringUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
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
@Repository
public class CommodityDao extends DynamicObjectBaseDao implements ICommodityDao {

    @Override
    public List<Commodity> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<Commodity> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<Commodity>)this.findForList("CommodityMapper.pageInfoQuery",pageUtilEntity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }

    @Override
    public List<Map> infoQuery(Map<String, String> param) {
        List<Map> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<Map>)this.findForList("CommodityMapper.infoQuery",param);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }
}