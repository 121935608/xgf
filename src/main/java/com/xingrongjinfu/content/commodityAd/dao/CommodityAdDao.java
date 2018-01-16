package com.xingrongjinfu.content.commodityAd.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;
import com.xingrongjinfu.system.commodity.model.Commodity;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("commodityAdDao")
public class CommodityAdDao extends DynamicObjectBaseDao implements ICommodityAdDao
{


    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> commodityAdPageInfo = null;
        try
        {
        	commodityAdPageInfo = (List<TableDataInfo>) this.findForList("ContentCommodityAdMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return commodityAdPageInfo;

    }
    
    /**
     * 修改信息
     * 
     */
    public int updateCommodityAdInfo(CommodityAd commodityAd)
    {
        return this.update("ContentCommodityAdMapper.updateCommodityAdInfo", commodityAd);
    }

    /**
     * 添加信息
     * 
     */
	public int addCommodityAdInfo(CommodityAd commodityAd) {
		return this.save("ContentCommodityAdMapper.addCommodityAdInfo", commodityAd);
	}

	/**
     * 通过ID查询
     * 
     */
	@Override
	public CommodityAd findByCommodityAdId(String commodityAdId) {
		return (CommodityAd) this.findForObject("ContentCommodityAdMapper.findByCommodityAdId", commodityAdId);
	}

    @Override
    public List<Integer> getAllType() {
        List<Integer> typeList = null;
        try
        {
            typeList = (List<Integer>) this.findForList("ContentCommodityAdMapper.getAllType", null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return typeList;

    }

    @Override
    public List<Category> getFL() {
        List<Category> typeList = null;
        try
        {
            typeList = (List<Category>) this.findForList("ContentCommodityAdMapper.getFL", null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return typeList;
    }

    @Override
    public List<Commodity> getCommoditys(String categoryId) {
        List<Commodity> commodityList = null;
        try
        {
            commodityList = (List<Commodity>) this.findForList("ContentCommodityAdMapper.getCommoditys", categoryId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return commodityList;
    }

    @Override
    public List<Map<String, String>> getCommoditysByAdId(String commodityAdId) {
        List<Map<String, String>> commodityList = null;
        try
        {
            commodityList = (List<Map<String, String>>) this.findForList("ContentCommodityAdMapper.getCommoditysByAdId", commodityAdId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return commodityList;
    }

}
