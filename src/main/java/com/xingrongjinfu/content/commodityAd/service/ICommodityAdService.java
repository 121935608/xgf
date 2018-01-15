package com.xingrongjinfu.content.commodityAd.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;

/**
 *  业务层
 * 
 * @author 
 */
public interface ICommodityAdService
{

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 修改状态
     * 
     */
    public int changeCommodityAdStatus(CommodityAd commodityAd);
    

    /**
     * 添加
     * 
     */
	public int addCommodityAdInfo(CommodityAd commodityAd);

	/**
     * 修改信息
     * 
     */
	public int updateCommodityAdInfo(CommodityAd commodityAd);

	/**
     * 通过ID查询
     * 
     */
	public CommodityAd findByCommodityAdId(String commodityAdId);

}
