package com.xingrongjinfu.commodity.fenlei.service;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;

public interface IFenleiService {
    List<Fenlei> getAll(String storeId) throws Exception;
    
    public List<Fenlei> queryCategorys(String storeId) throws Exception;

    

    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Fenlei fenlei);

    /**
     * 修改
     * 
     */
    public int updateCategoryInfo(Fenlei fenlei);
    
    /**
     * 通过ID查询
     * 
     */
    public Fenlei findByCategoryId(String categoryId);

    /**
     * 根据父菜单ID查询
     * 
     */
    public List<Fenlei> findCategoryByPid(String parentId)throws Exception;


    /**
     * 删除菜单
     * 
     */
    public int deleteCategory(Fenlei fenlei);
    
    public int queryCom(Map map);
    
    public List<Fenlei> getCategorys(Map map)throws Exception;
    int getByName(Map map);
}
