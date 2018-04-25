package com.xingrongjinfu.system.storeaffairs.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("storeSaleCountDao")
public class StoreSaleCountDao extends DynamicObjectBaseDao implements IStoreSaleCountDao {


    /**
     * 根据条件分页查询还款计划表
     *
     * @param
     * @return
     */
    public List<TableDataInfo> storeSaleCountPageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> storeSalePageInfo = null;
        try
        {
            storeSalePageInfo = (List<TableDataInfo>) this.findForList("StoreAffairMapper.storeSalepageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return storeSalePageInfo;
    }

}
