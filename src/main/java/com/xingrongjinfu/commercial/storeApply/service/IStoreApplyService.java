package com.xingrongjinfu.commercial.storeApply.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.storeApply.model.StoreApply;

public interface IStoreApplyService {
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public int updateStoreApply(StoreApply storeApply);
}
