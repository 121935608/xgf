package com.xingrongjinfu.commodity.label.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.commodity.label.model.Label;



/**
 *  业务层
 * 
 * @author 
 */
public interface ILabelService
{
	/**
     * 通过名字查询
     * 
     */
    public Label findByCategoryName(String categoryName);
    
    /**
     * 校验名称是否唯一
     */
    public String checkNameUnique(Label category);

    /**
     * 通过ID查询
     * 
     */
    public Label findByCategoryId(String categoryId);

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<Label> pageInfoQuery(PageUtilEntity pageUtilEntity);
   

     /**
     * 修改信息
     * 
     */
    public int updateCategoryInfo(Label category);


    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Label category);
    
    /**
     * 修改状态
     * 
     */
    public int changeCategoryStatus(Label category);

    /**
     * 删除信息
     * 
     */
	public int deleteById(Label category);

    /**
     * 修改标签时校验名称是否唯一
     * @param map
     * @return
     */
	public String  isExistByName(Label label);
}
