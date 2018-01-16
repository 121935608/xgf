package com.xingrongjinfu.content.commodityAd.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.content.commodityAd.dao.ICommodityAdDao;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;
import com.xingrongjinfu.system.commodity.model.Commodity;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("commodityAdService")
public class CommodityAdService implements ICommodityAdService
{

    @Autowired
    private ICommodityAdDao commodityAdDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return commodityAdDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeCommodityAdStatus(CommodityAd commodityAd)
    {
        return commodityAdDao.updateCommodityAdInfo(commodityAd);
    }

    /**
     * 添加
     * 
     */
	@Override
	public int addCommodityAdInfo(CommodityAd commodityAd) {
		return commodityAdDao.addCommodityAdInfo(commodityAd);
	}

	/**
     * 修改信息
     * 
     */
	@Override
	public int updateCommodityAdInfo(CommodityAd commodityAd) {
		return commodityAdDao.updateCommodityAdInfo(commodityAd);
	}

	/**
     * 通过ID查询
     */
	@Override
	public CommodityAd findByCommodityAdId(String commodityAdId) {
		return commodityAdDao.findByCommodityAdId(commodityAdId);
	}

    @Override
    public List<Integer> getAllType() {
        
        return commodityAdDao.getAllType();
    }

    @Override
    public List<Category> getFL() {
        
        return commodityAdDao.getFL();
    }

    @Override
    public List<Commodity> getCommoditys(String categoryId) {
        return commodityAdDao.getCommoditys(categoryId);
    }

    @Override
    public List<Map<String, String>> getCommoditysByAdId(String commodityAdId) {
        return commodityAdDao.getCommoditysByAdId(commodityAdId);
    }
	
}
