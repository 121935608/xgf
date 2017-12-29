package com.xingrongjinfu.commodity.label.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.label.model.Label;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("labelDao")
public class LabelDao extends DynamicObjectBaseDao implements ILabelDao
{

    /**
     * 通过ID查询
     * 
     */
    public Label findByCategoryId(String categoryId)
    {
        return (Label) this.findForObject("CommodityLabelMapper.findByCategoryId", categoryId);
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
        	cityPageInfo = (List<TableDataInfo>) this.findForList("CommodityLabelMapper.pageInfoQuery", pageUtilEntity);
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
    public int addCategoryInfo(Label category)
    {
        return (int) this.save("CommodityLabelMapper.addCategoryInfo", category);
    }

    /**
     * 修改信息
     * 
     */
    public int updateCategoryInfo(Label category)
    {
        return this.update("CommodityLabelMapper.updateCategoryInfo", category);
    }
}
