package com.xingrongjinfu.system.storeaffairs.service;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface IStoreSaleCountService {

    public List<TableDataInfo> storeSaleCountQuery(PageUtilEntity pageUtilEntity);
}
