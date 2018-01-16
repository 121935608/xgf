package com.xingrongjinfu.content.commodityAd.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;
import com.xingrongjinfu.system.commodity.model.Commodity;


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

	/**
	 * Description: 获取所有类型<br/>
	 *
	 * @author huYL
	 * @return
	 */
	List<Integer> getAllType();
	/**
	 * Description: 获取所有分类<br/>
	 *
	 * @author huYL
	 * @return
	 */
	List<Category> getFL();
	/**
	 * Description: 获取某分类的所有商品<br/>
	 *
	 * @author huYL
	 * @return
	 */
	List<Commodity> getCommoditys(String categoryId);
	/**
	 * Description: 获取某商品的所有分类商品<br/>
	 *
	 * @author huYL
	 * @return
	 */
	List<Map<String, String>> getCommoditysByAdId(String commodityAdId);

}
