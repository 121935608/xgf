package com.xingrongjinfu.crm.crmStore.service;

import com.xingrongjinfu.crm.crmStore.dao.CrmStoreDao;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmStoreServiceImpl implements CrmStoreService {
    @Autowired
    private CrmStoreDao crmStoreDao;
    @Override
    public List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity) {
        return crmStoreDao.selectStoreSup(pageUtilEntity);
    }
}
