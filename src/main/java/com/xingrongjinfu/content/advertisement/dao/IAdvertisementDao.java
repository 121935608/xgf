package com.xingrongjinfu.content.advertisement.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.model.Carousel;


/**
 * 数据层
 * 
 * @author 
 */
public interface IAdvertisementDao
{
   
    /**
     * 根据条件分页查询
     * 
     */
    public List<Advertisement> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 通过名字查询
     * 
     */
    public Advertisement findByAdvertisementName(String advertisementName);
    
    /**
     * 修改
     * 
     */
    public int updateAdvertisementInfo(Advertisement advertisement);

    /**
     * 添加
     * 
     */
	public int addAdvertisementInfo(Advertisement advertisement);

	/**
     * 通过ID查询
     * 
     */
	public Advertisement findByAdvertisementId(String advertisementId);

	/**
     * 删除
     * 
     */
	public int deleteById(Advertisement advertisement);

	

}
