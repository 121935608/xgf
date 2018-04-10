package com.xingrongjinfu.commercial.storeApply.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.storeApply.model.StoreApply;

public interface IStoreApplyDao {
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    public int updateStoreApply(StoreApply storeApply);
}
