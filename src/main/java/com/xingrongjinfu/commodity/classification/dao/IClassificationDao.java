package com.xingrongjinfu.commodity.classification.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.user.model.User;

/**
 * 数据层
 * 
 * @author 
 */
public interface IClassificationDao
{

    /**
     * 通过ID查询
     * 
     */
    public Category findByCategoryId(String categoryId);

    
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

   
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

}
