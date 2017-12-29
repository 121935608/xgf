package com.xingrongjinfu.commodity.classification.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.classification.model.Category;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("classificationDao")
public class ClassificationDao extends DynamicObjectBaseDao implements IClassificationDao
{

    /**
     * 通过ID查询
     * 
     */
    public Category findByCategoryId(String categoryId)
    {
        return (Category) this.findForObject("CommodityClassificationMapper.findByCategoryId", categoryId);
    }

    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> cityPageInfo = null;
        try
        {
        	cityPageInfo = (List<TableDataInfo>) this.findForList("CommodityClassificationMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cityPageInfo;

    }

    /**
     * 新增信息
     * 
     */
    public int addCategoryInfo(Category category)
    {
        return (int) this.save("CommodityClassificationMapper.addCategoryInfo", category);
    }

    /**
     * 修改信息
     * 
     */
    public int updateCategoryInfo(Category category)
    {
        return this.update("CommodityClassificationMapper.updateCategoryInfo", category);
    }
}
