package com.xingrongjinfu.commodity.fenlei.dao;

import java.util.List;

import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
@Repository
public class FenleiDao extends DynamicObjectBaseDao implements IFenleiDao {

    @Override
    public List<Fenlei> getAll(String storeId) throws Exception {
        
        return (List<Fenlei>) this.findForList("FenleiMapper.getAll", storeId);
    }

    @Override
    public List<Fenlei> queryCategorys(String storeId) throws Exception {
        return (List<Fenlei>) this.findForList("FenleiMapper.queryCategorys", storeId);
    }

    @Override
    public int addCategoryInfo(Fenlei fenlei) {
        return this.save("FenleiMapper.addCategoryInfo", fenlei);
    }

    @Override
    public int updateCategoryInfo(Fenlei fenlei) {
        return this.update("FenleiMapper.updateCategoryInfo", fenlei);
    }

    @Override
    public Fenlei findByCategoryId(String categoryId) {
        return (Fenlei) this.findForObject("FenleiMapper.findByCategoryId", categoryId);
    }

    @Override
    public List<Fenlei> findCategoryByPid(String parentId) throws Exception {
        return (List<Fenlei>) this.findForList("FenleiMapper.findCategoryByPid", parentId);
    }

    @Override
    public int deleteCategory(Fenlei fenlei) {
        return (int) this.delete("FenleiMapper.deleteCategoryInfo", fenlei);
    }

}
