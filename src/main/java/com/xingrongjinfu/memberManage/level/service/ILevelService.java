package com.xingrongjinfu.memberManage.level.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.memberManage.level.model.Level;

public interface ILevelService {
    List<TableDataInfo>pageQueryInfo(PageUtilEntity pageUtilEntity);
    
    List<Level> queryLevelExist(String storeId);
    
    int addLevel(Level level);
    
    int updateLevel(Level level);
    
    Level getById(String id);
}
