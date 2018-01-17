package com.xingrongjinfu.commodity.label.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.commodity.label.model.Label;

/**
 * 数据层
 * 
 * @author 
 */
public interface ILabelDao
{

    /**
     * 通过ID查询
     * 
     */
    public Label findByCategoryId(String categoryId);
    
    /**
     * 通过名字查询
     * 
     */
    public Label findByCategoryName(String categoryName);

    
    /**
     * 根据条件分页查询
     * 
     */
    public List<Label> pageInfoQuery(PageUtilEntity pageUtilEntity);

   
    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Label category);

    /**
     * 修改
     * 
     */
    public int updateCategoryInfo(Label category);


    /**
     * 删除
     * 
     */
	public int deleteById(Label category);

}
