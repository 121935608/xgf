package com.xingrongjinfu.memberManage.level.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.memberManage.level.dao.ILevelDao;
import com.xingrongjinfu.memberManage.level.model.Level;

@Service
public class LevelService implements ILevelService {
    @Autowired
    private ILevelDao levelDao;

    @Override
    public List<TableDataInfo> pageQueryInfo(PageUtilEntity pageUtilEntity) {
        return levelDao.pageQueryInfo(pageUtilEntity);
    }

    @Override
    public List<Level> queryLevelExist(String storeId) {
        return levelDao.queryLevelExist(storeId);
    }

    @Override
    public int addLevel(Level level) {
        return levelDao.addLevel(level);
    }

    @Override
    public int updateLevel(Level level) {
        return levelDao.updateLevel(level);
    }

    @Override
    public Level getById(String id) {
        return levelDao.getById(id);
    }
}
