package com.xingrongjinfu.commodity.label.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.label.dao.ILabelDao;
import com.xingrongjinfu.commodity.label.model.Label;


/**
 * 业务层处理
 * 
 * @author y
 */
@Service("labelService")
public class LabelService implements ILabelService
{

    @Autowired
    private ILabelDao labelDao;

    /**
     * 通过ID查询
     */
    public Label findByCategoryId(String categoryId)
    {
        return labelDao.findByCategoryId(categoryId);
    }

    
    /**
     * 根据条件分页查询
     */
    public List<Label> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return labelDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改信息
     * 
     */
    public int updateCategoryInfo(Label category)
    {
        return labelDao.updateCategoryInfo(category);
    }

    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Label category)
    {        
        return labelDao.addCategoryInfo(category);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeCategoryStatus(Label category)
    {
        return labelDao.updateCategoryInfo(category);
    }

}
