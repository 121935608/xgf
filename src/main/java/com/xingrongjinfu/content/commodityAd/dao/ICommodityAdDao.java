package com.xingrongjinfu.content.commodityAd.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;


/**
 * 数据层
 * 
 * @author 
 */
public interface ICommodityAdDao
{
   
    /**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 修改
     * 
     */
    public int updateCommodityAdInfo(CommodityAd commodityAd);

    /**
     * 添加
     * 
     */
	public int addCommodityAdInfo(CommodityAd commodityAd);

	/**
     * 通过ID查询
     * 
     */
	public CommodityAd findByCommodityAdId(String CommodityAdId);

	

}
