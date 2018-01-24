package com.xingrongjinfu.commodity.label.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.common.UserConstants;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.label.common.LabelConstant;
import com.xingrongjinfu.commodity.label.dao.ILabelDao;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.utils.ObjectUtil;


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
     * 通过名字查询
     */
    @Override
	public Label findByCategoryName(String categoryName) {
		return labelDao.findByCategoryName(categoryName);
	}
    
    /**
     * 校验名称是否唯一
     */
    @Override
	public String checkNameUnique(Label category) {
		
    	if(category.getCategoryId()==null){
    		category.setCategoryId("-1");
    	}
    	String categoryName = category.getCategoryName();
    	String categoryId = category.getCategoryId();
    	category = new Label();
    	category.setCategoryName(categoryName);
    	Label newCategory = labelDao.findByCategoryName(categoryName);
    	if(ObjectUtil.isNotNull(newCategory)&&newCategory.getCategoryId()!=categoryId){
    		return LabelConstant.NAME_NOT_UNIQUE;
    	}
    	
    	return LabelConstant.NAME_UNIQUE;
	}
    
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


    /**
     * 删除信息
     * 
     */
	@Override
	public int deleteById(Label category) {
		return labelDao.deleteById(category);
	}

    @Override
    public String isExistByName(Label label) {
        
        Label newLabel= labelDao.isExistByName(label);
        if (ObjectUtil.isNotNull(newLabel)){
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }

	


	

}
