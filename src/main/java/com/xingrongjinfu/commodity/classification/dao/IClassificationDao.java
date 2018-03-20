package com.xingrongjinfu.commodity.classification.dao;

import java.util.List;

import com.xingrongjinfu.commodity.classification.model.Category;

/**
 * 数据层
 * 
 * @author 
 */
public interface IClassificationDao
{
	
	public List<Category> queryCategorys();

    

    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Category category);

    /**
     * 修改
     * 
     */
    public int updateCategoryInfo(Category category);
    
    /**
     * 通过ID查询
     * 
     */
    public Category findByCategoryId(String categoryId);

    /**
     * 根据父菜单ID查询
     * 
     */
	public List<Category> findCategoryByPid(String parentId);


	/**
     * 删除菜单
     * 
     */
    public int deleteCategory(Category category);
	
    public int queryCom(String id);
    
    public int isParentId(String id);
}
