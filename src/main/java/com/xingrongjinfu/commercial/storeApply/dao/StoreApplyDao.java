package com.xingrongjinfu.commercial.storeApply.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commercial.storeApply.model.StoreApply;

@Repository
public class StoreApplyDao extends DynamicObjectBaseDao implements IStoreApplyDao {

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> supplierApplyPageInfo = null;
        try
        {
            supplierApplyPageInfo = (List<TableDataInfo>) this.findForList("StoreApplyMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return supplierApplyPageInfo;
    }

    @Override
    public int updateStoreApply(StoreApply storeApply) {
        return this.update("StoreApplyMapper.updateStoreApply", storeApply);
    }

}
