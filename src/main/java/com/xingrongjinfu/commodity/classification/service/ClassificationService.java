package com.xingrongjinfu.commodity.classification.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.classification.dao.IClassificationDao;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.user.model.User;


/**
 * 业务层处理
 * 
 * @author y
 */
@Service("classificationService")
public class ClassificationService implements IClassificationService
{

    @Autowired
    private IClassificationDao classificationDao;

    /**
     * 通过ID查询
     */
    public Category findByCategoryId(String categoryId)
    {
        return classificationDao.findByCategoryId(categoryId);
    }

    
    /**
     * 根据条件分页查询
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return classificationDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改信息
     * 
     */
    public int updateCategoryInfo(Category category)
    {
        return classificationDao.updateCategoryInfo(category);
    }

    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Category category)
    {        
        return classificationDao.addCategoryInfo(category);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeCategoryStatus(Category category)
    {
        return classificationDao.updateCategoryInfo(category);
    }

}
