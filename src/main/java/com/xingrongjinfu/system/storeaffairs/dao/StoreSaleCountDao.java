package com.xingrongjinfu.system.storeaffairs.dao;

import com.xingrongjinfu.system.storeaffairs.model.PurchaseDto;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("storeSaleCountDao")
public class StoreSaleCountDao extends DynamicObjectBaseDao implements IStoreSaleCountDao {


    @Override
    public List<PurchaseDto> infoQuery(Map<String, String> param) {
        List<PurchaseDto> data = null;
        try
        {
            data = (List<PurchaseDto>) this.findForList("StoreAffairMapper.infoQuery", param);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

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
