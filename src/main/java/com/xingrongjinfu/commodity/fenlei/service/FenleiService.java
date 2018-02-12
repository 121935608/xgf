package com.xingrongjinfu.commodity.fenlei.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.fenlei.dao.IFenleiDao;
import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
@Service
public class FenleiService implements IFenleiService{

    @Autowired
    private IFenleiDao fenleiDao;
    
    @Override
    public List<Fenlei> getAll(String storeId) throws Exception {
        
        return fenleiDao.getAll(storeId);
    }

    @Override
    public List<Fenlei> queryCategorys(String storeId) throws Exception {
        return fenleiDao.queryCategorys(storeId);
    }

    @Override
    public int addCategoryInfo(Fenlei fenlei) {
        return fenleiDao.addCategoryInfo(fenlei);
    }

    @Override
    public int updateCategoryInfo(Fenlei fenlei) {
        return fenleiDao.updateCategoryInfo(fenlei);
    }

    @Override
    public Fenlei findByCategoryId(String categoryId) {
        return fenleiDao.findByCategoryId(categoryId);
    }

    @Override
    public List<Fenlei> findCategoryByPid(String parentId) throws Exception {
        return fenleiDao.findCategoryByPid(parentId);
    }

    @Override
    public int deleteCategory(Fenlei fenlei) {
        return fenleiDao.deleteCategory(fenlei);
    }

    @Override
    public int queryCom(Map map) {
        
        return fenleiDao.queryCom(map);
    }

    @Override
    public List<Fenlei> getCategorys(Map map)throws Exception {
       
        return fenleiDao.getCategorys(map);
    }

    @Override
    public int getByName(Map map) {
        
        return fenleiDao.getByName(map);
    }



}
