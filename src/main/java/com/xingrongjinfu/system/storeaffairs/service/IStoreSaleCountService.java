package com.xingrongjinfu.system.storeaffairs.service;

import com.xingrongjinfu.system.storeaffairs.model.PurchaseDto;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

public interface IStoreSaleCountService {

    public List<TableDataInfo> storeSaleCountQuery(PageUtilEntity pageUtilEntity);

    List<PurchaseDto> infoQuery(Map<String, String> param);
}
