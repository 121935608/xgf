package com.xingrongjinfu.system.storeaffairs.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface IStoreSaleCountDao {

    public List<TableDataInfo> storeSaleCountPageInfoQuery(PageUtilEntity pageUtilEntity);
}
