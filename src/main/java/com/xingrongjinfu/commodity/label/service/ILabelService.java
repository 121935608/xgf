package com.xingrongjinfu.commodity.label.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.label.model.Label;



/**
 *  业务层
 * 
 * @author 
 */
public interface ILabelService
{

    /**
     * 通过ID查询
     * 
     */
    public Label findByCategoryId(String categoryId);

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
   

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

}
