package com.xingrongjinfu.system.storeaffairs.service;

import com.xingrongjinfu.system.storeaffairs.dao.IStoreSaleCountDao;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("storeSaleCountService")
public class StoreSaleCountService implements IStoreSaleCountService{


    @Autowired
    private IStoreSaleCountDao  storeSaleCountDao;
    public List<TableDataInfo> storeSaleCountQuery(PageUtilEntity pageUtilEntity){
          return storeSaleCountDao.storeSaleCountPageInfoQuery(pageUtilEntity);
    }


}
