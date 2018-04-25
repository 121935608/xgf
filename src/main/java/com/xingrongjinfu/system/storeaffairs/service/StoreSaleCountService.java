package com.xingrongjinfu.system.storeaffairs.service;

import com.xingrongjinfu.system.storeaffairs.dao.IStoreSaleCountDao;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("storeSaleCountService")
public class StoreSaleCountService implements IStoreSaleCountService{


    @Autowired
    private IStoreSaleCountDao  storeSaleCountDao;

    @Override
    public List<Store> infoQuery(Map<String, String> param) {
        return storeSaleCountDao.infoQuery(param);
    }

    public List<TableDataInfo> storeSaleCountQuery(PageUtilEntity pageUtilEntity){
          return storeSaleCountDao.storeSaleCountPageInfoQuery(pageUtilEntity);
    }


}
