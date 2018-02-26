package com.xingrongjinfu.memberManage.level.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.memberManage.level.model.Level;
@Repository
public class LevelDao extends DynamicObjectBaseDao implements ILevelDao {

    @Override
    public List<TableDataInfo> pageQueryInfo(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> pageQueryInfo=null;
        try{
           pageQueryInfo=(List<TableDataInfo>)this.findForList("LevelMapper.pageInfoQuery",pageUtilEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageQueryInfo;
    }

    @Override
    public List<Level> queryLevelExist(String storeId) {
        List<Level> list = null;
        try {
            list = (List<Level>) this.findForList("LevelMapper.queryLevelExist", storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addLevel(Level level) {
        return this.save("LevelMapper.addLevel", level);
    }

    @Override
    public int updateLevel(Level level) {
        return this.update("LevelMapper.updateLevel", level);
    }

    @Override
    public Level getById(String id) {
        return (Level) this.findForObject("LevelMapper.getById", id);
    }

}
