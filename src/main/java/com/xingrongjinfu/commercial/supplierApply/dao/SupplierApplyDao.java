package com.xingrongjinfu.commercial.supplierApply.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commercial.supplierApply.model.SupplierApply;

@Repository
public class SupplierApplyDao extends DynamicObjectBaseDao implements ISupplierApplyDao {

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> supplierApplyPageInfo = null;
        try
        {
            supplierApplyPageInfo = (List<TableDataInfo>) this.findForList("SupplierApplyMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return supplierApplyPageInfo;

    }

    @Override
    public SupplierApply getById(String id) {
        return (SupplierApply) this.findForObject("SupplierApplyMapper.getById", id);
    }

    @Override
    public int updateSupplierApply(SupplierApply supplierApply) {
        
        return this.update("SupplierApplyMapper.updateSupplierApply", supplierApply);
    }

}
