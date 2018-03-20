package com.xingrongjinfu.commercial.supplierApply.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.supplierApply.model.SupplierApply;

public interface ISupplierApplyService {
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public SupplierApply getById(String id);
    
    public int updateSupplierApply(SupplierApply supplierApply);
}
