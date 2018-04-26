package com.xingrongjinfu.crm.crmStore.service;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface CrmStoreService {

    /*查询门店和督导员的所有信息*/
    List<TableDataInfo> selectStoreSup(PageUtilEntity pageUtilEntity);
}
