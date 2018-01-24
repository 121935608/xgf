package com.xingrongjinfu.commodity.label.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
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
     * 通过名字查询
     * 
     */
	@Override
	public Label findByCategoryName(String categoryName) {
		return (Label) this.findForObject("CommodityLabelMapper.findByCategoryName", categoryName);
	}
	
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
    public List<Label> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<Label> labelPageInfo = null;
        try
        {
        	labelPageInfo = (List<Label>) this.findForList("CommodityLabelMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return labelPageInfo;

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

    /**
     * 删除信息
     * 
     */
	@Override
	public int deleteById(Label category) {
		return (int) this.delete("CommodityLabelMapper.deleteCategoryInfo", category);
	}

    @Override
    public int isExistByName(Map map) {
        
        return (int) this.findForObject("CommodityLabelMapper.isExistByName", map);
    }

	
}
