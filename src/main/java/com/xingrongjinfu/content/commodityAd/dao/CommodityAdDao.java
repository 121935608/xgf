package com.xingrongjinfu.content.commodityAd.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;

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

}
