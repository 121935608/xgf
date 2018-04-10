package com.xingrongjinfu.commercial.storeApply.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.storeApply.dao.IStoreApplyDao;
import com.xingrongjinfu.commercial.storeApply.model.StoreApply;

@Service
public class StoreApplyService implements IStoreApplyService {
    @Autowired
    private IStoreApplyDao storeApplyDao;

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return storeApplyDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public int updateStoreApply(StoreApply storeApply) {
        return storeApplyDao.updateStoreApply(storeApply);
    }
}
