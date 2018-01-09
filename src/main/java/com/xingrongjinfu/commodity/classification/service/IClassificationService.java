package com.xingrongjinfu.commodity.classification.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.user.model.User;



/**
 *  业务层
 * 
 * @author 
 */
public interface IClassificationService
{

	public List<Category> queryCategorys();
	
    /**
     * 通过ID查询
     * 
     */
    public Category findByCategoryId(String categoryId);
   

     /**
     * 修改信息
     * 
     */
    public int updateCategoryInfo(Category category);


    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Category category);
    
    /**
     * 修改状态
     * 
     */
    public int changeCategoryStatus(Category category);
    
    /**
     * 删除菜单
     * 
     */
    public int deleteCategory(Category category);

	public List<Category> findCategoryByPid(String parentId);


	

}
