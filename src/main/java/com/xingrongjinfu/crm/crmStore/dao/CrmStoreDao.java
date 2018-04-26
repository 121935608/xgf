package com.xingrongjinfu.crm.crmStore.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface CrmStoreDao {

    /*查询门店和督导员的所有信息*/
    List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity);
}
