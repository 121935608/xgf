package com.xingrongjinfu.commercial.supplierApply.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.supplierApply.dao.ISupplierApplyDao;
import com.xingrongjinfu.commercial.supplierApply.model.SupplierApply;

@Service
public class SupplierApplyService implements ISupplierApplyService {
    @Autowired
    private ISupplierApplyDao supplierApplyDao;
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return supplierApplyDao.pageInfoQuery(pageUtilEntity);
    }
    @Override
    public SupplierApply getById(String id) {
        return supplierApplyDao.getById(id);
    }
    @Override
    public int updateSupplierApply(SupplierApply supplierApply) {
        return supplierApplyDao.updateSupplierApply(supplierApply);
    }

}
