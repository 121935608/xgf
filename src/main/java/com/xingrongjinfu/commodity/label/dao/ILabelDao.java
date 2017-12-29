package com.xingrongjinfu.commodity.label.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

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
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

   
    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Label category);

    /**
     * 修改城市
     * 
     */
    public int updateCategoryInfo(Label category);

}
