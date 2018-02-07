package com.xingrongjinfu.commodity.fenlei.dao;

import java.util.List;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;

public interface IFenleiDao {
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
     * @throws Exception 
     * 
     */
    public List<Fenlei> findCategoryByPid(String parentId) throws Exception;


    /**
     * 删除菜单
     * 
     */
    public int deleteCategory(Fenlei fenlei);
    
    public int queryCom(String id);
    
    public List<Fenlei> getCategorys(String id) throws Exception;
    
    
    
    
}
